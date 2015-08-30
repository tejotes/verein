package de.popts.verein.einheit.jpa;

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

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitService;
import de.popts.verein.einheit.api.EinheitArt;
import de.popts.verein.einheit.api.EinheitException;
import de.popts.verein.einheit.api.EinheitListenerService;

@Transactional
@Component(provides = ManagedTransactional.class)
public class EinheitApiJpaImpl implements EinheitService, ManagedTransactional {

	@ServiceDependency
	private volatile EinheitListenerService listerApi;
	
	@ServiceDependency(filter="(osgi.unit.name=VereinEinheitPU)")
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {EinheitService.class};
	}

	@Override
	public Einheit einheitErzeugen(Einheit einheit) throws Exception {
		// check params
		if (einheit == null) {
			throw new EinheitException("einheit == null");
		}
		
		// set oid
		if (einheit.getOid() == null) {
			einheit.setOid(UUID.randomUUID().toString());
		}
		if (einheit.getCreated() == null) {
			einheit.setCreated(new Date());
		}
		
		// persist
		em.persist(JpaEinheit.fromEinheit(einheit));
		
		// notify listeners
		listerApi.einheitAngelegt(einheit);
		
		// return einheit
		return einheit;
	}

	@Override
	public void einheitLoeschen(Einheit einheit) throws Exception {
		// check params
		if (einheit == null) {
			throw new EinheitException("einheit == null");
		}
		
		// check oid
		String oid = einheit.getOid();
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// get entity
		JpaEinheit jpaEinheit = jpaeinheit4oid(oid);
		if (jpaEinheit == null) {
			throw new EinheitException("oid not found: " + oid);
		}
		
		// delete
		em.remove(jpaEinheit);
	}

	@Override
	public Einheit einheit4oid(String oid) throws Exception {
		// check params
		if (oid == null) {
			throw new EinheitException("oid == null");
		}
		
		// get entity
		JpaEinheit jpaEinheit = jpaeinheit4oid(oid);
		if (jpaEinheit == null) {
			throw new EinheitException("oid not found: " + oid);
		}
		
		// return result
		return jpaEinheit.toEinheit();
	}

	@Override
	public List<Einheit> einheitList4oberEinheit(Einheit oberEinheit) throws Exception {
		// check params
		if (oberEinheit == null) {
			throw new EinheitException("oberEinheit == null");
		}
		String oberEinheitOid = oberEinheit.getOid();
		
		// execute query
		TypedQuery<JpaEinheit> query = em.createQuery("select e from JpaEinheit e where e.oberEinheitOid = :oberEinheitOid", JpaEinheit.class);
		
		// return result
		return query.setParameter("oberEinheitOid", oberEinheitOid).getResultList().stream().map(JpaEinheit::toEinheit).collect(Collectors.toList());
	}

	@Override
	public List<Einheit> einheitList4art(EinheitArt art) throws Exception {
		// execute query
		TypedQuery<JpaEinheit> query = em.createQuery("select e from JpaEinheit e where e.art = :art", JpaEinheit.class);
		
		// return result
		return query.setParameter("art", art).getResultList().stream().map(JpaEinheit::toEinheit).collect(Collectors.toList());
	}

	@Override
	public List<Einheit> einheitList() throws Exception {
		// execute query
		TypedQuery<JpaEinheit> query = em.createQuery("select e from JpaEinheit e", JpaEinheit.class);
		
		// return result
		return query.getResultList().stream().map(JpaEinheit::toEinheit).collect(Collectors.toList());
	}
	
	
	private JpaEinheit jpaeinheit4oid(String oid) {
		// declare query
		TypedQuery<JpaEinheit> query = em.createQuery("select e from JpaEinheit e where e.oid = :oid", JpaEinheit.class);
		
		// execute query
		JpaEinheit jpaEinheit = query.setParameter("oid", oid).getSingleResult();
		
		// return result
		return jpaEinheit;
	}
}
