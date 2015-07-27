package de.popts.verein.rollentyp.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitApi;
import de.popts.verein.einheit.api.EinheitArt;
import de.popts.verein.einheit.api.EinheitException;
import de.popts.verein.rollentyp.api.RollenArt;
import de.popts.verein.rollentyp.api.RollenTyp;
import de.popts.verein.rollentyp.api.RollenTypApi;
import de.popts.verein.rollentyp.api.RollenTypException;
import de.popts.verein.rollentyp.api.RollenTypListenerApi;

@Transactional
@Component(provides = ManagedTransactional.class)
public class RollenTypApiJpaImpl implements RollenTypApi, ManagedTransactional {

	@ServiceDependency
	private volatile RollenTypListenerApi rollenTypListenerApi;
	
	@ServiceDependency
	private volatile EinheitApi einheitApi;
	
	@ServiceDependency
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {RollenTypApi.class};
	}

	@Override
	public RollenTyp rollenTypErzeugen(RollenTyp rollenTyp) throws RollenTypException {
		// check params
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		
		// set oid
		rollenTyp.setOid(UUID.randomUUID().toString());
		
		// persist
		JpaRollenTyp jpaRollenTyp = JpaRollenTyp.fromRollenTyp(rollenTyp);
		em.persist(jpaRollenTyp);

		// notify listeners
		rollenTypListenerApi.rollenTypAngelegt(rollenTyp);

		// return result
		return rollenTyp;
	}

	@Override
	public void rollenTypLoeschen(RollenTyp rollenTyp) throws RollenTypException {
		// check params
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}

		// lookup in DB
		JpaRollenTyp jpaRollenTyp = jpaRollenTyp4oid(rollenTyp.getOid());
		
		// delete from DB
		em.remove(jpaRollenTyp);
		
		// notify listener
		rollenTypListenerApi.rollenTypGeloescht(rollenTyp);
	}

	@Override
	public void addRollenTypToEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt) throws RollenTypException {
		// check rolenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		JpaRollenTyp jpaRollenTyp = jpaRollenTyp4oid(rollenTyp.getOid());
		if (jpaRollenTyp == null) {
			throw new RollenTypException("unknown rollenTyp: "+rollenTyp.getOid());
		}
		
		// check einheitArt
		if (einheitArt == null) {
			throw new RollenTypException("einheitArt == null");
		}
		
		// check duplicate
		JpaRollenTypZuordnung jpaRollenTypZuordnung = jpaRollenTypZuordnung4RollenTypAndEinheitArt(rollenTyp.getOid(), einheitArt);
		
		// no duplicate -> insert 
		if (jpaRollenTypZuordnung == null) {
			jpaRollenTypZuordnung = new JpaRollenTypZuordnung();
			jpaRollenTypZuordnung.setOid(UUID.randomUUID().toString());
			jpaRollenTypZuordnung.setRollenTypOid(rollenTyp.getOid());
			jpaRollenTypZuordnung.setEinheitArt(einheitArt);
			em.persist(jpaRollenTypZuordnung);
		}
	}

	@Override
	public void removeRollenTypFromEinheitArt(RollenTyp rollenTyp, EinheitArt einheitArt) throws RollenTypException {
		// check rolenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		JpaRollenTyp jpaRollenTyp = jpaRollenTyp4oid(rollenTyp.getOid());
		if (jpaRollenTyp == null) {
			throw new RollenTypException("unknown rollenTyp: "+rollenTyp.getOid());
		}
		
		// check einheitArt
		if (einheitArt == null) {
			throw new RollenTypException("einheitArt == null");
		}
		
		// check existence
		JpaRollenTypZuordnung jpaRollenTypZuordnung = jpaRollenTypZuordnung4RollenTypAndEinheitArt(rollenTyp.getOid(), einheitArt);
		if (jpaRollenTypZuordnung == null) {
			throw new RollenTypException("RollenTypZuordnung does not exist: " + rollenTyp.getOid() + ", " + einheitArt);
		}
		
		// remove from DB
		em.remove(jpaRollenTypZuordnung);
	}

	@Override
	public void addRollenTypToEinheit(RollenTyp rollenTyp, Einheit einheit) throws RollenTypException {
		// check rolenTyp
		if (rollenTyp == null) {
			throw new RollenTypException("rollenTyp == null");
		}
		JpaRollenTyp jpaRollenTyp = jpaRollenTyp4oid(rollenTyp.getOid());
		if (jpaRollenTyp == null) {
			throw new RollenTypException("unknown rollenTyp: "+rollenTyp.getOid());
		}
		
		// check einheit
		try {
			einheit = einheitApi.einheit4oid(einheit.getOid());
		} catch (EinheitException e) {
			throw new RollenTypException("einheit == null");
		}
		
		// check duplicate
		JpaRollenTypZuordnung jpaRollenTypZuordnung = jpaRollenTypZuordnung4RollenTypAndEinheit(rollenTyp.getOid(), einheit.getOid());
		
		// no duplicate -> insert 
		if (jpaRollenTypZuordnung == null) {
			jpaRollenTypZuordnung = new JpaRollenTypZuordnung();
			jpaRollenTypZuordnung.setOid(UUID.randomUUID().toString());
			jpaRollenTypZuordnung.setRollenTypOid(rollenTyp.getOid());
			jpaRollenTypZuordnung.setEinheitOid(einheit.getOid());
			em.persist(jpaRollenTypZuordnung);
		}
	}

	@Override
	public List<RollenTyp> listRollenTyp4EinheitArtAndRollenArt(EinheitArt einheitArt, RollenArt rollenArt) throws RollenTypException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRollenTypFromEinheit(RollenTyp rollenTyp, Einheit einheit) throws RollenTypException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RollenTyp> listRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RollenTyp> listAllRollenTyp4EinheitAndRollenArt(Einheit einheit, RollenArt rollenArt) throws RollenTypException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RollenTyp> listRollenTyp4RollenArt(RollenArt rollenArt) throws RollenTypException {
		
		// XXX DUMMY implementation
		
		// declare result
		List<RollenTyp> rollenTypList = new ArrayList<>();
		
		JpaRollenTyp jpaRollenTyp = jpaRollenTyp4oid("rollenart-1");
		rollenTypList.add(jpaRollenTyp.toRollenTyp());
		
		// return result
		return rollenTypList;
	}

	@Override
	public boolean isValidRollenTyp4Einheit(Einheit einheit, RollenTyp rollenTyp) throws RollenTypException {
		// TODO Auto-generated method stub
		return false;
	}

	private JpaRollenTyp jpaRollenTyp4oid(String oid) {
		// declare query
		TypedQuery<JpaRollenTyp> query = em.createQuery("select rt from JpaRollenTyp rt where rt.oid = :oid", JpaRollenTyp.class);
		
		// execute query
		JpaRollenTyp jpaRollenTyp = query.setParameter("oid", oid).getSingleResult();
		
		// return result
		return jpaRollenTyp;
	}

	private JpaRollenTypZuordnung jpaRollenTypZuordnung4RollenTypAndEinheitArt(String rollenTypOid, EinheitArt einheitArt) {
		// declare query
		TypedQuery<JpaRollenTypZuordnung> query = em.createQuery("select rtz from JpaRollenTypZuordnung rtz where rtz.rollenTypOid = :rollenTypOid and rtz.einheitArt = :einheitArt", JpaRollenTypZuordnung.class);
		
		// execute query
		JpaRollenTypZuordnung jpaRollenTypZuordnung = query.setParameter("rollenTypOid", rollenTypOid).setParameter("einheitArt", einheitArt).getSingleResult();
		
		// return result
		return jpaRollenTypZuordnung;
	}

	private JpaRollenTypZuordnung jpaRollenTypZuordnung4RollenTypAndEinheit(String rollenTypOid, String einheitOid) {
		// declare query
		TypedQuery<JpaRollenTypZuordnung> query = em.createQuery("select rt from JpaRollenTypZuordnung rtz where rtz.rollenTypOid = :rollenTypOid and rtz.einheitOid = :einheitOid", JpaRollenTypZuordnung.class);
		
		// execute query
		JpaRollenTypZuordnung JpaRollenTypZuordnung = query.setParameter("rollenTypOid", rollenTypOid).setParameter("einheitOid", einheitOid).getSingleResult();
		
		// return result
		return JpaRollenTypZuordnung;
	}
}
