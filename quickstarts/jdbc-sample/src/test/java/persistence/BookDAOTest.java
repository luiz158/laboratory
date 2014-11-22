package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import domain.Book;

@RunWith(DemoiselleRunner.class)
public class BookDAOTest {
	
	@Inject
	BookDAO dao;
	
	Book book = new Book(1, "Title");
	
	@Before
	public void before() throws Exception {
		for (Book book : dao.findAll()) {
			dao.delete(book.getId());
		}
	}
	
	@Test
	public void testInsert() throws Exception {
		dao.insert(book);
		List<Book> lista = dao.findAll();
		assertNotNull(lista);
		assertEquals(1, lista.size());
	}
	
	@Test
	public void testUpdate() throws Exception {
		dao.insert(book);
		Book bookToUpdate = dao.findById(1);
		bookToUpdate.setDescription("New Title");
		dao.update(bookToUpdate);
		Book bookUpdated = dao.findById(1);
		assertEquals("New Title", bookUpdated.getDescription());
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.insert(book);
		
		List<Book> lista = dao.findAll();
		assertNotNull(lista);
		assertEquals(1, lista.size());
		
		dao.delete(dao.findById(1).getId());
		lista = dao.findAll();
		assertEquals(0, lista.size());
	}	
}
