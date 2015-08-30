package de.popts.verein.einheit.api;

import java.util.List;

public interface EinheitService {

	public Einheit einheitErzeugen(Einheit einheit) throws Exception;
	
	public void einheitLoeschen(Einheit einheit) throws Exception;
	
	public Einheit einheit4oid(String oid) throws Exception;
	
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws Exception;
	
	public List<Einheit> einheitList4art(EinheitArt art) throws Exception;
	
	public List<Einheit> einheitList() throws Exception;
}
