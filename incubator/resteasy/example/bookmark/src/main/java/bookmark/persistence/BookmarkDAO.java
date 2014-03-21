package bookmark.persistence;

import java.util.List;

import bookmark.entity.Bookmark;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Bookmark> findAll() {
		return findByJPQL("select b from Bookmark b order by b.description");
	}
}
