package de.popts.verein.person.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.osgi.framework.ServiceReference;

import de.popts.verein.person.api.Person;
import de.popts.verein.person.api.PersonListener;
import de.popts.verein.person.api.PersonListenerService;

@Component
public class PersonListenerServiceImpl implements PersonListenerService {
	
	final private Map<ServiceReference, PersonListener> listenerMap = new ConcurrentHashMap<>();

	@ServiceDependency(removed = "listenerRemoved", required = false)
	public void listenerAdded(ServiceReference ref, PersonListener listener) {
		listenerMap.put(ref, listener);
		System.out.println("listener added: " + listener);
	}

	public void listenerRemoved(ServiceReference ref) {
		listenerMap.remove(ref);
		System.out.println("listener removed: " + ref);
	}

	@Override
	public void personAngelegt(Person person) {
		for (PersonListener listener : listenerMap.values()) {
			listener.personAngelegt(person);
		}
	}

	@Override
	public void personGeloescht(Person person) {
		for (PersonListener listener : listenerMap.values()) {
			listener.personGeloescht(person);
		}
	}

	@Override
	public void personGeaendert(Person person) {
		for (PersonListener listener : listenerMap.values()) {
			listener.personGeaendert(person);
		}
	}

}
