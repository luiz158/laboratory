package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Analise;
import br.gov.serpro.catalogo.entity.Anexo;
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
	
}
