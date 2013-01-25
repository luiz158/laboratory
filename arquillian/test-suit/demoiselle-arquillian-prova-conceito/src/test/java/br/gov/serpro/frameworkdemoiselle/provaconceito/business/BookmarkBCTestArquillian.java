package br.gov.serpro.frameworkdemoiselle.provaconceito.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.template.Crud;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transaction;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.serpro.frameworkdemoiselle.provaconceito.domain.Bookmark;
import br.gov.serpro.frameworkdemoiselle.provaconceito.persistence.BookmarkDAO;

@RunWith(Arquillian.class)
public class BookmarkBCTestArquillian {

	@Inject
	private BookmarkBC bookmarkBC;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(Bookmark.class)
				.addClass(BookmarkBC.class)
				.addClass(DelegateCrud.class)
				.addClass(Crud.class)
				.addClass(BookmarkDAO.class)
				.addClass(JPACrud.class)
				.addClass(DemoiselleException.class)
				.addClass(Pagination.class)
				.addClass(ResourceBundle.class)
				.addClass(Transaction.class)
				.addClass(Beans.class)
				.addClass(Locale.class)
				.addClass(BeanManager.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testInsert() {
		
		bookmarkBC.teste();
		
//		Bookmark bookmark = new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
//		bookmarkBC.insert(bookmark);
//		List<Bookmark> listaBookmarks = bookmarkBC.findAll();
//		assertNotNull(listaBookmarks);
//		assertEquals(1, listaBookmarks.size());
	}

}
