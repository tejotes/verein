package de.popts.verein.rollentyp;

public enum RollenArt {

	ANY("ANY"), 
	NONE("NONE"), 
	MITGLIED("MITGLIED"), 
	FUNKTIONArt("FUNKTION"), 
	EHRUNG("EHRUNG");

	private final String text;

	private RollenArt(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
