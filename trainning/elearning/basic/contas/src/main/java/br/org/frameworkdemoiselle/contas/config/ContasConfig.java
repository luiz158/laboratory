package br.org.frameworkdemoiselle.contas.config;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "contas")
public class ContasConfig {
	
	@Name("valor.minimo")
	private Long valorMinimo; 
	
	public Long getValorMinimo(){
		return valorMinimo;
	}

}
