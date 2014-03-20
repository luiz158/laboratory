package br.gov.frameworkdemoiselle.resteasy.internal.implementation;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "frameworkdemoiselle.rest.")
public class RestConfig {

	@Name("date.format")
	private String dateFormat = "yyyy-MM-dd";

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
}
