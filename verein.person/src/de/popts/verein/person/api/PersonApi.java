package de.popts.verein.person.api;

import java.util.List;

public interface PersonApi {

	public Person personAnlegen(Person person) throws PersonException;
	
	public void personLoeschen(Person person) throws PersonException;
	
	public Person personAendern(Person person) throws PersonException;
	
	public List<Person> listAll();
	
	public List<Person> list4Jahr(int jahr);
	
	public Person get4oid(String oid);
	
}
