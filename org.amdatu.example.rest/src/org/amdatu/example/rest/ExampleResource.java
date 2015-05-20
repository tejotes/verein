package org.amdatu.example.rest;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.Arrays;
import org.apache.felix.dm.annotation.api.Component;

@Component(provides=Object.class)
@Path("example")
public class ExampleResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
   public List<String> list() {
		
		return Arrays.asList("test");
	}
	
}