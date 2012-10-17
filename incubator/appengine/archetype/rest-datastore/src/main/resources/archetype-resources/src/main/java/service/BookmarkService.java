package ${package}.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.gov.frameworkdemoiselle.util.Strings;
import ${package}.business.BookmarkBC;
import ${package}.domain.Bookmark;

@Path("/bookmark.json")
@Produces("application/json;charset=UTF-8")
public class BookmarkService {

	@Inject
	private BookmarkBC bc;

	@GET
	public List<Bookmark> findAll() {
		return bc.findAll();
	}

	@PUT
	public void insert(@FormParam("link") String link, @FormParam("description") String description) {
		Bookmark bookmark = new Bookmark();
		bookmark.setLink(link);
		bookmark.setDescription(description);

		bc.insert(bookmark);
	}

	@DELETE
	public void delete(@QueryParam("id") Long id) {
		bc.delete(id);
	}

	@POST
	public void update(@FormParam("id") String id, @FormParam("link") String link,
			@FormParam("description") String description) {

		if (Strings.isEmpty(id)) {
			throw new ValidationException("O id deve ser preenchido!");
		}

		Bookmark bookmark = bc.load(Long.valueOf(id));

		if (!Strings.isEmpty(link)) {
			bookmark.setLink(link);
		}

		if (!Strings.isEmpty(description)) {
			bookmark.setDescription(description);
		}

		bc.update(bookmark);
	}
}
