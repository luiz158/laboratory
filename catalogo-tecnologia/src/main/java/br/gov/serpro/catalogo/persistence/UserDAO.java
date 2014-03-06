package br.gov.serpro.catalogo.persistence;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.catalogo.entity.User;

@PersistenceController
public class UserDAO extends JPACrud<User, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public User loadByCPF(String cpf) {
		String jpql = "from " + this.getBeanClass().getName() + " where name = :cpf";

		TypedQuery<User> query = getEntityManager().createQuery(jpql, User.class);
		query.setParameter("cpf", cpf);

		User result;

		try {
			result = query.getSingleResult();
		} catch (NoResultException cause) {
			result = null;
		}

		return result;
	}
	
}
