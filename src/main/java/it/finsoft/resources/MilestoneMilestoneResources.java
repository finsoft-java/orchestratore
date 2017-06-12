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
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.manager.MilestoneMilestoneManager;

@Stateless
@Path("resources/milestoneMilestones")
@Produces({ MediaType.APPLICATION_JSON })
public class MilestoneMilestoneResources {

	@Inject
	MilestoneMilestoneManager manager;

	@GET
	public List<MilestoneMilestone> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("{id}")
	public MilestoneMilestone findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public MilestoneMilestone create(MilestoneMilestone cal) {
		System.out.println("post resources, salvo semaforomilestone " + cal);
		return manager.preCreate(cal);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("{id}") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, MilestoneMilestone m) {
		m.setIdSemaforoMilestone(id);
		manager.preCreate(m);
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
