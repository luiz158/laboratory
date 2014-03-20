package bookmark.rest.datatables;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import bookmark.entity.Bookmark;

@ValidateRequest
@Path("bookmark/datatables")
@Produces(APPLICATION_JSON)
public class BookmarkDataTablesService {

	@Inject
	private EntityManager em;

	@GET
	public DataTablesResult<Bookmark> findAllPaged(@QueryParam("sEcho") Integer echo,
			@QueryParam("iDisplayStart") Integer displayStart, @QueryParam("iDisplayLength") Integer displayLength,
			@QueryParam("sSearch") String search, @QueryParam("iSortCol_0") Integer sortCol) throws Exception {

		StringBuffer jpqlBase = new StringBuffer();
		jpqlBase.append(" from Bookmark this where ");
		// jpqlBase.append(" this.id = :id or ");
		jpqlBase.append(" lower(this.description) like :description or ");
		jpqlBase.append(" lower(this.link) like :link ");

		System.out.println(sortCol);

		TypedQuery<Bookmark> query = em.createQuery(jpqlBase.toString(), Bookmark.class);
		// query.setParameter("id", search);
		query.setParameter("description", "%" + search.toLowerCase() + "%");
		query.setParameter("link", "%" + search.toLowerCase() + "%");
		query.setFirstResult(displayStart);
		query.setMaxResults(displayLength);
		List<Bookmark> data = query.getResultList();

		TypedQuery<Long> countQuery = em.createQuery("select count(this) " + jpqlBase.toString(), Long.class);
		// countQuery.setParameter("id", search);
		countQuery.setParameter("description", "%" + search.toLowerCase() + "%");
		countQuery.setParameter("link", "%" + search.toLowerCase() + "%");
		Long count = countQuery.getSingleResult();

		DataTablesResult<Bookmark> result = new DataTablesResult<Bookmark>();
		result.setEcho(echo);
		result.setTotalRecords(count);
		result.setTotalDisplayRecords(count);
		result.setData(data);

		return result;
	}
}
