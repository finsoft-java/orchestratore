package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.finsoft.entity.Entita;
import it.finsoft.manager.EntitaManager;

@Stateless
@Path("collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {
	
	@Inject
	EntitaManager em;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Entita create(Entita ent) {
		System.out.println("post resources, salvo entita " + ent);
		return em.save(ent);
	}
	
	/*
	 * Metodo GET per inserire dati via http
	 * 
	*/
	@GET
	public Entita create(
			@QueryParam("codice")String codice,
			@QueryParam("acronimo")String acronimo, 
			@QueryParam("descrizione")String descrizione
			) {
		Entita ent = new Entita();
		ent.setCodice(codice);
		ent.setAcronimo(acronimo);
		ent.setDescrizione(descrizione);
		System.out.println("salva resources, salvo entita " + ent);
		return em.save(ent);
	}
	
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
