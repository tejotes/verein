package de.popts.verein.person.jpa.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.person.Person;
import de.popts.verein.person.PersonApi;
import de.popts.verein.person.PersonException;
import de.popts.verein.person.jpa.JpaPerson;
import de.popts.verein.person.memory.PersonApiMemoryImpl;

@Transactional
@Component(provides = ManagedTransactional.class)
public class PersonApiJpaImpl implements PersonApi, ManagedTransactional {

	@ServiceDependency
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {PersonApi.class};
	}

	@Override
	public Person personAnlegen(Person person) throws PersonException {
		// set oid
		person.setOid(UUID.randomUUID().toString());
		
		// trivial implementation
		em.persist(JpaPerson.fromPerson(person));
		
		// return person
		return person;
	}

	@Override
	public void personLoeschen(Person person) throws PersonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person personAendern(Person person) throws PersonException {
		// trivial implementation
		em.persist(JpaPerson.fromPerson(person));
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person get4oid(String oid) {
		// declare query
		TypedQuery<JpaPerson> query = em.createQuery("select p from JpaPerson p where p.oid = :oid", JpaPerson.class);
		
		// execute query
		JpaPerson jpaPerson = query.setParameter("oid", oid).getSingleResult();
		
		// convert to Person
		return (jpaPerson != null) ? jpaPerson.toPerson() : null;
	}

}
