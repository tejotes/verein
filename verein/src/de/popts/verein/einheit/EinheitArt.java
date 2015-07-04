package de.popts.verein.einheit;

public enum EinheitArt {

	ANY("ANY"),
	NONE("NONE"),
	VEREIN("VEREIN"), 
	ABTEILUNG("ABTEILUNG"), 
	GRUPPE("GRUPPE");

	private final String text;

	private EinheitArt(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
