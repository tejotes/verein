package de.popts.verein.rolle.impl;

import java.util.Date;
import java.util.List;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.service.command.CommandProcessor;

import de.popts.verein.person.api.Person;
import de.popts.verein.rolle.api.Rolle;
import de.popts.verein.rolle.api.RolleService;

@Component(properties = {
	@Property(name = CommandProcessor.COMMAND_SCOPE, value = "rolle"),
	@Property(name = CommandProcessor.COMMAND_FUNCTION, value = {"show", "store", "remove"}) 
}, provides = Object.class)
public class TestCommands {

	@ServiceDependency
	private volatile RolleService rolleService;
	
	public void show(String personOid) throws Exception {
		List<Rolle> rolleList = rolleService.rolleList4person(new Person(personOid));

		if (rolleList.isEmpty()) {
			System.out.println("no rollen found.");
		}
		
		for (Rolle rolle : rolleList) {
			System.out.println(rolle);
		}
	}
	
	public void remove(String rolleOid) throws Exception {
		Rolle rolle = rolleService.get4oid(rolleOid);
		rolleService.rolleBeenden(rolle, new Date(), "Test");
	}
	
}
