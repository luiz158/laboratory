package br.gov.frameworkdemoiselle.exceptionhandler.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback=true, severity=SeverityType.INFO)
public class CPFDuplicadoException extends RuntimeException{
	
	public CPFDuplicadoException (String message) {
		super(message);
	}
}
