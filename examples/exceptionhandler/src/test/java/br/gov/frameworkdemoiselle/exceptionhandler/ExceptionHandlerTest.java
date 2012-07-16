package br.gov.frameworkdemoiselle.exceptionhandler;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.persistence.RollbackException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.exceptionhandler.business.PessoaBC;
import br.gov.frameworkdemoiselle.exceptionhandler.entity.Pessoa;
import br.gov.frameworkdemoiselle.exceptionhandler.exception.CPFDuplicadoException;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class ExceptionHandlerTest {

	@Inject 
	private PessoaBC pessoaBC;

	@Before
	public void limparBase() {
		for (Pessoa p : pessoaBC.findAll()){
			pessoaBC.delete(p.getCpf());
		}
	}
	
//	@Test
//	public void inserirUmaPessoaComSucesso() {
//		pessoaBC.insert(new Pessoa("12345678910", "João"));
//		assertEquals(1, pessoaBC.findAll().size());
//	}
//	
//	@Test
//	public void inserirPessoasComSucesso() {
//		pessoaBC.insert(new Pessoa("12345678910", "João"), new Pessoa("12345678911", "Maria"));
//		assertEquals(2, pessoaBC.findAll().size());
//	}
	
	@Test(expected=CPFDuplicadoException.class)
	public void inserirPessoasComCPFIguaisComRollbackImplicito() {
		pessoaBC.insert(new Pessoa("12345678910", "João"), new Pessoa("12345678910", "Maria"));
		Assert.fail();
	}

//	@Test
//	public void inserirPessoasComCPFIguaisComRollbackExplicito() {
//		try {
//			pessoaBC.insert(new Pessoa("12345678910", "João"), new Pessoa("12345678910", "Maria"));
//			Assert.fail();
//		} catch(CPFDuplicadoException e) {
//			assertEquals(0, pessoaBC.findAll().size());
//		}
//		
//	}
//	
//	@Test(expected=RollbackException.class)
//	public void inserirPessoa() {
//		pessoaBC.insert(new Pessoa("12345678910", "João"));
//		pessoaBC.insert(new Pessoa("12345678910", "Maria"));
//		Assert.fail();
//	}
	

	
}

