package de.popts.verein.einheit.impl;

import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitApi;
import de.popts.verein.einheit.api.EinheitArt;
import de.popts.verein.einheit.api.EinheitException;

@Component(properties = {
		@Property(name = CommandProcessor.COMMAND_SCOPE, value = "einheit"),
		@Property(name = CommandProcessor.COMMAND_FUNCTION, value = { "show", "store", "remove" }) 
	}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile EinheitApi einheitApi;
	
	public void show() throws EinheitException {
		// start timer
		long startMillis = System.currentTimeMillis();
		
		// get list
		List<Einheit> einheitList = einheitApi.einheitList();
		
		if (einheitList == null || einheitList.isEmpty()) {
			System.out.println("no Einheit found.");
		}
		
		// put to stdout
		for (Einheit einheit : einheitList) {
			System.out.println(einheit);
		}
		
		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void show(String oid) throws EinheitException {
		// start timer
		long startMillis = System.currentTimeMillis();
		
		// get einheit
		Einheit einheit = einheitApi.einheit4oid(oid);
		
		// check result
		if (einheit != null) {
			System.out.println(einheit);
		} else {
			System.out.println("no Einheit found.");
		}
		
		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void store(String artString, String id, String name, String obereinheitOid) throws EinheitException {
		// start timer
		long startMillis = System.currentTimeMillis();
		
		// check params
		EinheitArt art = EinheitArt.valueOf(artString);
		
		Einheit einheit = new Einheit();
		einheit.setArt(art);
		einheit.setId(id);
		einheit.setName(name);
		einheit.setOberEinheitOid(obereinheitOid);
		
		einheit = einheitApi.einheitErzeugen(einheit);

		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]: " + einheit);
	}
	
	public void store(String artString, String id, String name) throws EinheitException {
		// delegate
		store(artString, id, name, null);
	}
	
	public void remove(String oid) throws EinheitException {
		// start timer
		long startMillis = System.currentTimeMillis();
		
		// delete einheit
		Einheit einheit = new Einheit(oid);
		einheitApi.einheitLoeschen(einheit);
		
		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	

}
