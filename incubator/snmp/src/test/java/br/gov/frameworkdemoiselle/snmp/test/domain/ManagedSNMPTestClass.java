package br.gov.frameworkdemoiselle.snmp.test.domain;

import br.gov.frameworkdemoiselle.management.annotation.Managed;
import br.gov.frameworkdemoiselle.management.annotation.Property;

@Managed
public class ManagedSNMPTestClass {
	
	@Property(description="randomProperty")
	private Integer randomProperty;

	
	public Integer getRandomProperty() {
		return randomProperty;
	}

	public void setRandomProperty(Integer randomProperty) {
		this.randomProperty = randomProperty;
	}
	
}
