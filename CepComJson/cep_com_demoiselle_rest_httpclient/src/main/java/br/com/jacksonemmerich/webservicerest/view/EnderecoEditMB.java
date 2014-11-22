
package br.com.jacksonemmerich.webservicerest.view;

import javax.inject.Inject;

import br.com.jacksonemmerich.webservicerest.business.EnderecoBC;
import br.com.jacksonemmerich.webservicerest.domain.Endereco;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

// To remove unused imports press: Ctrl+Shift+o

@ViewController
@PreviousView("./endereco_list.jsf")
public class EnderecoEditMB extends AbstractEditPageBean<Endereco, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoBC enderecoBC;
	
	
	// busca o cep do enderecoDAO
	public void buscaEnderecoCep(){
		String cep = this.getBean().getCep();
		if(cep.length() == 8) setBean(enderecoBC.getBuscaEndPorCEP(this.getBean().getCep()));

	}

	
	@Override
	@Transactional
	public String delete() {
		this.enderecoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.enderecoBC.insert(this.getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.enderecoBC.update(this.getBean());
		return getPreviousView();
	}
	
	@Override
	protected Endereco handleLoad(Long id) {
		return this.enderecoBC.load(id);
	}	
}