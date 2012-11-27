package br.gov.frameworkdemoiselle.internal.configuration;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "frameworkdemoiselle.security")
public class JAASConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginModuleName;

	public String getLoginModuleName() {
		return loginModuleName;
	}

	public void setLoginModuleName(String loginModuleName) {
		this.loginModuleName = loginModuleName;
	}
}
