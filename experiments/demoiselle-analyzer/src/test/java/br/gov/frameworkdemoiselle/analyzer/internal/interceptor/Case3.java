package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import br.gov.frameworkdemoiselle.analyzer.Analyze;

@SuppressWarnings("unused")
public class Case3 {

	private Case3AttributeA attributeA;

	private Case3AttributeB attributeB;

	public Case3() {
		Case3AttributeA instanceA = new Case3AttributeA();
		Case3AttributeB instanceB = new Case3AttributeB();

		instanceA.setOhter(instanceB);
		instanceB.setOhter(instanceA);

		this.attributeA = instanceA;
		this.attributeB = instanceB;
	}

	@Analyze
	public void go() {
	}
}
