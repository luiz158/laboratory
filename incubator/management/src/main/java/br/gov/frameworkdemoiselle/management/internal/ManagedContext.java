package br.gov.frameworkdemoiselle.management.internal;

import javax.enterprise.context.RequestScoped;

import br.gov.frameworkdemoiselle.internal.context.ThreadLocalContext;


public class ManagedContext extends ThreadLocalContext {
	
	/**
	 * Constructs a new context. This context is disabled when created.
	 */
	public ManagedContext() {
		super(RequestScoped.class);
	}

}
