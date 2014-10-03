package org.demoiselle.drails.exceptions;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidationException() {
    }

    public ValidationException(String string) {
        super(string);
    }

    public ValidationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ValidationException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ValidationException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
    
    
}
