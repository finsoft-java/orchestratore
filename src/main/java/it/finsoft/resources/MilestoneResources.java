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

import it.finsoft.entity.Milestone;
import it.finsoft.manager.MilestoneManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class MilestoneResources {

	@Inject
	MilestoneManager milestoneManager;

	@GET
	@Path("Milestones")
	public List<Milestone> findAll() {
		return milestoneManager.findAll();
	}

	@GET
	@Path("Milestones({id})")
	public Milestone findById(@PathParam("id") Long id) {
		return milestoneManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Milestones")
	public Milestone create(Milestone cal) {
		System.out.println("post resources, salvo entita " + cal);
		return milestoneManager.save(cal);
	}

	@DELETE
	@Path("Milestones({id})")
	public void delete(@PathParam("id") Long id) {
		milestoneManager.remove(id);
	}

	@PUT
	@Path("Milestones({id})") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, Milestone m) {
		m.setIdMilestone(id);
		milestoneManager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Milestones/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Milestones");
		return "ok Milestones";
	}
	/* ---- TEST RESOURCES ---- */

}
