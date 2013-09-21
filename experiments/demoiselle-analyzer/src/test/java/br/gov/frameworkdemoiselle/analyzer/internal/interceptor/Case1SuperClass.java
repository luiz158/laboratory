package br.gov.frameworkdemoiselle.analyzer.internal.interceptor;

@SuppressWarnings("unused")
public class Case1SuperClass {

	private String nonInitializedStringFromSuperClass;

	private String initializedStringFromSuperClass = "SuperClass's Initialized String";

	private int nonInitializedPrimitiveIntegerFromSuperClass;

	private int initializedPrimitiveIntegerFromSuperClass = 2001;

	private Long nonInitializedWrappedLongFromSuperClass;

	private Long initializedWrappedLongFromSuperClass = Long.valueOf(2002);

	private Class<?> nonInitializedClassFromSuperClass;

	private Class<?> initializedClassFromSuperClass = Object.class;

}
