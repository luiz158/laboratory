package br.gov.serpro.auction5.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(severity = SeverityType.ERROR)
public class ApplicationRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ApplicationRuntimeException(String message) {
		super(message);
	}
}