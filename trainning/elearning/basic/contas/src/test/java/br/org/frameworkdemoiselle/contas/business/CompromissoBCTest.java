package br.org.frameworkdemoiselle.contas.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.org.frameworkdemoiselle.contas.domain.Compromisso;
import br.org.frameworkdemoiselle.contas.exception.CompromissoException;

@RunWith(DemoiselleRunner.class)
public class CompromissoBCTest {
	
	@Inject 
	CompromissoBC compromissoBC;

	
	@Before
	public void before() {
		for (Compromisso compromisso : compromissoBC.findAll()) {
			compromissoBC.delete(compromisso.getId());
		}
	}
	
	@Test
	public void testInsert() {
		Compromisso compromisso = new Compromisso ("Conta Luz", new Date(), null, new Long(11), null) ;
		compromissoBC.insert(compromisso);
		List<Compromisso> listaCompromissos = compromissoBC.findAll(); 
		assertNotNull(listaCompromissos);
		assertEquals(1, listaCompromissos.size());
	}
	@Test
	public void testDelete() {		
		Compromisso compromisso = new Compromisso ("Conta Luz", new Date(), null, new Long(11), null) ;
		compromissoBC.insert(compromisso);
		List<Compromisso> listaCompromissos = compromissoBC.findAll(); 
		assertNotNull(listaCompromissos);
		assertEquals(1, listaCompromissos.size());
		compromissoBC.delete(compromisso.getId());
		listaCompromissos = compromissoBC.findAll();
		assertEquals(0, listaCompromissos.size());
		
	}
	
	@Test
	public void testUpdate() {
		Compromisso compromisso = new Compromisso ("Conta Luz", new Date(), null, new Long(11), null) ;
		compromissoBC.insert(compromisso);
		List<Compromisso> listaCompromissos = compromissoBC.findAll();
		
		Compromisso compromisso2 = (Compromisso)listaCompromissos.get(0);
		assertNotNull(listaCompromissos);
		assertEquals("Conta Luz", compromisso2.getNomeCompromisso());
		
		compromisso2.setNomeCompromisso("Conta Luz alterado");
		compromisso2.setValorCompromisso(new Long(12));
		compromissoBC.update(compromisso2);
		
		listaCompromissos = compromissoBC.findAll();
		Compromisso compromisso3 = (Compromisso)listaCompromissos.get(0);
		assertEquals("Conta Luz alterado", compromisso3.getNomeCompromisso());
	}
	
	@Test(expected = CompromissoException.class)
	public void falhaCompromissoDuplicado() {
		compromissoBC.insert(new Compromisso ("Conta Luz", new Date(), null, new Long(11), null)) ;
		compromissoBC.insert(new Compromisso ("Conta Luz", new Date(), null, new Long(11), null)) ;
		
	}
	
	@Test(expected = CompromissoException.class)
	public void falhaValorCompromissoMenor() {
		compromissoBC.insert(new Compromisso ("Conta Luz", new Date(), null, new Long(9), null)) ;		
	}
	
	@Test
	public void falhaCompromissoAtrasado() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dataCompromisso = format.parse("28/12/2011");
		
		compromissoBC.insert(new Compromisso ("Conta Luz", dataCompromisso, new Date(), new Long(11), null)) ;
		List<Compromisso> listaCompromissos = compromissoBC.findAll();
		
		Compromisso compromisso = (Compromisso)listaCompromissos.get(0);
		assertNotNull(listaCompromissos);
		assertEquals(true, compromisso.isAtrasado());
	}	
	
	@Test
	public void testCarregar() {
		compromissoBC.carregarCompromissos();
		List<Compromisso> listaCompromissos = compromissoBC.findAll();
		assertNotNull(listaCompromissos);
		assertEquals(2, listaCompromissos.size());
	}

}
