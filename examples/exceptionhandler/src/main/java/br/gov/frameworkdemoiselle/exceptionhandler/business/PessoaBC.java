package br.gov.frameworkdemoiselle.exceptionhandler.business;

import javax.persistence.RollbackException;

import br.gov.frameworkdemoiselle.DemoiselleException;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.exceptionhandler.entity.Pessoa;
import br.gov.frameworkdemoiselle.exceptionhandler.exception.CPFDuplicadoException;
import br.gov.frameworkdemoiselle.exceptionhandler.persistence.PessoaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class PessoaBC extends DelegateCrud<Pessoa, String, PessoaDAO> {

	@Transactional
	public void insert(Pessoa bean1, Pessoa bean2){
		if (bean1.getCpf().equals(bean2.getCpf())) {
			throw new CPFDuplicadoException("########## CPF DUPLICADO EXCEPTION ##########");
		}
		super.insert(bean1);
		super.insert(bean2);
	}
	
	@ExceptionHandler
	public void tratadorDeExcecaoRollback(RollbackException re) { 
		System.out.println("########## ROLLBACK EXCEPTION ##########");
		throw re;
	}
	
	@ExceptionHandler
	public void tratarCPFDuplicado(CPFDuplicadoException cpfe) {
		throw cpfe;
	}
}
