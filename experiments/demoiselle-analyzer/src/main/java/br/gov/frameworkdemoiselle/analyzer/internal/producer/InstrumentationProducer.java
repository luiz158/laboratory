package br.gov.frameworkdemoiselle.analyzer.internal.producer;

import java.lang.instrument.Instrumentation;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

public class InstrumentationProducer {

	private static Instrumentation instrumentation;

	public static void premain(String agentArgs, Instrumentation instrumentation) {
		InstrumentationProducer.instrumentation = instrumentation;
	}

	@Default
	@Produces
	public Instrumentation create() {
		if (instrumentation == null) {
			throw new IllegalStateException("faltando: -javaassist: bla bla bla"); 
		}

		return instrumentation;
	}
}
