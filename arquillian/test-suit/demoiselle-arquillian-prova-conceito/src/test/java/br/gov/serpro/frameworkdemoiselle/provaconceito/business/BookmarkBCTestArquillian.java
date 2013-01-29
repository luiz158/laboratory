package br.gov.serpro.frameworkdemoiselle.provaconceito.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.serpro.frameworkdemoiselle.provaconceito.domain.Bookmark;
import br.gov.serpro.frameworkdemoiselle.provaconceito.persistence.BookmarkDAO;

@RunWith(Arquillian.class)
public class BookmarkBCTestArquillian {

	private static final String WEBAPP_SRC = "src/main/webapp";
	
	@Inject
	private BookmarkBC bookmarkBC;

	@Deployment
	public static WebArchive createDeployment() {
		
		File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeAndTestDependencies()
                .asFile();
		
		return ShrinkWrap.create(WebArchive.class)
				.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
				.addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")), "beans.xml")
				.addAsResource(new FileAsset(new File("src/test/resources/META-INF/persistence.xml")), "META-INF/persistence.xml")
				.addAsLibraries(libs)
				.addClass(Bookmark.class)
				.addClass(BookmarkBC.class)
				.addClass(BookmarkDAO.class);
	}

	@Before
	public void before() {
		for (Bookmark bookmark : bookmarkBC.findAll()) {
			bookmarkBC.delete(bookmark.getId());
		}
	}

	@Test
	public void testLoad() {
		bookmarkBC.load();
		List<Bookmark> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(10, listaBookmarks.size());
	}
	
	@Test
	public void testInsert() {
		Bookmark bookmark = new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		List<Bookmark> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(1, listaBookmarks.size());
	}
	
	@Test
	public void testDelete() {
		Bookmark bookmark = new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		
		List<Bookmark> listaBookmarks = bookmarkBC.findAll();
		assertNotNull(listaBookmarks);
		assertEquals(1, listaBookmarks.size());
		
		bookmarkBC.delete(bookmark.getId());
		listaBookmarks = bookmarkBC.findAll();
		assertEquals(0, listaBookmarks.size());
	}
	@Test
	public void testUpdate() {
		Bookmark bookmark = new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		bookmarkBC.insert(bookmark);
		
		List<Bookmark> listaBookmarks = bookmarkBC.findAll();
		Bookmark bookmark2 = (Bookmark)listaBookmarks.get(0);
		assertNotNull(listaBookmarks);
		assertEquals("Demoiselle Portal", bookmark2.getDescription());
		
		bookmark2.setDescription("Demoiselle Portal alterado");
		bookmarkBC.update(bookmark2);
		
		listaBookmarks = bookmarkBC.findAll();
		Bookmark bookmark3 = (Bookmark)listaBookmarks.get(0);
		assertEquals("Demoiselle Portal alterado", bookmark3.getDescription());
	}
	
}
