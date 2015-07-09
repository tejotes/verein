package de.popts.verein.rollentyp;

public interface RollenTypListener {

	public void rollenTypAngelegt(RollenTyp rollenTyp) throws RollenTypException;
	
	public void rollenTypGeloescht(RollenTyp rollenTyp) throws RollenTypException;
}
