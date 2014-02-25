package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Anexo;
import br.gov.serpro.catalogo.entity.Fase;

@PersistenceController
public class AnexoDAO extends JPACrud<Anexo, Long> {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Anexo> listarSemCarregarBytes(Fase fase) {
		Query query = getEntityManager().createNamedQuery(Anexo.ANEXOS_DA_DEMANDA_NA_FASE, Anexo.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}	
	
}
