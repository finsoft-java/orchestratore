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
import it.finsoft.manager.WSPollingManager;

@Stateless
@Path("Polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	WSManager wsManager;

	@Inject
	MilestoneManager milestoneManager;

	@Inject
	WSPollingManager wsPolling;

	/**
	 * Questo è il servizio web del Polling: dato un codice milestone e una
	 * relativa tag, dice se il programma in questione può partire oppure no.
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return 1 se può partire, 0 in caso contrario
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@QueryParam("milestone") String codiceMilestone, @QueryParam("tag") String tag) {
		return wsPolling.calcolaPolling(codiceMilestone, tag) ? "1" : "0";
	}

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