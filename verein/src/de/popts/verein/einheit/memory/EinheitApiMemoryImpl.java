package de.popts.verein.einheit.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.einheit.Einheit;
import de.popts.verein.einheit.EinheitApi;
import de.popts.verein.einheit.EinheitArt;
import de.popts.verein.einheit.EinheitException;
import de.popts.verein.einheit.EinheitListenerApi;

@Component
public class EinheitApiMemoryImpl implements EinheitApi {

	@ServiceDependency
	private volatile EinheitListenerApi listenerApi;
	
	private final Map<String, Einheit> einheitStore = new ConcurrentHashMap<>();
	
	@Override
	public Einheit einheitErzeugen(EinheitArt art, String id, String name, Einheit oberEinheit) throws EinheitException {
		// check params
		if (art == null) {
			throw new EinheitException("art == null");
		}
		
		if (id==null || id.isEmpty()) {
			throw new EinheitException("id == null or empty");
		}
		
		// create new Einheit
		Einheit einheit = new Einheit();
		einheit.setOid(UUID.randomUUID().toString());
		einheit.setArt(art);
		einheit.setId(id);
		einheit.setName(name);
		einheit.setCreated(new Date());
		
		if (oberEinheit != null) {
			einheit.setOberEinheitOid(oberEinheit.getOid());
		}
		
		// put clone in store
		einheitStore.put(einheit.getOid(), new Einheit(einheit));
		
		// notify listeners
		listenerApi.einheitAngelegt(einheit);
		
		// return copy of einheit
		return einheit;
	}

	@Override
	public void einheitLoeschen(Einheit einheit) throws EinheitException {
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
	public Einheit einheit4oid(Einheit einheit) throws EinheitException {
		// check params
		if (einheit == null) {
			throw new EinheitException("einheit == null");
		}
		String oid = einheit.getOid();
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// lookup in hashmap
		Einheit result = einheitStore.get(oid);
		
		// return clone of result
		return (result != null) ? new Einheit(result) : null;
	}

	@Override
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws EinheitException {
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
	public List<Einheit> einheitList4art(EinheitArt art) throws EinheitException {
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
	public List<Einheit> einheitList() throws EinheitException {
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
