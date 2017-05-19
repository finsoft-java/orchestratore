package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.resource.spi.IllegalStateException;
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
import it.finsoft.entity.Evento;
import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;

@Stateless
@Path("collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {
	
	@Inject
	EventoManager em;
	
	@GET
	public Evento create(
			@QueryParam("codice")String tipoEvento,
			@QueryParam("entita")String entita, 
			@QueryParam("tag")String tag
			) {
		//Evento ev = new Evento();
		/*ev.setTipoEvento(tipoEvento);
		ev.setEntita(entita);*/
		//ev.setTag(tag);
		//System.out.println("salva resources, salvo entita " + ev);
		//return em.save(ev);
		
		throw new UnsupportedOperationException("TODO");
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok collector");
		return "ok collector";
	}
	/* ---- TEST RESOURCES ---- */
	
	/*
	 * Metodo GET per inserire dati via http
	 * 
	*/
	/*@GET
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
	}*/
	
	
	/*
	public Response getEventoAsJSON(@Context HttpHeaders httpHeaders, Entita entita1) {
		List<Entita> ents = em.findAll();
		ents.add(entita1);
		System.out.println("post resources, creo entita1 " + ents);
		em.save(entita1);
		System.out.println("post resources, salvo entita1 " + ents);
		return Response.ok(ents).build();		 
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Entita create(Entita ent) {
		System.out.println("post resources, salvo entita " + ent);
		return em.save(ent);
	}
	*/
	

}
