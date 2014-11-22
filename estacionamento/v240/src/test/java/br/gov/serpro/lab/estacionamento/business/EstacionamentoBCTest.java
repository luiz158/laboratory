

package br.gov.serpro.lab.estacionamento.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.lab.estacionamento.domain.Estacionamento;
import br.gov.serpro.lab.estacionamento.business.EstacionamentoBC;

@RunWith(DemoiselleRunner.class)
public class EstacionamentoBCTest {

    @Inject
	private EstacionamentoBC estacionamentoBC;
	
	@Before
	public void before() {
		for (Estacionamento estacionamento : estacionamentoBC.findAll()) {
			estacionamentoBC.delete(estacionamento.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		Estacionamento estacionamento = new Estacionamento("nome","58513124000150","0730144900196",null);
		estacionamentoBC.insert(estacionamento);
		List<Estacionamento> listOfEstacionamento = estacionamentoBC.findAll();
		assertNotNull(listOfEstacionamento);
		assertEquals(1, listOfEstacionamento.size());
	}	
	
	@Test
	public void testDelete() {
		
		Estacionamento estacionamento = new Estacionamento("nome","58513124000150","0730144900196",null);
		estacionamentoBC.insert(estacionamento);
		
		List<Estacionamento> listOfEstacionamento = estacionamentoBC.findAll();
		assertNotNull(listOfEstacionamento);
		assertEquals(1, listOfEstacionamento.size());
		
		estacionamentoBC.delete(estacionamento.getId());
		listOfEstacionamento = estacionamentoBC.findAll();
		assertEquals(0, listOfEstacionamento.size());
	}
	
	@Test
	public void testUpdate() {

		Estacionamento estacionamento = new Estacionamento("nome","58513124000150","0730144900196",null);
		estacionamentoBC.insert(estacionamento);
		
		List<Estacionamento> listOfEstacionamento = estacionamentoBC.findAll();
		Estacionamento estacionamento2 = (Estacionamento)listOfEstacionamento.get(0);
		assertNotNull(listOfEstacionamento);

		estacionamento2.setNome("novo valor");
		estacionamentoBC.update(estacionamento2);
		
		listOfEstacionamento = estacionamentoBC.findAll();
		Estacionamento estacionamento3 = (Estacionamento)listOfEstacionamento.get(0);
		
		assertEquals("novo valor", estacionamento3.getNome());
	}

}