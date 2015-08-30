package de.popts.verein.rollentyp.api;

import java.util.List;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitArt;

public interface RollenTypService {

	public RollenTyp rollenTypErzeugen(RollenTyp rollenTyp) throws Exception;
	
	public void rollenTypLoeschen(RollenTyp rollenTyp) throws Exception;
	
	public void addRollenTypToEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt)  throws Exception;
	
	public void removeRollenTypFromEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt)  throws Exception;
	
	public void addRollenTypToEinheit(RollenTyp rollenTyp, Einheit einheit)  throws Exception;
	
	public void removeRollenTypFromEinheit(RollenTyp rollenTyp, Einheit einheit)  throws Exception;
	
	public List<RollenTyp> listRollenTyp4EinheitArtAndRollenArt(EinheitArt einheitArt, RollenArt rollenArt) throws Exception;
	
	public List<RollenTyp> listRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws Exception;
	
	public List<RollenTyp> listAllRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws Exception;
	
	public List<RollenTyp> listRollenTyp4RollenArt(RollenArt rollenArt) throws Exception;

	public boolean isValidRollenTyp4Einheit(Einheit einheit, RollenTyp rollenTyp) throws Exception;
}
