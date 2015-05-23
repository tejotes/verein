package de.popts.verein.einheit;

import java.util.List;

public interface EinheitApi {

	public Einheit einheitErzeugen(EinheitArt art, String id, String name, Einheit oberEinheit) throws EinheitException;
	
	public void einheitLoeschen(Einheit einheit) throws EinheitException;
	
	public Einheit einheit4oid(Einheit einheit) throws EinheitException;
	
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws EinheitException;
	
	public List<Einheit> einheitList4art(EinheitArt art) throws EinheitException;
}
