package br.gov.frameworkdemoiselle.transacaojta.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transacaojta.entity.Pessoa;
import br.gov.frameworkdemoiselle.transacaojta.persistence.PessoaDAO;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class PessoaBC extends DelegateCrud<Pessoa, String, PessoaDAO> {
	
	@Transactional
	public void insert(Pessoa bean1, Pessoa bean2){
		super.insert(bean1);
		super.insert(bean2);
	}
	
}
