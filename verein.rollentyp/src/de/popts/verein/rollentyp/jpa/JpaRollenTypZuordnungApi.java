package de.popts.verein.rollentyp.jpa;

import de.popts.verein.einheit.api.EinheitArt;

public interface JpaRollenTypZuordnungApi {

	public void zuordnungAnlegen(EinheitArt einheitArt, String rollenTypOid);
	
	public void zuordnungAnlegen(String einheitOid, String rollenTypOid);
	
}
