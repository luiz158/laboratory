package br.gov.frameworkdemoiselle.transacaojta.aplication;

import javax.inject.Inject;
import javax.persistence.RollbackException;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.transacaojta.business.PessoaBC;
import br.gov.frameworkdemoiselle.transacaojta.entity.Pessoa;


public class Aplicacao {

	@Inject
	private PessoaBC pessoaBC;
	
	@Startup
	public void start() {
		inserirUmaPessoaComSucesso();
		inserirPessoasComCPFIguais();
	}
	
	public void limparBase() {
		for (Pessoa p : pessoaBC.findAll()){
			pessoaBC.delete(p.getCpf());
		}
	}
	
	public void inserirUmaPessoaComSucesso() {
		limparBase();
		pessoaBC.insert(new Pessoa("12345678910", "João"));
		if (pessoaBC.findAll().size() == 1) 
			System.out.println("#############  Pessoa Inserida com sucesso #############");
	}
	
	public void inserirPessoasComCPFIguais() {
		limparBase();
		try {
			pessoaBC.insert(new Pessoa("12345678910", "João"), new Pessoa("12345678910", "Maria"));
			System.out.println("#############  Erro: Não retornou exceção ao inserir dois registros com mesma chave. #############");
		} catch(Exception e) {
			if (pessoaBC.findAll().size() == 0) 
				System.out.println("#############  Rollback efetuado com sucesso. #############");
		}
	}
	
	
}
