package example.twophasecommit;

 
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import example.twophasecommit.business.AuditingBC;
import example.twophasecommit.business.BookBC;
import example.twophasecommit.domain.Auditing;
import example.twophasecommit.domain.Book;

@RunWith(DemoiselleRunner.class)
public class BookTest {

	@Inject
	private BookBC bookbc;
	
	@Inject
	private AuditingBC auditingbc;
	
	@Before
	public void limparBase() {
		for (Book b : bookbc.findAll()){
			bookbc.delete(b.getId());
		}
		for(Auditing a : auditingbc.findAll()) {
			auditingbc.delete((long) a.getId());
		}
	}
	
	@Test
	public void addBookSuccessfully() {
		boolean flag = false;
		bookbc.insert(new Book("Título 1", "Autor 1"));
		auditingbc.insert(new Auditing("Ação: Insert / Bean: Book"));
		if (bookbc.findAll().size()==1 && auditingbc.findAll().size()==1) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	
	
}
