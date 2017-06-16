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
import it.finsoft.manager.WSNewPollingFoglie;
import it.finsoft.manager.WSNewPollingPredeccesoriDiretti;

@Stateless
@Path("Polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	WSManager wsManager;

	@Inject
	MilestoneManager milestoneManager;

	@Inject
	WSNewPollingFoglie wsPollingFoglie;

	@Inject
	WSNewPollingPredeccesoriDiretti wsPollingPred;

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

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String
	 * get(@QueryParam("milestone") String descMilestone, @QueryParam(value =
	 * "tag") List<String> tags) { return
	 * wsManager.getPollingFoglieByDescr(descMilestone, tags) ? "1" : "0"; }
	 */

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@QueryParam("milestone") String descMilestone, @QueryParam("tag") String tag) {
		return wsPollingFoglie.getPollingByDesc(descMilestone, tag) ? "1" : "0";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("Polling2")
	public String getV2(@QueryParam("milestone") String descMilestone, @QueryParam("tag") String tag) {
		return wsPollingPred.getPollingByDesc(descMilestone, tag) ? "1" : "0";
	}
	// ---------------------------Polling1L------------------------------------------//
	// Polling di 1' Livello con 2 routine
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public boolean
	 * get(@QueryParam("milestone") String descMilestone, @QueryParam(value =
	 * "tag") List<String> tags) { return
	 * wsManager.getPolling1LByDescr(descMilestone, tags); }
	 */

	/* ---- TEST RESOURCES ---- */
	@Path("Polling/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Polling");
		return "ok Polling";
	}

	// TEST per l'esplosione della gerarchia
	@GET
	@Path("testTree")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Milestone> Hierarchy(@QueryParam("milestone") String descMilestone) {
		Milestone m = milestoneManager.findByCod(descMilestone.toUpperCase());
		return milestoneManager.getHierarchy(m);
	}

	// TEST per l'esplosione solo delle foglie
	@GET
	@Path("testLeaf")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Milestone> Leaf(@QueryParam("milestone") String descMilestone) {
		Milestone m = milestoneManager.findByCod(descMilestone.toUpperCase());
		return milestoneManager.getFoglie(m);
	}
}