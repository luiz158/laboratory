package br.gov.serpro.catalogo.persistence;

import javax.enterprise.event.Observes;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.security.AfterLoginSuccessful;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.serpro.catalogo.entity.Usuario;

@PersistenceController
public class UserDAO extends JPACrud<Usuario, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario loadByCPF(String cpf) {
		String jpql = "from " + this.getBeanClass().getName() + " where cpf = :cpf";

		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("cpf", cpf);

		Usuario result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException cause) {
			result = null;
		}

		return result;
	}

	public boolean existeCadastroParaCPF(String cpf) {
		String jpql = "from " + this.getBeanClass().getName() + " where cpf = :cpf";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("cpf", cpf);			
		return !query.getResultList().isEmpty();
	}
	
		
}
