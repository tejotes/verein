package de.popts.verein.rollentyp;

public class RollenTypException extends Exception {
	
	public RollenTypException(String message) {
		super(message);
	}
	
	public RollenTypException() {
		super();
	}
	
	public RollenTypException(Throwable cause) {
		super(cause);
	}

	public RollenTypException(String message, Throwable cause) {
		super(message, cause);
	}

}
