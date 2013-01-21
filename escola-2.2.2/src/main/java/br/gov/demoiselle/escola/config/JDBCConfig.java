package br.gov.demoiselle.escola.config;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix="framework.demoiselle.persistence.jdbc")
public class JDBCConfig {

	private String driver;
	private String url;
	private String user;
	private String pass;
	
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	
}
