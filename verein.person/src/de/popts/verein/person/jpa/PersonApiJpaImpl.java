package de.popts.verein.person.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.person.api.Person;
import de.popts.verein.person.api.PersonApi;
import de.popts.verein.person.api.PersonException;
import de.popts.verein.person.api.PersonListenerApi;

@Transactional
@Component(provides = ManagedTransactional.class)
public class PersonApiJpaImpl implements PersonApi, ManagedTransactional {

	@ServiceDependency
	private volatile PersonListenerApi listenerApi;
	
	@ServiceDependency
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {PersonApi.class};
	}

	@Override
	public Person personAnlegen(Person person) throws PersonException {
		// check params
		if (person == null) {
			throw new PersonException("person==null");
		}
		
		// set oid
		if (person.getOid() == null) {
			person.setOid(UUID.randomUUID().toString());
		}
		
		// trivial implementation
		em.persist(JpaPerson.fromPerson(person));
		
		// notify listeners
		listenerApi.personAngelegt(person);
		
		// return person
		return person;
	}

	@Override
	public void personLoeschen(Person person) throws PersonException {
		// check params
		if (person == null) {
			throw new PersonException("person == null");
		}
		
		// execute query
		JpaPerson jpaPerson = jpaPerson4oid(person.getOid());
		
		// delete from db
		em.remove(jpaPerson);
		
		// notify listeners
		listenerApi.personGeloescht(person);
	}

	@Override
	public Person personAendern(Person person) throws PersonException {
		// trivial implementation
		em.persist(JpaPerson.fromPerson(person));
		
		// notify listeners
		listenerApi.personGeaendert(person);
		
		// return person
		return person;
	}

	@Override
	public List<Person> listAll() {
		// execute query
		TypedQuery<JpaPerson> query = em.createQuery("select p from JpaPerson p", JpaPerson.class);
		
		// return result
		return query.getResultList().stream().map(JpaPerson::toPerson).collect(Collectors.toList());
	}

	@Override
	public List<Person> list4Jahr(int jahr) {
		// execute query
		TypedQuery<JpaPerson> query = em.createQuery("select p from JpaPerson p WHERE SQL('EXTRACT (YEAR FROM ?)', p.geburtsDatum) = :year", JpaPerson.class);
		
		query.setParameter("year", jahr);
		
		// return result
		return query.getResultList().stream().map(JpaPerson::toPerson).collect(Collectors.toList());
	}

	@Override
	public Person get4oid(String oid) {
		// execute query
		JpaPerson jpaPerson = jpaPerson4oid(oid);
		
		// convert to Person
		return (jpaPerson != null) ? jpaPerson.toPerson() : null;
	}

	private JpaPerson jpaPerson4oid(String oid) {
		// declare query
		TypedQuery<JpaPerson> query = em.createQuery("select p from JpaPerson p where p.oid = :oid", JpaPerson.class);
		
		// execute query
		JpaPerson jpaPerson = query.setParameter("oid", oid).getSingleResult();
		
		// return result
		return jpaPerson;
	}
	
}
