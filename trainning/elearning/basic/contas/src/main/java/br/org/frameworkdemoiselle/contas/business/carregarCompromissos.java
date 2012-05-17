package br.org.frameworkdemoiselle.contas.business;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class carregarCompromissos {
	
	@Inject
	private Logger logger;
	
	@Inject
	private CompromissoBC compromissoBC;
	
	@Startup
	public void iniciar(){
		logger.info("Iniciando ...");
		compromissoBC.carregarCompromissos();	
		
	}

}
