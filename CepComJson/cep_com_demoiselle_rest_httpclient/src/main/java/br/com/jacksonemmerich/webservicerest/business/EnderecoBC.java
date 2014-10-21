
package br.com.jacksonemmerich.webservicerest.business;

import br.com.jacksonemmerich.webservicerest.domain.Endereco;
import br.com.jacksonemmerich.webservicerest.persistence.EnderecoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

// To remove unused imports press: Ctrl+Shift+o

@BusinessController
public class EnderecoBC extends DelegateCrud<Endereco, Long, EnderecoDAO> {
	private static final long serialVersionUID = 1L;
	
	
	
	
	// delega pro DAO o metodo que busca o endereço
	public Endereco getBuscaEndPorCEP(String cep) {
		return getDelegate().getBuscaEndPorCEP(cep);
	}
	
	
	
}
