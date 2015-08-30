package de.popts.verein.person.memory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.dm.annotation.api.Start;
import org.apache.felix.dm.annotation.api.Stop;
import org.osgi.framework.ServiceReference;

import de.popts.verein.person.api.Person;
import de.popts.verein.person.api.PersonService;
import de.popts.verein.person.api.PersonException;
import de.popts.verein.person.api.PersonListener;
import de.popts.verein.person.api.PersonListenerService;

@Component
public class PersonServiceMemoryImpl implements PersonService {

	@ServiceDependency
	private volatile PersonListenerService listenerApi;

	@Override
	public Person personAnlegen(Person person) throws Exception {
		// check params
		if (person == null) {
			throw new PersonException("person == null");
		}

		// generate oid
		String newOid = generateOid();
		person.setOid(newOid);

		// put clone in map
		personStore.put(newOid, new Person(person));

		// notify listeners
		listenerApi.personAngelegt(person);

		// return person
		return person;
	}

	@Override
	public void personLoeschen(Person person) throws Exception {
		// check params
		if (person == null) {
			throw new PersonException("person == null");
		}

		// check oid
		String oid = person.getOid();
		if (oid == null || oid.isEmpty()) {
			String message = String.format("oid(%) invalid", oid);
			throw new PersonException(message);
		}

		// remove from map
		if (personStore.remove(oid) == null) {
			String message = String.format("oid(%) unknown", oid);
			throw new PersonException(message);
		}

		// notify listeners
		listenerApi.personGeloescht(person);
	}

	@Override
	public Person personAendern(Person person) throws Exception {
		// check params
		if (person == null) {
			throw new PersonException("person == null");
		}

		// check oid
		String oid = person.getOid();
		if (oid == null || oid.isEmpty()) {
			String message = String.format("oid(%) invalid", oid);
			throw new PersonException(message);
		}

		// check existence
		if (!personStore.containsKey(oid)) {
			String message = String.format("oid(%) unknown", oid);
			throw new PersonException(message);
		}

		// replace
		personStore.put(oid, person);

		// notify listeners
		listenerApi.personGeaendert(person);

		// return result
		return person;

	}

	private Map<String, Person> personStore = new HashMap<>(101);

	private String generateOid() {
		return UUID.randomUUID().toString();
	}

	@Override
	public List<Person> listAll() {
		// declare result
		List<Person> personList = new ArrayList<>(personStore.size());

		// copy store
		for (Person person : personStore.values()) {
			personList.add(new Person(person));
		}

		// return result
		return personList;
	}

	@Override
	public List<Person> list4Jahr(int jahr) {
		// declare result
		List<Person> personList = new ArrayList<>(personStore.size());

		// copy store
		for (Person person : personStore.values()) {
			// check geburtsDatum as calendar
			Date geburtsDatum = person.getGeburtsDatum();
			if (geburtsDatum == null) {
				continue;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(geburtsDatum);

			// check jahrgang
			if (cal.get(Calendar.YEAR) == jahr) {
				personList.add(new Person(person));
			}
		}

		// return result
		return personList;
	}

	@Override
	public Person get4oid(String oid) {
		// check params
		if (oid == null) {
			return null;
		}

		// get result
		Person person = personStore.get(oid);
		person = (person != null) ? new Person(person) : null;

		// return result
		return person;
	}

	@Start
	public void start() {
		System.out.println("PersonApiMemoryImpl.start()");
	}

	@Stop
	public void stop() {
		System.out.println("PersonApiMemoryImpl.stop()");
	}
}
