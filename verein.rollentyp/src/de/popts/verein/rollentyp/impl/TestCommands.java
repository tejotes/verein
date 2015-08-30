package de.popts.verein.rollentyp.impl;

import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.rollentyp.api.RollenArt;
import de.popts.verein.rollentyp.api.RollenTyp;
import de.popts.verein.rollentyp.api.RollenTypService;

@Component(properties = {
		@Property(name = CommandProcessor.COMMAND_SCOPE, value = "rollentyp"),
		@Property(name = CommandProcessor.COMMAND_FUNCTION, value = {"store", "showAll"}) 
}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile RollenTypService rollenTypService;
	
	public void store(String rollenArtString, String id, String name) throws Exception {
		// start timer
		long startMillis = System.currentTimeMillis();

		RollenTyp rollenTyp = new RollenTyp();
		rollenTyp.setArt(RollenArt.valueOf(rollenArtString));
		rollenTyp.setId(id);
		rollenTyp.setName(name);

		rollenTyp = rollenTypService.rollenTypErzeugen(rollenTyp);
		System.out.println("RollenTyp erzeugt: " + rollenTyp);

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void showAll() throws Exception {
		// start timer
		long startMillis = System.currentTimeMillis();

		// get all RollenTyp
		List<RollenTyp> rollenTypList = rollenTypService.listRollenTyp4RollenArt(RollenArt.ANY); 

		// list on screen
		for (RollenTyp rollenTyp : rollenTypList) {
			System.out.println(rollenTyp);
		}

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
	public void showAll(String rollenArtString) throws Exception {
		// start timer
		long startMillis = System.currentTimeMillis();

		// get RollenArt
		RollenArt rollenArt = RollenArt.valueOf(rollenArtString);
		
		// get all RollenTyp
		List<RollenTyp> rollenTypList = rollenTypService.listRollenTyp4RollenArt(rollenArt); 

		// list on screen
		for (RollenTyp rollenTyp : rollenTypList) {
			System.out.println(rollenTyp);
		}

		// log
		long durationMillis = System.currentTimeMillis() - startMillis;
		System.out.println("duration: " + durationMillis + "[ms]");
	}
	
}
