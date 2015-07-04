package de.popts.verein.rollentyp;

import java.util.List;

import de.popts.verein.einheit.Einheit;
import de.popts.verein.einheit.EinheitArt;

public interface RollenTypApi {

	public RollenTyp rollenTypErzeugen(RollenTyp rollenTyp) throws RollenTypException;
	
	public void rollenTypLoeschen(RollenTyp rollenTyp) throws RollenTypException;
	
	public void addRollenTypToEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt)  throws RollenTypException;
	
	public void removeRollenTypFromEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt)  throws RollenTypException;
	
	public void addRollenTypToEinheit(RollenTyp rollenTyp, Einheit einheit)  throws RollenTypException;
	
	public void removeRollenTypFromEinheit(RollenTyp rollenTyp, Einheit einheit)  throws RollenTypException;
	
	public List<RollenTyp> listRollenTyp4EinheitArtAndRollenArt(EinheitArt einheitArt, RollenArt rollenArt) throws RollenTypException;
	
	public List<RollenTyp> listRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException;
	
	public List<RollenTyp> listAllRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException;
	
	public List<RollenTyp> listRollenTyp4RollenArt(RollenArt rollenArt) throws RollenTypException;

	public boolean isValidRollenTyp4Einheit(Einheit einheit, RollenTyp rollenTyp) throws RollenTypException;
}
