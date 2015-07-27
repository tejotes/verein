package de.popts.verein.einheit.impl;

import org.apache.felix.dm.annotation.api.Component;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitListener;

@Component
public class StdoutEinheitListener implements EinheitListener {

	@Override
	public void einheitAngelegt(Einheit einheit) {
		System.out.println("stdout: Einheit angelegt: " + einheit);
	}

	@Override
	public void einheitGeloescht(Einheit einheit) {
		System.out.println("stdout: Einheit geloescht: " + einheit);
	}

}
