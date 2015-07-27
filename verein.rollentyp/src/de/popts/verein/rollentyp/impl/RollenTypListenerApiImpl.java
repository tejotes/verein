package de.popts.verein.rollentyp.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.osgi.framework.ServiceReference;

import de.popts.verein.rollentyp.api.RollenTyp;
import de.popts.verein.rollentyp.api.RollenTypException;
import de.popts.verein.rollentyp.api.RollenTypListener;
import de.popts.verein.rollentyp.api.RollenTypListenerApi;

@Component
public class RollenTypListenerApiImpl implements RollenTypListenerApi {

	final private Map<ServiceReference, RollenTypListener> listenerMap = new ConcurrentHashMap<>();

	@ServiceDependency(removed = "listenerRemoved", required = false)
	public void listenerAdded(ServiceReference ref, RollenTypListener listener) {
		listenerMap.put(ref, listener);
		System.out.println("listener added: " + listener);
	}

	public void listenerRemoved(ServiceReference ref) {
		listenerMap.remove(ref);
		System.out.println("listener removed: " + ref);
	}

	@Override
	public void rollenTypAngelegt(RollenTyp rollenTyp) throws RollenTypException {
		for (RollenTypListener listener : listenerMap.values()) {
			listener.rollenTypAngelegt(rollenTyp);
		}
	}

	@Override
	public void rollenTypGeloescht(RollenTyp rollenTyp) throws RollenTypException {
		for (RollenTypListener listener : listenerMap.values()) {
			listener.rollenTypGeloescht(rollenTyp);
		}
	}

}
