package br.org.frameworkdemoiselle.contas.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.frameworkdemoiselle.contas.business.CompromissoBC;
import br.org.frameworkdemoiselle.contas.domain.Compromisso;

@ViewController
@NextView("/compromisso_edit.xhtml")
@PreviousView("/compromisso_list.xhtml")
public class CompromissoListMB extends AbstractListPageBean<Compromisso, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CompromissoBC compromissoBC;
	
	@Override
	protected List<Compromisso> handleResultList() {
		return this.compromissoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				compromissoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}