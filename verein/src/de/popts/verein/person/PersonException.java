package de.popts.verein.person;

public class PersonException extends Exception {

	private static final long serialVersionUID = -4561837503726524649L;

	public PersonException(String message) {
		super(message);
	}
	
	public PersonException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PersonException(Throwable cause) {
		super(cause);
	}
	
}
