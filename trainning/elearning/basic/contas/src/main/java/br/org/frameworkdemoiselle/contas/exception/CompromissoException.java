package br.org.frameworkdemoiselle.contas.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;

@ApplicationException(rollback = true)
public class CompromissoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8521334322048036977L;
	
	public CompromissoException(String string) {
		super(string);
	}
	
	public CompromissoException() {
	}

}
