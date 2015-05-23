package de.popts.verein.person.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.osgi.framework.ServiceReference;

import de.popts.verein.person.Person;
import de.popts.verein.person.PersonListener;
import de.popts.verein.person.PersonListenerApi;

@Component
public class PersonListenerApiImpl implements PersonListenerApi {
	
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
