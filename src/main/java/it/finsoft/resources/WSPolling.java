package it.finsoft.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Milestone;
import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.WSManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	WSManager wsManager;

	@Inject
	MilestoneManager managerMil;

	// -------------------------Precedente-Metodo-polling------------------------//
	/*
	 * @GET public DatiPolling get(@QueryParam("milestone") String
	 * descMilestone,
	 * 
	 * @QueryParam(value = "tag") List<String> tags) {
	 * 
	 * return wsManager.getPollingOld(descMilestone, tags);
	 * 
	 * }
	 */

	// -------------------------PollingFoglie----------------------//

	@GET
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean get(@QueryParam("milestone") String descMilestone, @QueryParam(value = "tag") List<String> tags) {
		return wsManager.getPollingFoglieByDescr(descMilestone, tags);
	}

	// ---------------------------Polling1L------------------------------------------//
	// Polling di 1' Livello con 2 routine
	/*
	 * @GET
	 * @Produces(MediaType.TEXT_PLAIN) public boolean
	 * get(@QueryParam("milestone") String descMilestone, @QueryParam(value =
	 * "tag") List<String> tags) { return wsManager.getPolling1LByDescr(descMilestone,
	 * tags); }
	 */

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public String prova() {
		System.out.println("ok polling");
		return "ok polling";
	}
	/* ---- TEST RESOURCES ---- */

	// TEST per l'esplosione della gerarchia
	@GET
	@Path("testTree")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Milestone> Hierarchy(@QueryParam("milestone") String descMilestone) {
		Milestone m = managerMil.findByDesc(descMilestone.toUpperCase());
		return managerMil.getHierarchy(m);
	}

	// TEST per l'esplosione solo delle foglie
	@GET
	@Path("testLeaf")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Milestone> Leaf(@QueryParam("milestone") String descMilestone) {
		Milestone m = managerMil.findByDesc(descMilestone.toUpperCase());
		return managerMil.getFoglie(m);
	}
}