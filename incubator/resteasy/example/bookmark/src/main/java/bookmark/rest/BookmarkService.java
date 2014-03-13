package bookmark.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import bookmark.business.BookmarkBC;
import bookmark.entity.Bookmark;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@Path("api/bookmark")
public class BookmarkService {

	@Inject
	private BookmarkBC bc;

	@GET
	@Produces(APPLICATION_JSON)
	public List<Bookmark> findAll() throws Exception {
		return bc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(APPLICATION_JSON)
	public Bookmark load(@PathParam("id") Long id) throws Exception {
		return bc.load(id);
	}

	@POST
	@Transactional
	public Response insert(Bookmark entity) {
		if (entity.getId() != null) {
			return Response.status(BAD_REQUEST).entity("Não defina o atributo \"id\"").type(TEXT_PLAIN).build();
		}

		entity.setId(null);
		Long id = bc.insert(entity).getId();

		return Response.status(CREATED).entity(id).build();
	}

	@POST
	@Path("{id}")
	@Transactional
	public Response update(@PathParam("id") Long id, Bookmark entity) {
		if (entity.getId() != null) {
			return Response.status(BAD_REQUEST).entity("Não defina o atributo \"id\"").type(TEXT_PLAIN).build();
		}

		entity.setId(id);
		bc.update(entity);

		return Response.status(NO_CONTENT).build();
	}

	@DELETE
	// @Path("{id}")
	@Transactional
	public List<Long> delete(List<Long> ids) throws Exception {
		bc.delete(ids);

		return ids;
	}
}
