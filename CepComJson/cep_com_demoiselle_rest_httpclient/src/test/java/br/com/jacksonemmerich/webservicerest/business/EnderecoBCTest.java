

package br.com.jacksonemmerich.webservicerest.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.com.jacksonemmerich.webservicerest.domain.Endereco;
import br.com.jacksonemmerich.webservicerest.business.EnderecoBC;

@RunWith(DemoiselleRunner.class)
public class EnderecoBCTest {

    @Inject
	private EnderecoBC enderecoBC;
	
	@Before
	public void before() {
		for (Endereco endereco : enderecoBC.findAll()) {
			enderecoBC.delete(endereco.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		// modifique para inserir dados conforme o construtor
		Endereco endereco = new Endereco();
		enderecoBC.insert(endereco);
		List<Endereco> listOfEndereco = enderecoBC.findAll(); 
		assertNotNull(listOfEndereco);
		assertEquals(1, listOfEndereco.size());
	}	
	
	@Test
	public void testDelete() {
		
		// modifique para inserir dados conforme o construtor
		Endereco endereco = new Endereco();
		enderecoBC.insert(endereco);
		
		List<Endereco> listOfEndereco = enderecoBC.findAll();
		assertNotNull(listOfEndereco);
		assertEquals(1, listOfEndereco.size());
		
		enderecoBC.delete(endereco.getId());
		listOfEndereco = enderecoBC.findAll();
		assertEquals(0, listOfEndereco.size());
	}
	
	@Test
	public void testUpdate() {
		// modifique para inserir dados conforme o construtor
		Endereco endereco = new Endereco();
		enderecoBC.insert(endereco);
		
		List<Endereco> listOfEndereco = enderecoBC.findAll();
		Endereco endereco2 = (Endereco)listOfEndereco.get(0);
		assertNotNull(listOfEndereco);

		// alterar para tratar uma propriedade existente na Entidade Endereco
		// endereco2.setUmaPropriedade("novo valor");
		enderecoBC.update(endereco2);
		
		listOfEndereco = enderecoBC.findAll();
		Endereco endereco3 = (Endereco)listOfEndereco.get(0);
		
		// alterar para tratar uma propriedade existente na Entidade Endereco
		// assertEquals("novo valor", endereco3.getUmaPropriedade());
	}

}