package br.gov.serpro.catalogo.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.StatusEnum;
import br.gov.serpro.catalogo.rest.FaseDTO;

@PersistenceController
public class FaseDAO extends JPACrud<Fase, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	
	@Override
	public void delete(Long id) {
		logger.debug("Implementar a exclusão lógica..");		
	}
	
	public List<Fase> pesquisar(FaseDTO dto) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Fase> query = builder.createQuery(Fase.class);
	    Root<Fase> fase = query.from(Fase.class);
	    query.select(fase);
	 
	    List<Predicate> predicateList = new ArrayList<Predicate>();	 
	    	    
	    predicateList.add(builder.notEqual(fase.<StatusEnum>get("status"), StatusEnum.EXCLUIDO.ordinal()));	    
	 
	    if (dto.getIdentificador() != null) {
	    	Predicate p = builder.equal(fase.<String>get("identificador"), dto.getIdentificador());
	        predicateList.add(p);
	    }
	    
	    if (dto.getFase() != null) {
	    	Predicate p = builder.equal(fase.<FaseEnum>get("fase"), FaseEnum.getFase(dto.getFase()));
	        predicateList.add(p);
	    }
	    
	    if (dto.getPalavraChave() != null && !dto.getPalavraChave().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(fase.<String>get("objetivo")), "%"+dto.getPalavraChave().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getUnidadeGestora() != null && !dto.getUnidadeGestora().isEmpty()) {
	        Predicate p = builder.like(
	                builder.upper(fase.<String>get("unidadeGestora")), "%"+dto.getUnidadeGestora().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getOrigemReferencia() != null && dto.getOrigemReferencia().getId()>0) {
	    	Predicate p = builder.equal(fase.get("origemReferencia").<Long>get("id"), +dto.getOrigemReferencia().getId());
	        predicateList.add(p);
	    }
	    
	    if (dto.getCodigoReferencia() != null && !dto.getCodigoReferencia().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(fase.<String>get("codigoReferencia")), "%"+dto.getCodigoReferencia().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getFinalizada()!= null &&  dto.getFinalizada() == 1) {
	    	Predicate p = builder.isNotNull(fase.<Date>get("dataFinalizacao"));
	        predicateList.add(p);
	    }
	    
	    if (dto.getFinalizada()!= null &&  dto.getFinalizada() == 0) {
	    	Predicate p = builder.isNull(fase.<Date>get("dataFinalizacao"));
	        predicateList.add(p);
	    }
	    
	    if (dto.getFinalizacaoApartir()!= null ) {
	    	Predicate p = builder.greaterThanOrEqualTo(fase.<Date>get("dataFinalizacao"), dto.getFinalizacaoApartir());
	        predicateList.add(p);	        
	    }
	    
	    if (dto.getFinalizacaoAte()!= null ) {
	    	Predicate p = builder.lessThanOrEqualTo(fase.<Date>get("dataFinalizacao"), dto.getFinalizacaoAte());
	        predicateList.add(p);	        
	    }	   	 
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    query.where(predicates);	 
	    return getEntityManager().createQuery(query).getResultList();
	}


	public Fase obterFasePosterior(Long id) {
		//Query query = getEntityManager().createNamedQuery(Fase.OBTER_POR_FASE_ANTERIOR, Fase.class);
		String jpql = "select f from Fase f where (f.faseAnterior.id = :id) and (f.status <> :status)";
		TypedQuery<Fase> query = getEntityManager().createQuery(jpql, Fase.class);
		query.setParameter("id", id);
		query.setParameter("status", StatusEnum.EXCLUIDO);
		Fase fase = null;
		try{
			fase = query.getResultList().get(0);
		}catch(IndexOutOfBoundsException e){}
		return fase;
	}

	public List<Fase> obterCadeiaApartirDafaseInicialComExcluidos(Long id) {
		String jpql = "select f from Fase f where (f.id = :id or f.faseInicial.id = :id) order by id ASC";
		TypedQuery<Fase> query = getEntityManager().createQuery(jpql, Fase.class);
		query.setParameter("id", id);
		return query.getResultList();
	}	
	
	public List<Fase> obterCadeiaApartirDafaseInicial(Long id) {
		String jpql = "select f from Fase f where (f.id = :id or f.faseInicial.id = :id) and f.status <> :status order by id ASC";
		TypedQuery<Fase> query = getEntityManager().createQuery(jpql, Fase.class);
		query.setParameter("id", id);
		query.setParameter("status", StatusEnum.EXCLUIDO);
		return query.getResultList();
	}	
	
	
	@SuppressWarnings("unchecked")
	public Map<FaseEnum, Integer> totalPorFase(){
		Map<FaseEnum, Integer> map = new LinkedHashMap<FaseEnum, Integer>();
		String jpql = "SELECT f.fase, count(f) FROM Fase f where f.status <> :status  GROUP BY f.fase";
		List<Object[]> results = getEntityManager().createQuery(jpql).setParameter("status", StatusEnum.EXCLUIDO).getResultList();
		for (Object[] result : results) {
			map.put((FaseEnum) result[0], ((Number) result[1]).intValue());
		}
		return map;
		
	}
		
	
}
