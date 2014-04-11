package br.gov.serpro.catalogo;

import javax.validation.constraints.NotNull;

import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "catalogo")
public class AppConfig {
	
	@NotNull
	private String url;
	
	private Boolean debug;

	public String getUrl() {
		return url;
	}

	public Boolean isDebug() {
		return debug;
	}
	
	

}
