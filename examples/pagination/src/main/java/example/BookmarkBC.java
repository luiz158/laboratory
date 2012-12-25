package example;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class BookmarkBC {
	
	@Inject
	private BookmarkDAO dao; 
	
	public List<Bookmark> findAll() {
		return dao.findAll();
	}
	
	public void insert(final Bookmark bean) {
		dao.insert(bean);
	}
	
	@Startup
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
