package exp.bookmark.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import exp.bookmark.domain.Bookmark;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	// @PersistenceContext(type = PersistenceContextType.EXTENDED)
	// private EntityManager entityManager;
	//
	// @Override
	// protected EntityManager getEntityManager() {
	// return entityManager;
	// }
}
