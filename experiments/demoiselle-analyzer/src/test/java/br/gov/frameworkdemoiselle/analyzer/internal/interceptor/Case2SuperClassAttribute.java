package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

@SuppressWarnings("unused")
public class Case2SuperClassAttribute {

	private String initializedString = "Case 2 Initialized String SuperClass Attribute";

	private Case2SuperClassNestedAttribute nonInitializedNestedAttribute;

	private Case2SuperClassNestedAttribute initializedNestedAttribute = new Case2SuperClassNestedAttribute();
}
