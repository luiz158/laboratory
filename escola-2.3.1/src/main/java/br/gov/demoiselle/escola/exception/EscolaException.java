package br.gov.demoiselle.escola.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.Message;

@ApplicationException
public class EscolaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EscolaException(Message msg){
		super(msg.getText());
	}
	
	public EscolaException(Message msg, Throwable th){
		super(msg.getText(), th);
	}
	
}
