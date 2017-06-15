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
import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.MilestoneMilestoneManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class MilestoneMilestoneResources {

	@Inject
	MilestoneMilestoneManager milestoneMilestonesManager;

	@Inject
	MilestoneManager milestoneManager;

	@GET
	@Path("MilestoneMilestones")
	public List<MilestoneMilestone> findAll() {
		return milestoneMilestonesManager.findAll();
	}

	@GET
	@Path("MilestoneMilestones({idM},{idCh})")
	public MilestoneMilestone findById(@PathParam("idM") Long id, @PathParam("idCh") Long idCh) {
		return milestoneMilestonesManager.findById(id, idCh);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("MilestoneMilestones")
	public MilestoneMilestone create(MilestoneMilestone cal) {
		System.out.println("post resources, salvo semaforomilestone " + cal);
		return milestoneMilestonesManager.preCheck(cal);
	}

	@DELETE
	@Path("MilestoneMilestones({idM},{idCh})")
	public void delete(@PathParam("idM") Long id, @PathParam("idCh") Long idCh) {
		milestoneMilestonesManager.remove(id, idCh);
	}

	@PUT
	@Path("MilestoneMilestones({id},{idCh})")
	// richiede di inserire (in json) tutti i campi obbligatori
	// Bisogna vedere se va bene per l'update via interfaccia
	public void update(@PathParam("id") Long id, @PathParam("idCh") Long idCh, MilestoneMilestone m) {
		m.setMilestone(milestoneManager.findById(id));
		m.setMilestoneChild(milestoneManager.findById(idCh));
		milestoneMilestonesManager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("MilestoneMilestones/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok MilestoneMilestones");
		return "ok MilestoneMilestones";
	}
	/* ---- TEST RESOURCES ---- */

}
