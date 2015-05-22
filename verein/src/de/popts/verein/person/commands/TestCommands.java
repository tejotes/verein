package de.popts.verein.person.commands;

import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.person.Person;
import de.popts.verein.person.PersonApi;
import de.popts.verein.person.PersonException;

@Component(properties = {
		@Property(name = CommandProcessor.COMMAND_SCOPE, value = "person"),
		@Property(name = CommandProcessor.COMMAND_FUNCTION, value = {"show", "showyear", "store", "remove"}) 
}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile PersonApi personApi;

	public void store(String vorname, String name) throws PersonException {
		long startMillis = System.currentTimeMillis();
		Person person = new Person();
		person.setName(name);
		person.setVorname(vorname);

		personApi.personAnlegen(person);
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}

	public void show() {
		long startMillis = System.currentTimeMillis();
		List<Person> personList = personApi.listAll();

		for (Person person : personList) {
			System.out.println("person: " + person);
		}
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}

	public void show(String oid) {
		long startMillis = System.currentTimeMillis();
		Person person = personApi.get4oid(oid);
		System.out.println("person: " + person);
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}

	public void showyear(String yearString) {
		long startMillis = System.currentTimeMillis();
		int year = Integer.parseInt(yearString);
		List<Person> personList = personApi.list4Jahr(year);

		for (Person person : personList) {
			System.out.println("person: " + person);
		}
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}

	public void remove(String oid) throws PersonException {
		long startMillis = System.currentTimeMillis();
		Person person = personApi.get4oid(oid);
		personApi.personLoeschen(person);
		System.out.println("removed: " + person);
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}

}
