package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Evento;
import it.finsoft.manager.EventoManager;

@Stateless
@Path("eventi")
@Produces({ MediaType.APPLICATION_JSON })
public class EventoResources {

	@Inject
	EventoManager eventimanager;
	
	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova(){
		System.out.println("ok eventi");
		return "ok eventi";
	}
	/* ---- TEST RESOURCES ---- */
	
	@GET
	public List<Evento> findAll() {
		return eventimanager.findAll();
	}

	@GET
	@Path("{id}")
	public Evento findById(@PathParam("id") Long id) {
		return eventimanager.findById(id);
	}

	/*
	@GET
	@Path("{tag}")
	public Eventi findByTag(@PathParam("tag") String tag) {
		return eventimanager.findByTag(tag);
	}
	*/

	@POST
	@Path("crea")
	public Evento create(Evento evento) {
		return eventimanager.save(evento);
	}

}
