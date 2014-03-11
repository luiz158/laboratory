package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.FaseMembro;

@PersistenceController
public class FaseMembroDAO extends JPACrud<FaseMembro, Long> {
	
	private static final long serialVersionUID = 1L;
		
	@SuppressWarnings("unchecked")
	public List<FaseMembro> membrosDaFase(Fase fase) {
		Query query = getEntityManager().createNamedQuery(FaseMembro.MEMBROS_DA_FASE, FaseMembro.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}
	
}
