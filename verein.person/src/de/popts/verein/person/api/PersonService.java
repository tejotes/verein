package de.popts.verein.person.api;

import java.util.List;

public interface PersonService {

	public Person personAnlegen(Person person) throws Exception;
	
	public void personLoeschen(Person person) throws Exception;
	
	public Person personAendern(Person person) throws Exception;
	
	public List<Person> listAll();
	
	public List<Person> list4Jahr(int jahr);
	
	public Person get4oid(String oid);
	
}
