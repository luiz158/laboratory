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
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.rest.FaseDTO;

@PersistenceController
public class AnaliseDAO extends JPACrud<Analise, Long> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Analise> pesquisarAnalise(FaseDTO dto) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Analise> query = builder.createQuery(Analise.class);
	    Root<Analise> analise = query.from(Analise.class);
	    query.select(analise);
	 
	    List<Predicate> predicateList = new ArrayList<Predicate>();	 
	 
	    if (dto.getId() != null) {
	    	Predicate p = builder.equal(analise.<Long>get("id"), dto.getId());
	        predicateList.add(p);
	    }
	    
	    if (dto.getPalavraChave() != null && !dto.getPalavraChave().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(analise.<String>get("detalhamento")), "%"+dto.getPalavraChave().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getGestor() != null && !dto.getGestor().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(analise.<String>get("gestorAnalise")), "%"+dto.getGestor().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getOrigemReferencia() != null && !dto.getOrigemReferencia().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(analise.<String>get("origemReferencia")), "%"+dto.getOrigemReferencia().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    if (dto.getCodigoReferencia() != null && !dto.getCodigoReferencia().isEmpty()) {
	    	Predicate p = builder.like(
	                builder.upper(analise.<String>get("codigoReferencia")), "%"+dto.getCodigoReferencia().toUpperCase()+"%");
	        predicateList.add(p);
	    }
	    
	    //TODO Tratar a finalização.
	    
	 
	 
	    Predicate[] predicates = new Predicate[predicateList.size()];
	    predicateList.toArray(predicates);
	    query.where(predicates);	 
	    return getEntityManager().createQuery(query).getResultList();
	}	
	
	
	@Startup
	@Transactional //TODO Remover este médoto para produção.
	public void criarAnalisesDeExemplo(){
		Analise a = new Analise();
		a.setAreaGestorAnalise("CETEC");
		a.setCodigoReferencia("R171");
		a.setDataAnalise(new Date());
		a.setDemandante("Edson Arantes Nascimento");
		a.setDetalhamento("Analisar o uso de bolas de futsal na copa do mundo.");
		a.setGestorAnalise("Sérgio Hein");
		a.setOrigemReferencia("Twitter do Romário");
		a.setSituacao(Situacao.APROVADO);
		a.setJustificativa("É possível utilizar esta bola, pois será facilitada a ocorrência de tabacas e gols.");
		insert(a);
		
		a = new Analise();
		a.setAreaGestorAnalise("GLSDR");
		a.setCodigoReferencia("R206/123");
		a.setDataAnalise(new Date());
		a.setDemandante("Bidica Labonga");
		a.setDetalhamento("Verificar se é possível utilizar microondas para secar os uniformes.");
		a.setGestorAnalise("Marlinhos Carvalhal da Silva");
		a.setOrigemReferencia("Solicita");
		a.setSituacao(Situacao.REPROVADO);
		a.setJustificativa("O problema seria que os uniformes possuem substancias químicas que seriam alteradas durante a execução. O risco de explosão catastrófica é muito alto.");
		insert(a);
		
		a = new Analise();
		a.setAreaGestorAnalise("SUPST");
		a.setCodigoReferencia("R2424");
		a.setDataAnalise(new Date());
		a.setDemandante("Falcão Brega");
		a.setDetalhamento("Queremos programar em PHP para iOS");
		a.setGestorAnalise("Wilson Simoninha");
		a.setOrigemReferencia("SPEKX");
		a.setSituacao(Situacao.RASCUNHO);
		insert(a);
	}
	
}
