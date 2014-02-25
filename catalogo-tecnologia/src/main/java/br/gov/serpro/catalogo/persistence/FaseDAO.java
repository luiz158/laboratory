package br.gov.serpro.catalogo.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseEnum;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.rest.FaseDTO;

@PersistenceController
public class FaseDAO extends JPACrud<Fase, Long> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Fase> pesquisar(FaseDTO dto) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Fase> query = builder.createQuery(Fase.class);
	    Root<Fase> fase = query.from(Fase.class);
	    query.select(fase);
	 
	    List<Predicate> predicateList = new ArrayList<Predicate>();	 
	 
	    if (dto.getId() != null) {
	    	Predicate p = builder.equal(fase.<Long>get("id"), dto.getId());
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
	    
	    if (dto.getGestor() != null && !dto.getGestor().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(fase.<String>get("gestor")), "%"+dto.getGestor().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getOrigemReferencia() != null && !dto.getOrigemReferencia().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(fase.<String>get("origemReferencia")), "%"+dto.getOrigemReferencia().toUpperCase()+"%");
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
	
	
	
}
