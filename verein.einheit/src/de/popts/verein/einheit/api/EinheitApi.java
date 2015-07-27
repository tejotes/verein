package de.popts.verein.einheit.api;

import java.util.List;

public interface EinheitApi {

	public Einheit einheitErzeugen(Einheit einheit) throws EinheitException;
	
	public void einheitLoeschen(Einheit einheit) throws EinheitException;
	
	public Einheit einheit4oid(String oid) throws EinheitException;
	
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws EinheitException;
	
	public List<Einheit> einheitList4art(EinheitArt art) throws EinheitException;
	
	public List<Einheit> einheitList() throws EinheitException;
}
