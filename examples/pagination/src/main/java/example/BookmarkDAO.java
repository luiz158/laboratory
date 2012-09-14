package example;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class BookmarkDAO {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private PaginationContext paginationContext;

	public void insert(final Bookmark entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public List<Bookmark> findAll() {
		String jpql = "select this from Bookmark this";
				
		TypedQuery<Bookmark> query = entityManager.createQuery(jpql, Bookmark.class);
		
		Pagination pagination = paginationContext.getPagination(Bookmark.class);

		if (pagination != null) {
			pagination.setTotalResults(count().intValue());
			query.setFirstResult(pagination.getFirstResult());
			query.setMaxResults(pagination.getPageSize());
		}

		return query.getResultList();
	}
	
	private Long count() {
		Query query = entityManager.createQuery("select count(this) from Bookmark this");
		return (Long) query.getSingleResult();	
	}
	
	
}
