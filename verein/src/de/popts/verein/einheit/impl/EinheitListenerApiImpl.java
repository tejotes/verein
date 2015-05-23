package de.popts.verein.einheit.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.osgi.framework.ServiceReference;

import de.popts.verein.einheit.Einheit;
import de.popts.verein.einheit.EinheitListener;
import de.popts.verein.einheit.EinheitListenerApi;

@Component
public class EinheitListenerApiImpl implements EinheitListenerApi {

	final private Map<ServiceReference, EinheitListener> listenerMap = new ConcurrentHashMap<>();
	
	@ServiceDependency(removed = "listenerRemoved", required = false)
	public void listenerAdded(ServiceReference ref, EinheitListener listener) {
		listenerMap.put(ref, listener);
		System.out.println("EinheitListener added: " + listener);
	}

	public void listenerRemoved(ServiceReference ref) {
		listenerMap.remove(ref);
		System.out.println("EinheitListener removed: " + ref);
	}
	
	@Override
	public void einheitAngelegt(Einheit einheit) {
		// notify all listeners
		for (EinheitListener listener : listenerMap.values()) {
			listener.einheitAngelegt(einheit);
		}
	}

	@Override
	public void einheitGeloescht(Einheit einheit) {
		// notify all listeners
		for (EinheitListener listener : listenerMap.values()) {
			listener.einheitGeloescht(einheit);
		}
	}

}
