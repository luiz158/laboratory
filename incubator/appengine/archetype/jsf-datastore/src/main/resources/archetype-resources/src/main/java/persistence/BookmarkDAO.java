package ${package}.persistence;

import br.gov.frameworkdemoiselle.appengine.template.DatastoreServiceCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

import ${package}.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends DatastoreServiceCrud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;
}
