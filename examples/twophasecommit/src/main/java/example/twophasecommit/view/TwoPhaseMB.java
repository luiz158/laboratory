package example.twophasecommit.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import example.twophasecommit.business.AuditingBC;
import example.twophasecommit.business.BookBC;
import example.twophasecommit.domain.Auditing;
import example.twophasecommit.domain.Book;

@ViewController
public class TwoPhaseMB {

	private static final long serialVersionUID = 1L;

	@Inject
	private BookBC bookBC;
	
	@Inject
	private AuditingBC auditingBC;
	
	@Inject
	private MessageContext messageContext;
	
	public List<Book> getBooks() {
		return this.bookBC.findAll();
	}
	
	public List<Auditing> getAuditings(){
		return this.auditingBC.findAll();
	}
	
	public void limparBase() {
		for (Book b : bookBC.findAll()){
			bookBC.delete(b.getId());
		}
		for (Auditing a : auditingBC.findAll()) {
			auditingBC.delete(a.getId());
		}
	}
	
	@Transactional
	public void addBookSuccessfully() {
		limparBase();
		try { 
			bookBC.insert(new Book("Título 1", "Autor 1"));
			auditingBC.insert(new Auditing("INSERT BOOK 1"));
			messageContext.add("Test 1 - OK - Book and Auditing must be inserted!", SeverityType.INFO);
		} catch (Exception e) {
			messageContext.add("Test 1 - ERROR - Book and Auditing should be inserted, but were not!", SeverityType.ERROR);
		}
	}
	
	@Transactional
	public void addBookUnsuccessfully() {
		Book b = new Book("Título 2","Autor 2");
		b.setId(1L);
		try {
			auditingBC.insert(new Auditing("INSERT BOOK 2"));
			bookBC.insert(b);
			messageContext.add("Test 2 - ERRO - Book and Auditing must not be inserted, but were!", SeverityType.ERROR);
		} catch (Exception e) {
			messageContext.add("Test 2 - OK - Book and Auditing must not be inserted and were not!", SeverityType.INFO);
		}
	}
	
}
