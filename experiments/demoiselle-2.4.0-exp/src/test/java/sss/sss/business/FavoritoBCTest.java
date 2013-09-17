package sss.sss.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import exp.business.FavoritoBC;
import exp.entity.Favorito;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class FavoritoBCTest {

	@Inject
	private FavoritoBC favoritoBC;
	
	@Before
	public void before() {
		for (Favorito favorito : favoritoBC.findAll()) {
			favoritoBC.delete(favorito.getId());
		}
	}

	@Test
	public void testLoad() {
		favoritoBC.load();
		List<Favorito> listaFavoritos = favoritoBC.findAll();
		assertNotNull(listaFavoritos);
		assertEquals(10, listaFavoritos.size());
	}
	
	@Test
	public void testInsert() {
		Favorito favorito = new Favorito("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		favoritoBC.insert(favorito);
		List<Favorito> listaFavoritos = favoritoBC.findAll();
		assertNotNull(listaFavoritos);
		assertEquals(1, listaFavoritos.size());
	}
	
	@Test
	public void testDelete() {
		Favorito favorito = new Favorito("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		favoritoBC.insert(favorito);
		
		List<Favorito> listaFavoritos = favoritoBC.findAll();
		assertNotNull(listaFavoritos);
		assertEquals(1, listaFavoritos.size());
		
		favoritoBC.delete(favorito.getId());
		listaFavoritos = favoritoBC.findAll();
		assertEquals(0, listaFavoritos.size());
	}
	@Test
	public void testUpdate() {
		Favorito favorito = new Favorito("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br");
		favoritoBC.insert(favorito);
		
		List<Favorito> listaFavoritos = favoritoBC.findAll();
		Favorito favorito2 = (Favorito)listaFavoritos.get(0);
		assertNotNull(listaFavoritos);
		assertEquals("Demoiselle Portal", favorito2.getDescricao());
		
		favorito2.setDescricao("Demoiselle Portal alterado");
		favoritoBC.update(favorito2);
		
		listaFavoritos = favoritoBC.findAll();
		Favorito favorito3 = (Favorito)listaFavoritos.get(0);
		assertEquals("Demoiselle Portal alterado", favorito3.getDescricao());
	}
	
}