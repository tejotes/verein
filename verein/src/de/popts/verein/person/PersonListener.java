package de.popts.verein.person;

public interface PersonListener {

	public void personAngelegt(Person person);
	
	public void personGeloescht(Person person);
	
	public void personGeaendert(Person person);
	
}
