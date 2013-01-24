package br.gov.demoiselle.escola.config;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix="framework.demoiselle.persistence.jdbc")
public class JDBCConfig {

	private String jndi;
	
	public String getJndi() {
		return jndi;
	}
	
}
