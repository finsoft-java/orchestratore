package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Entita;
import it.finsoft.manager.EntitaManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {
	

	@Inject
	EntitaManager em;
	
	@GET
	public Boolean get(
			@QueryParam("semaforo")String semaforo,
			@QueryParam("tag")String tag
			) {

		throw new UnsupportedOperationException("TODO");
	}
	
	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok polling");
		return "ok polling";
	}
	/* ---- TEST RESOURCES ---- */
	
	/*
	public Response getEventoAsJSON(@Context HttpHeaders httpHeaders, Entita entita1) {
		List<Entita> ents = em.findAll();
		ents.add(entita1);
		System.out.println("post resources, creo entita1 " + ents);
		em.save(entita1);
		System.out.println("post resources, salvo entita1 " + ents);
		return Response.ok(ents).build();		 
	}
	*/

}
