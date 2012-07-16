package example.twophasecommit.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import example.twophasecommit.domain.Book;

@PersistenceController
public class BookDAO extends JPACrud<Book, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject @Name("database1-ds")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
	
}
