package it.finsoft.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pincopallino")
@Produces({ MediaType.APPLICATION_JSON })
public class AzioneResources {
	
	@GET
	@Path("Test")
	public String findAll() {
		return "Hello world!";
	}
}
