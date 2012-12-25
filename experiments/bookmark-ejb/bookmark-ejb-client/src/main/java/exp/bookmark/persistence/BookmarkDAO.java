package exp.bookmark.persistence;

import static javax.naming.Context.URL_PKG_PREFIXES;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import exp.bookmark.domain.Bookmark;
import exp.bookmark.service.BookmarkEJB;
import exp.bookmark.service.BookmarkEJBService;

@PersistenceController
public class BookmarkDAO implements Crud<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	private BookmarkEJBService getEJBService() {
		BookmarkEJBService result;

		try {
			final Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context context = new InitialContext(props);

			String jndi = "ejb:/bookmark-ejb-server/BookmarkEJBServiceImpl!" + BookmarkEJBService.class.getName();
			result = (BookmarkEJBService) context.lookup(jndi);

		} catch (NamingException e) {
			result = null;
		}

		return result;
	}

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
		getEJBService().delete(id);
	}

	@Override
	public List<Bookmark> findAll() {
		List<Bookmark> result = new ArrayList<Bookmark>();

		for (BookmarkEJB bookmarkEJB : getEJBService().findAll()) {
			result.add(parse(bookmarkEJB));
		}

		return result;
	}

	@Override
	@Transactional
	public void insert(Bookmark bookmark) {
		getEJBService().insert(parse(bookmark));
	}

	@Override
	public Bookmark load(Long id) {
		return parse(getEJBService().load(id));
	}

	@Override
	@Transactional
	public void update(Bookmark bookmark) {
		getEJBService().update(parse(bookmark));
	}

}
