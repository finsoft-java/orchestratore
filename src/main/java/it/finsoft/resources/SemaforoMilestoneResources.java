package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import it.finsoft.entity.SemaforoMilestone;
import it.finsoft.manager.SemaforoMilestoneManager;

@Stateless
@Path("resources/semaforimilestone")
@Produces({ MediaType.APPLICATION_JSON })
public class SemaforoMilestoneResources {
	
	@Inject
	SemaforoMilestoneManager manager;
	
	@GET
	public List<SemaforoMilestone> findAll() {
		return manager.findAll();
	}
	/*
	@GET
	public Response findAll() {
		System.out.println("siamo nel nuovo get ");
		GenericEntity<List<SemaforoMilestone>> semafmil = new GenericEntity<List<SemaforoMilestone>>(manager.findAll()) {};
		System.out.println("siamo nel nuovo get " + semafmil);
		return Response.ok(semafmil).build();
        }
	*/
	@GET
	@Path("{id}")
	public SemaforoMilestone findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public SemaforoMilestone create(SemaforoMilestone cal) {
		System.out.println("post resources, salvo semaforomilestone " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("{id}")//richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, SemaforoMilestone m) {
		m.setIdSemaforoMilestone(id);
		manager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok semaforimilestone");
		return "ok semaforimilestone";
	}
	/* ---- TEST RESOURCES ---- */


}
