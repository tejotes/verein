package de.popts.verein.einheit.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitService;
import de.popts.verein.einheit.api.EinheitArt;
import de.popts.verein.einheit.api.EinheitException;
import de.popts.verein.einheit.api.EinheitListenerService;

@Component
public class EinheitApiMemoryImpl implements EinheitService {

	@ServiceDependency
	private volatile EinheitListenerService listenerApi;
	
	private final Map<String, Einheit> einheitStore = new ConcurrentHashMap<>();
	
	@Override
	public Einheit einheitErzeugen(Einheit einheit) throws Exception {
		// check params
		if (einheit.getArt() == null) {
			throw new EinheitException("art == null");
		}
		
		String id = einheit.getId();
		if (id==null || id.isEmpty()) {
			throw new EinheitException("id == null or empty");
		}
		
		// create new Einheit
		einheit.setOid(UUID.randomUUID().toString());
		einheit.setCreated(new Date());
		
		// put clone in store
		einheitStore.put(einheit.getOid(), new Einheit(einheit));
		
		// notify listeners
		listenerApi.einheitAngelegt(einheit);
		
		// return copy of einheit
		return einheit;
	}

	@Override
	public void einheitLoeschen(Einheit einheit) throws Exception {
		// check params
		if (einheit == null) {
			throw new EinheitException("einheit == null");
		}
		String oid = einheit.getOid();
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// remove from hashmap
		Einheit deletedEinheit = einheitStore.remove(oid); 
		if (deletedEinheit == null) {
			String message = String.format("oid %s not found.", oid);
			throw new EinheitException(message);
		}
		
		// notify listeners
		listenerApi.einheitGeloescht(deletedEinheit);
	}

	@Override
	public Einheit einheit4oid(String oid) throws Exception {
		// check params
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// lookup in hashmap
		Einheit result = einheitStore.get(oid);
		
		// return clone of result
		return (result != null) ? new Einheit(result) : null;
	}

	@Override
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws Exception {
		// check params
		if (oberEinheit == null) {
			throw new EinheitException("oberEinheit == null");
		}
		String oid = oberEinheit.getOid();
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// declare result
		List<Einheit> einheitList = new ArrayList<>(einheitStore.values().size());
		
		// search in store
		for (Einheit einheit : einheitStore.values()) {
			if (oid.equals(einheit.getOberEinheitOid())) {
				einheitList.add(new Einheit(einheit));
			}
		}
		
		// return result
		return einheitList;
	}

	@Override
	public List<Einheit> einheitList4art(EinheitArt art) throws Exception {
		// check params
		if (art == null) {
			throw new EinheitException("art == null");
		}
		
		// declare result
		List<Einheit> einheitList = new ArrayList<>(einheitStore.values().size());
		
		// search in store
		for (Einheit einheit : einheitStore.values()) {
			if (einheit.getArt() == art) {
				einheitList.add(new Einheit(einheit));
			}
		}
		
		// return result
		return einheitList;
	}

	@Override
	public List<Einheit> einheitList() throws Exception {
		// declare result
		List<Einheit> einheitList = new ArrayList<>(einheitStore.values().size());
		
		// search in store
		for (Einheit einheit : einheitStore.values()) {
			einheitList.add(new Einheit(einheit));
		}
		
		// return result
		return einheitList;
	}

}
