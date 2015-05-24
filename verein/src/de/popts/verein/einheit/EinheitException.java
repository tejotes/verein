package de.popts.verein.einheit;

public class EinheitException extends Exception {
	
	public EinheitException(String message) {
		super(message);
	}
	
	public EinheitException() {
		super();
	}
	
	public EinheitException(Throwable cause) {
		super(cause);
	}

	public EinheitException(String message, Throwable cause) {
		super(message, cause);
	}

}
