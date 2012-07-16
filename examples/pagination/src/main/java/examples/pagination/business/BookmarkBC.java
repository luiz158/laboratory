package examples.pagination.business;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import examples.pagination.domain.Bookmark;
import examples.pagination.persistence.BookmarkDAO;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
			insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Google", "https://www.google.com.br/"));
			insert(new Bookmark("SERPRO", "https://www.serpro.gov.br/"));
			insert(new Bookmark("UOL", "http://www.uol.com.br/"));
			insert(new Bookmark("JBoss", "http://www.jboss.org/"));
			insert(new Bookmark("Primefaces", "http://primefaces.org/"));
			insert(new Bookmark("FISL", "http://softwarelivre.org/fisl13"));
			insert(new Bookmark("FTSL", "http://www.ftsl.org.br/"));
		}
	}
}
