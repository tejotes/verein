package de.popts.verein.rolle.jpa;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.person.api.Person;
import de.popts.verein.rolle.api.Rolle;
import de.popts.verein.rolle.api.RolleException;
import de.popts.verein.rolle.api.RolleService;

@Transactional
@Component(provides = ManagedTransactional.class)
public class RolleServiceJpaImpl implements RolleService, ManagedTransactional {

	@ServiceDependency(filter="(osgi.unit.name=VereinRollePU)")
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {RolleService.class};
	}

	@Override
	public Rolle rolleAnlegen(Rolle rolle) throws Exception {
		// check params
		if (rolle == null) {
			throw new RolleException("rolle == null");
		}
		
		// generate oid
		if (rolle.getOid() == null) {
			rolle.setOid(UUID.randomUUID().toString());
		}
		
		// persist
		em.persist(JpaRolle.fromRolle(rolle));
		
		// return result
		return rolle;
	}

	@Override
	public void rolleBeenden(Rolle rolle, Date endeDatum, String grund) throws Exception {
		// get jpaRolle
		JpaRolle jpaRolle = em.find(JpaRolle.class, rolle.getOid());
		
		// change attribs
		jpaRolle.setEndeDatum(endeDatum);
		jpaRolle.setEndeGrund(grund);
	}

	@Override
	public Rolle get4oid(String oid) throws Exception {
		// get from em
		JpaRolle jpaRolle = em.find(JpaRolle.class, oid);
		
		// return rolle
		return jpaRolle.toRolle();
	}

	@Override
	public List<Rolle> rolleList4person(Person person) throws Exception {
		// check params
		if (person == null) {
			throw new RolleException("person ==  null");
		}
		
		// declare query
		TypedQuery<JpaRolle>  query = em.createQuery("select r from JpaRolle r where r.personOid = :personOid", JpaRolle.class);
		query.setParameter("personOid", person.getOid());
		
		// return result
		return query.getResultList().stream().map(JpaRolle::toRolle).collect(Collectors.toList());
	}

}
