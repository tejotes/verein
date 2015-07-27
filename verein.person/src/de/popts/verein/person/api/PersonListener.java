package de.popts.verein.person.api;

public interface PersonListener {

	public void personAngelegt(Person person);
	
	public void personGeloescht(Person person);
	
	public void personGeaendert(Person person);
	
}
