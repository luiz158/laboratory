package exp.bookmark.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.transaction.Transactional;
import exp.bookmark.business.BookmarkBC;
import exp.bookmark.domain.Bookmark;

@Stateless
@Remote(BookmarkEJBService.class)
public class BookmarkEJBServiceImpl implements BookmarkEJBService {

	private static final long serialVersionUID = 1L;

	@Inject
	private BookmarkBC bookmarkBC;

	private Bookmark parse(BookmarkEJB bookmarkEJB) {
		Bookmark result = new Bookmark();

		result.setId(bookmarkEJB.getId());
		result.setDescription(bookmarkEJB.getDescription());
		result.setLink(bookmarkEJB.getLink());

		return result;
	}

	private BookmarkEJB parse(Bookmark bookmark) {
		BookmarkEJB result = new BookmarkEJB();

		result.setId(bookmark.getId());
		result.setDescription(bookmark.getDescription());
		result.setLink(bookmark.getLink());

		return result;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bookmarkBC.delete(id);
	}

	@Override
	public List<BookmarkEJB> findAll() {
		List<BookmarkEJB> result = new ArrayList<BookmarkEJB>();

		for (Bookmark bookmark : bookmarkBC.findAll()) {
			result.add(parse(bookmark));
		}

		return result;
	}

	@Override
	@Transactional
	public void insert(BookmarkEJB bookmarkEJB) {
		bookmarkBC.insert(parse(bookmarkEJB));
	}

	@Override
	public BookmarkEJB load(Long id) {
		return parse(bookmarkBC.load(id));
	}

	@Override
	@Transactional
	public void update(BookmarkEJB bookmarkEJB) {
		bookmarkBC.update(parse(bookmarkEJB));
	}
}
