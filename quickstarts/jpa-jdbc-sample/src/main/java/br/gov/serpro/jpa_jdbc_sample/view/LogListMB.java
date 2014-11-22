package br.gov.serpro.jpa_jdbc_sample.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.serpro.jpa_jdbc_sample.business.LogBC;
import br.gov.serpro.jpa_jdbc_sample.domain.Log;

@ViewController
public class LogListMB extends AbstractListPageBean<Log, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LogBC bc;

	@Override
	protected List<Log> handleResultList() {
		try {
			return this.bc.findAll();
		} catch (Exception e) {
			throw new DemoiselleException("Erro");
		}
	}

}
