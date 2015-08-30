package de.popts.verein.rollentyp.api;

public interface RollenTypListener {

	public void rollenTypAngelegt(RollenTyp rollenTyp) throws Exception;
	
	public void rollenTypGeloescht(RollenTyp rollenTyp) throws Exception;
}
