package br.gov.serpro.catalogo.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.FaseHistorico;

@PersistenceController
public class FaseHistoricoDAO extends JPACrud<FaseHistorico, Long> {

	private static final long serialVersionUID = 1L;

	public List<FaseHistorico> obterHistorico(List<Long> ids) {
		String jpql = "select f from FaseHistorico f where f.fase.id in (:ids) ORDER BY f.data DESC ";
		TypedQuery<FaseHistorico> query = getEntityManager().createQuery(jpql, FaseHistorico.class);
		query.setParameter("ids", ids);
		return query.getResultList();
	}
	

}
