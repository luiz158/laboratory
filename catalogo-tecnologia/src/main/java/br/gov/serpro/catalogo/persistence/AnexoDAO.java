package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Transient;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Anexo;

@PersistenceController
public class AnexoDAO extends JPACrud<Anexo, Long> {
	
	private static final long serialVersionUID = 1L;

	public List<Anexo> listarSemCarregarBytes(Long id, Integer fase) {
		Query query = getEntityManager().createNamedQuery(Anexo.ANEXOS_DA_DEMANDA__NA_FASE, Anexo.class);
		query.setParameter("demanda", id);
		query.setParameter("fase", fase);		
		return query.getResultList();
	}	
	
}
