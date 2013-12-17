

package br.gov.serpro.lab.estacionamento.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.lab.estacionamento.domain.Cliente;
import br.gov.serpro.lab.estacionamento.business.ClienteBC;

@RunWith(DemoiselleRunner.class)
public class ClienteBCTest {

    @Inject
	private ClienteBC clienteBC;
	
	@Before
	public void before() {
		for (Cliente cliente : clienteBC.findAll()) {
			clienteBC.delete(cliente.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		// modifique para inserir dados conforme o construtor
		Cliente cliente = new Cliente("nome","cpf","email","tituloEleitor","documento","telefone",null,null);
		clienteBC.insert(cliente);
		List<Cliente> listOfCliente = clienteBC.findAll();
		assertNotNull(listOfCliente);
		assertEquals(1, listOfCliente.size());
	}	
	
	@Test
	public void testDelete() {
		
		// modifique para inserir dados conforme o construtor
		Cliente cliente = new Cliente("nome","cpf","email","tituloEleitor","documento","telefone",null,null);
		clienteBC.insert(cliente);
		
		List<Cliente> listOfCliente = clienteBC.findAll();
		assertNotNull(listOfCliente);
		assertEquals(1, listOfCliente.size());
		
		clienteBC.delete(cliente.getId());
		listOfCliente = clienteBC.findAll();
		assertEquals(0, listOfCliente.size());
	}
	
	@Test
	public void testUpdate() {
		// modifique para inserir dados conforme o construtor
		Cliente cliente = new Cliente("nome","cpf","email","tituloEleitor","documento","telefone",null,null);
		clienteBC.insert(cliente);
		
		List<Cliente> listOfCliente = clienteBC.findAll();
		Cliente cliente2 = (Cliente)listOfCliente.get(0);
		assertNotNull(listOfCliente);

		// alterar para tratar uma propriedade existente na Entidade Cliente
		// cliente2.setUmaPropriedade("novo valor");
		clienteBC.update(cliente2);
		
		listOfCliente = clienteBC.findAll();
		Cliente cliente3 = (Cliente)listOfCliente.get(0);
		
		// alterar para tratar uma propriedade existente na Entidade Cliente
		// assertEquals("novo valor", cliente3.getUmaPropriedade());
	}

}