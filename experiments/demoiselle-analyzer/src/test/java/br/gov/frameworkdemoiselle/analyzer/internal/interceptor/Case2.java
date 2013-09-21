package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import br.gov.frameworkdemoiselle.analyzer.Analyze;

@SuppressWarnings("unused")
public class Case2 extends Case2SuperClass {

	private Case2Attribute nonInitializedAttribute;

	private Case2Attribute initializedAttribute = new Case2Attribute();

	@Analyze
	public void go() {
	}
}
