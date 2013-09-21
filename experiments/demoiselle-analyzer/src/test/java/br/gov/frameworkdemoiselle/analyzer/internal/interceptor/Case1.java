package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

import br.gov.frameworkdemoiselle.analyzer.Analyze;

@SuppressWarnings("unused")
public class Case1 extends Case1SuperClass {

	private String nonInitializedStringFrom;

	private String initializedStringFrom = "Initialized String";

	private int nonInitializedPrimitiveIntegerFrom;

	private int initializedPrimitiveIntegerFrom = 1001;

	private Long nonInitializedWrappedLongFrom;

	private Long initializedWrappedLongFrom = Long.valueOf(1002);

	private Class<?> nonInitializedClassFrom;

	private Class<?> initializedClassFrom = Object.class;

	@Analyze
	public void go() {
	}
}
