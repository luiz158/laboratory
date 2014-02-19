package br.gov.serpro.catalogo.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Anexo;
import br.gov.serpro.catalogo.entity.Situacao;
import br.gov.serpro.catalogo.rest.FaseDTO;

@PersistenceController
public class AnaliseDAO extends JPACrud<Analise, Long> {
	
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unchecked")
	public List<Analise> pesquisarAnalise(FaseDTO dto) {
		Query query = getEntityManager().createNamedQuery(Analise.ANALISES_POR_FASE, Analise.class);
		//query.setParameter("demanda", id);
		//query.setParameter("fase", fase);		
		return query.getResultList();
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
