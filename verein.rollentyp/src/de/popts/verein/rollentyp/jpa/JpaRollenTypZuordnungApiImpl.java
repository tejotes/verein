package de.popts.verein.rollentyp.jpa;

import javax.persistence.EntityManager;

import org.amdatu.jta.ManagedTransactional;
import org.amdatu.jta.Transactional;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

@Transactional
@Component(provides = ManagedTransactional.class)
public class JpaRollenTypZuordnungApiImpl implements ManagedTransactional {

	@ServiceDependency
	private volatile EntityManager em;
	
	@Override
	public Class<?>[] getManagedInterfaces() {
		return new Class[] {JpaRollenTypZuordnungApi.class};
	}

}
