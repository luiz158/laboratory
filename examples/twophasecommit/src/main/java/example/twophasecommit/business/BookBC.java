package example.twophasecommit.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import example.twophasecommit.domain.Book;
import example.twophasecommit.persistence.BookDAO;

@BusinessController
public class BookBC extends DelegateCrud<Book, Long, BookDAO> {
		
	private static final long serialVersionUID = 1L;

}
