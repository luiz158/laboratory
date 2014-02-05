package br.gov.frameworkdemoiselle.persistence;

import br.gov.frameworkdemoiselle.domain.Recurso;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import javax.persistence.NoResultException;

/**
 * @author escritorio
 */
@PersistenceController
public class RecursoDAO extends JPACrud<Recurso, Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param nome
	 * @return
	 */
	public Recurso load(String nome) {
		try {
			return (Recurso) getEntityManager().createNamedQuery("Recurso.findByNome").setParameter("nome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
     *
     */
	public void clear() {
		for (Recurso object : findAll()) {
			delete(object.getId());
		}
	}

}
