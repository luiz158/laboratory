package br.org.frameworkdemoiselle.contas.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.frameworkdemoiselle.contas.business.CompromissoBC;
import br.org.frameworkdemoiselle.contas.domain.Compromisso;

@ViewController
@PreviousView("/compromisso_list.xhtml")
public class CompromissoEditMB extends AbstractEditPageBean<Compromisso, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompromissoBC compromissoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.compromissoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.compromissoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.compromissoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.compromissoBC.load(getId()));
	}

}