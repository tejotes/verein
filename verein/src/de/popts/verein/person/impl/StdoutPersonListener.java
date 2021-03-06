package de.popts.verein.person.impl;

import org.apache.felix.dm.annotation.api.Component;

import de.popts.verein.person.Person;
import de.popts.verein.person.PersonListener;

@Component
public class StdoutPersonListener implements PersonListener {

	@Override
	public void personAngelegt(Person person) {
		System.out.println("stdout:personAngelegt: "+person);
	}

	@Override
	public void personGeloescht(Person person) {
		System.out.println("stdout:personGeloescht: "+person);
	}

	@Override
	public void personGeaendert(Person person) {
		System.out.println("stdout:personGešndert: "+person);
	}

}
