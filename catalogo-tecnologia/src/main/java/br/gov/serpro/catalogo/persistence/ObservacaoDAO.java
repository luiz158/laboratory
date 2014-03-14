package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.Fase;
import br.gov.serpro.catalogo.entity.Observacao;

@PersistenceController
public class ObservacaoDAO extends JPACrud<Observacao, Long> {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Observacao> observacoes(Fase fase) {
		Query query = getEntityManager().createNamedQuery(Observacao.OBSERVACOES_NA_FASE, Observacao.class);
		query.setParameter("fase", fase.getId());		
		return query.getResultList();
	}	
	
}
