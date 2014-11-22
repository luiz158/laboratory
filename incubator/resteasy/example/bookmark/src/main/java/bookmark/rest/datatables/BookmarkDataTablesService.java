package bookmark.rest.datatables;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.spi.validation.ValidateRequest;

import bookmark.business.BookmarkBC;
import bookmark.entity.Bookmark;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;

@ValidateRequest
@Path("bookmark/datatables")
@Produces(APPLICATION_JSON)
public class BookmarkDataTablesService {

	@Inject
	private BookmarkBC bc;

	@Inject
	private PaginationContext paginationContext;

	@GET
	public DataTablesResult<Bookmark> findAllPaged(@QueryParam("sEcho") Integer echo,
			@QueryParam("iDisplayStart") Integer displayStart, @QueryParam("iDisplayLength") Integer displayLength)
			throws Exception {

		Pagination pagination = paginationContext.getPagination(Bookmark.class, true);
		pagination.setPageSize(displayLength);
		pagination.setFirstResult(displayStart);

		List<Bookmark> data = bc.findAll();
		Long count = (long) pagination.getTotalResults();

		DataTablesResult<Bookmark> result = new DataTablesResult<Bookmark>();
		result.setEcho(echo);
		result.setTotalRecords(count);
		result.setTotalDisplayRecords(count);
		result.setData(data);

		return result;
	}
}
