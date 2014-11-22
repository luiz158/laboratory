package bookmark.business;

import bookmark.entity.Bookmark;
import bookmark.persistence.BookmarkDAO;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {

	private static final long serialVersionUID = 1L;

	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark("Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Jira", "http://demoiselle.atlassian.net"));
			insert(new Bookmark("GitHub", "http://github.com/demoiselle"));
			insert(new Bookmark("Maven", "http://search.maven.org/#search%7Cga%7C1%7Cdemoiselle"));
			insert(new Bookmark("SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark("Blog", "http://frameworkdemoiselle.wordpress.com"));
			insert(new Bookmark("Twitter", "http://twitter.com/fwkdemoiselle"));
			insert(new Bookmark("Facebook", "http://facebook.com/FrameworkDemoiselle"));
			insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));

			for (int i = 0; i < 1000; i++) {
				insert(new Bookmark("Book " + i, "http://" + i));
			}
		}
	}
}
