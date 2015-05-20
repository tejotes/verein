package de.popts.verein.person.tester;

import java.util.Date;
import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.dm.annotation.api.Start;
import org.apache.felix.dm.annotation.api.Stop;

import de.popts.verein.person.Person;
import de.popts.verein.person.PersonApi;
import de.popts.verein.person.PersonException;
import de.popts.verein.person.PersonListener;

@Component
public class PersonTester implements PersonListener {

	@ServiceDependency
	private volatile PersonApi personApi;
	
	@Start
	public void activate() {

		System.out.println("PersonTester.start()");
		long startMillis = System.currentTimeMillis();
		
		// create some persons
		try {
			Person person = null;
			
			// add Thomas
			person = new Person();
			person.setName("Thomas");
			personApi.personAnlegen(person);
			
			// add Stefanie
			person = new Person();
			person.setName("Stefanie");
			personApi.personAnlegen(person);
			
			// add Annette
			person = new Person();
			person.setName("Annette");
			personApi.personAnlegen(person);
			
			// add Philipp
			person = new Person();
			person.setName("Philipp");
			personApi.personAnlegen(person);
			
			// list persons
			List<Person> personList = personApi.listAll();
			for (Person currentPerson : personList) {
				System.out.println(currentPerson);
			}
			
			if (personList.size() > 0) {
				Person person0 = personList.get(0);
				person0.setGeburtsDatum(new Date());
				personApi.personAendern(person0);
			}
		} catch (PersonException e) {
			e.printStackTrace();
		}

		
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: "+durationMillis+"[ms].");
	}

	@Stop
	public void stop() {
		// log
		System.out.println("PersonTester.stop()");
		
		// remove all Persons
		List<Person> personList = personApi.listAll();
		for (Person person : personList) {
			try {
				personApi.personLoeschen(person);
			} catch (PersonException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void personAngelegt(Person person) {
		System.out.println("Person angelegt: " + person);
	}

	@Override
	public void personGeloescht(Person person) {
		System.out.println("Person geloescht: " + person);
	}

	@Override
	public void personGeaendert(Person person) {
		System.out.println("Person geändert: " + person);
	}


	
	
}
