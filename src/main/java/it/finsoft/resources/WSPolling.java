package it.finsoft.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSManager.DatiPolling;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	WSManager wsManager;

	@GET
	public DatiPolling get(@QueryParam("milestone") String descMilestone,
			@QueryParam(value = "tag") List<String> tags) {
		
		return wsManager.getPolling(descMilestone, tags);

	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok polling");
		return "ok polling";
	}
	/* ---- TEST RESOURCES ---- */
}
