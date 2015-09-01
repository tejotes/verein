package de.popts.verein.einheit.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.ServiceDependency;

import de.popts.verein.einheit.api.Einheit;
import de.popts.verein.einheit.api.EinheitService;

@Component(provides = Object.class)
@Path("einheit")
public class EinheitResource {

	@ServiceDependency
	private volatile EinheitService einheitService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Einheit> list() throws Exception {
		
		List<Einheit> einheitList = einheitService.einheitList();
		
		return einheitList;
	}
}
