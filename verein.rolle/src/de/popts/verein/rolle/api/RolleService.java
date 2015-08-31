package de.popts.verein.rolle.api;

import java.util.Date;
import java.util.List;

import de.popts.verein.person.api.Person;

public interface RolleService {

	Rolle rolleAnlegen(Rolle rolle) throws Exception;
	
	void rolleBeenden(Rolle rolle, Date endeDatum, String grund) throws Exception;
	
	Rolle get4oid(String oid) throws Exception;
	
	List<Rolle> rolleList4person(Person person) throws Exception;
}
