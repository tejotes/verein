package de.popts.verein.einheit.api;

public interface EinheitListener {

	public void einheitAngelegt(Einheit einheit) throws Exception;
	
	public void einheitGeloescht(Einheit einheit) throws Exception;
}
