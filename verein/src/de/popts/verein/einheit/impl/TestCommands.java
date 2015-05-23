package de.popts.verein.einheit.impl;

import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.einheit.Einheit;
import de.popts.verein.einheit.EinheitApi;
import de.popts.verein.einheit.EinheitArt;
import de.popts.verein.einheit.EinheitException;

@Component(properties = {
		@Property(name = CommandProcessor.COMMAND_SCOPE, value = "einheit"),
		@Property(name = CommandProcessor.COMMAND_FUNCTION, value = { "show", "store" }) 
	}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile EinheitApi einheitApi;
	
	public void show() throws EinheitException {
		// get list
		List<Einheit> einheitList = einheitApi.einheitList();
		
		// put to stdout
		for (Einheit einheit : einheitList) {
			System.out.println(einheit);
		}
	}
	
	public void store(String artString, String id, String name, String obereinheitOid) throws EinheitException {
		long startMillis = System.currentTimeMillis();
		
		// check params
		EinheitArt art = EinheitArt.valueOf(artString);
		Einheit oberEinheit = obereinheitOid != null ? einheitApi.einheit4oid(new Einheit(obereinheitOid)) : null;
		
		Einheit einheit = einheitApi.einheitErzeugen(art, id, name, oberEinheit);

		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]: " + einheit);
	}
	
	public void store(String artString, String id, String name) throws EinheitException {
		// delegate
		store(artString, id, name, null);
	}
}
