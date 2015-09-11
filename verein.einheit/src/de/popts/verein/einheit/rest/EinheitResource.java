package de.popts.verein.einheit.rest;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

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

	@GET
	@Path("{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("oid") String oid) {
		
		try {
			Einheit einheit = einheitService.einheit4oid(oid);
			return Response.ok(einheit).build();
		} catch (Exception e) {
			return Response.status(404).build();
		}
	}
	
	@PUT
	@Path("{oid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void put(@PathParam("oid") String oid, Einheit einheit) throws Exception {
		// nix zu tun
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Einheit einheit) throws Exception {
		einheitService.einheitErzeugen(einheit);
		return Response.seeOther(UriBuilder.fromResource(this.getClass()).path(einheit.getOid()).build()).build();
	}
	
	@DELETE
	@Path("{oid}")
	void delete(@PathParam("oid") String oid) throws Exception {
		Einheit einheit = new Einheit(oid);
		einheitService.einheitLoeschen(einheit);
	}
	
}
