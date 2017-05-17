package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Semaforo;
import it.finsoft.manager.SemaforoManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class SemaforoResources {
	
	@Inject
	SemaforoManager semaforomanager;
	
	/* ---- TEST RESOURCES ---- */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String prova(){
		System.out.println("ok semafori");
		return "ok semafori";
	}
	/* ---- TEST RESOURCES ---- */

	/*
	@GET
	public List<Semaforo> findAll() {
		return semaforomanager.findAll();
	}
	*/

	@GET
	@Path("{id}")
	public Semaforo findById(@PathParam("id") Long id) {
		return semaforomanager.findById(id);
	}

	/*
	@GET
	@Path("{tag}")
	public Evento findByTag(@PathParam("tag") String tag) {
		return eventimanager.findByTag(tag);
	}
	*/

}
