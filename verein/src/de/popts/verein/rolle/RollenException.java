package de.popts.verein.rolle;

public class RollenException extends Exception {
	
	public RollenException(String message) {
		super(message);
	}
	
	public RollenException() {
		super();
	}
	
	public RollenException(Throwable cause) {
		super(cause);
	}

	public RollenException(String message, Throwable cause) {
		super(message, cause);
	}

}
