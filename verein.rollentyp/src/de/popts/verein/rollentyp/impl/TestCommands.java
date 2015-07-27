package de.popts.verein.rollentyp.impl;

import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.rollentyp.api.RollenArt;
import de.popts.verein.rollentyp.api.RollenTyp;
import de.popts.verein.rollentyp.api.RollenTypApi;
import de.popts.verein.rollentyp.api.RollenTypException;

@Component(properties = {
		@Property(name = CommandProcessor.COMMAND_SCOPE, value = "rollentyp"),
		@Property(name = CommandProcessor.COMMAND_FUNCTION, value = {"store", "showAll"}) 
}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile RollenTypApi rollenTypApi;
	
	public void store(String rollenArtString, String id, String name) throws RollenTypException {
		// start timer
		long startMillis = System.currentTimeMillis();

		RollenTyp rollenTyp = new RollenTyp();
		rollenTyp.setArt(RollenArt.valueOf(rollenArtString));
		rollenTyp.setId(id);
		rollenTyp.setName(name);

		rollenTyp = rollenTypApi.rollenTypErzeugen(rollenTyp);
		System.out.println("RollenTyp erzeugt: " + rollenTyp);

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void showAll() throws RollenTypException {
		// start timer
		long startMillis = System.currentTimeMillis();

		// get all RollenTyp
		List<RollenTyp> rollenTypList = rollenTypApi.listRollenTyp4RollenArt(RollenArt.ANY); 

		// list on screen
		for (RollenTyp rollenTyp : rollenTypList) {
			System.out.println(rollenTyp);
		}

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void showAll(String rollenArtString) throws RollenTypException {
		// start timer
		long startMillis = System.currentTimeMillis();

		// get RollenArt
		RollenArt rollenArt = RollenArt.valueOf(rollenArtString);
		
		// get all RollenTyp
		List<RollenTyp> rollenTypList = rollenTypApi.listRollenTyp4RollenArt(rollenArt); 

		// list on screen
		for (RollenTyp rollenTyp : rollenTypList) {
			System.out.println(rollenTyp);
		}

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
}
