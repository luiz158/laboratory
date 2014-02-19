package br.gov.serpro.catalogo.persistence;

import java.util.Date;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Situacao;

@PersistenceController
public class AnaliseDAO extends JPACrud<Analise, Long> {
	
	private static final long serialVersionUID = 1L;
		
	
	@Startup
	@Transactional //TODO Remover este médoto para produção.
	public void criarAnalisesDeExemplo(){
		Analise a = new Analise();		
		a.setArea("CETEC");
		a.setCodigoReferencia("R171");
		a.setDataRealizacao(new Date());
		a.setDemandante("Edson Arantes Nascimento");
		a.setObjetivo("Analisar o uso de bolas de futsal na copa do mundo.");
		a.setGestor("Sérgio Hein");
		a.setOrigemReferencia("Twitter do Romário");
		a.setSituacao(Situacao.APROVADO);
		a.setSituacaoJustificativa("É possível utilizar esta bola, pois será facilitada a ocorrência de tabacas e gols.");
		insert(a);		
		
	}
	
	
}
