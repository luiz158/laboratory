package br.com.jacksonemmerich.webservicerest.business;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jacksonemmerich.webservicerest.domain.Endereco;
import br.com.jacksonemmerich.webservicerest.persistence.EnderecoDAO;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;


@RunWith(DemoiselleRunner.class)
public class TestWebServiceRest {
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	
	@Test
	public void testeWebService(){
		
		Endereco endereco = enderecoDAO.getBuscaEndPorCEP("76812176");
		System.out.println(endereco.getLogradouro());
	}
	
}
