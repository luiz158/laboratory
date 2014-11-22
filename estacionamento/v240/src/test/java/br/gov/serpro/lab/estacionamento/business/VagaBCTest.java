package br.gov.serpro.lab.estacionamento.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.lab.estacionamento.domain.Vaga;
import br.gov.serpro.lab.estacionamento.business.VagaBC;

@RunWith(DemoiselleRunner.class)
public class VagaBCTest {

    @Inject
	private VagaBC vagaBC;
	
	@Before
	public void before() {
		for (Vaga vaga : vagaBC.findAll()) {
			vagaBC.delete(vaga.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		Vaga vaga = new Vaga(true, "porte", null ,new Date(),new Date(), null);
		vagaBC.insert(vaga);
		List<Vaga> listOfVaga = vagaBC.findAll();
		assertNotNull(listOfVaga);
		assertEquals(1, listOfVaga.size());
	}	
	
	@Test
	public void testDelete() {
		
		Vaga vaga = new Vaga(true,"porte",null,new Date(),new Date(),null);
		vagaBC.insert(vaga);
		
		List<Vaga> listOfVaga = vagaBC.findAll();
		assertNotNull(listOfVaga);
		assertEquals(1, listOfVaga.size());
		
		vagaBC.delete(vaga.getId());
		listOfVaga = vagaBC.findAll();
		assertEquals(0, listOfVaga.size());
	}
	
	@Test
	public void testUpdate() {

		Vaga vaga = new Vaga(true,"porte",null,new Date(),new Date(),null);
		vagaBC.insert(vaga);
		
		List<Vaga> listOfVaga = vagaBC.findAll();
		Vaga vaga2 = (Vaga)listOfVaga.get(0);
		assertNotNull(listOfVaga);

		vaga2.setPorte("novo valor");
		vagaBC.update(vaga2);
		
		listOfVaga = vagaBC.findAll();
		Vaga vaga3 = (Vaga)listOfVaga.get(0);
		
		assertEquals("novo valor", vaga3.getPorte());
	}

}