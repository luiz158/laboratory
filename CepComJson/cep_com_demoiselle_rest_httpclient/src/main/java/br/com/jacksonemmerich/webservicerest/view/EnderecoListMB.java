package br.com.jacksonemmerich.webservicerest.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.com.jacksonemmerich.webservicerest.business.EnderecoBC;
import br.com.jacksonemmerich.webservicerest.domain.Endereco;

@ViewController
@NextView("./endereco_edit.jsf")
@PreviousView("./endereco_list.jsf")
public class EnderecoListMB extends AbstractListPageBean<Endereco, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoBC enderecoBC;
	
	@Override
	protected List<Endereco> handleResultList() {
		return this.enderecoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				enderecoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}