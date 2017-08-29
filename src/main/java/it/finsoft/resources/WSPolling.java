package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSPollingManager;
import it.finsoft.util.KeyNotFoundException;
import it.finsoft.util.StringJsonResponse;

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

	public final static Logger LOG = Logger.getLogger(WSManager.class);

	/**
	 * Questo e' il servizio web del Polling: dato un codice milestone e una
	 * relativa tag, dice se la milestone e' stata raggiunta oppure no. Ha senso
	 * sia per le milestone atomiche (l'evento e' avvenuto?) sia per le
	 * aggregate (gli eventi prerequisiti sono avvenuti?).
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return Deve restituire 0 se l'evento non si e' verificato, 1 se si e'
	 *         verificato in parte (per le aggregate), 2 se si e' verificato
	 *         completamente.
	 */
	@GET
	public StringJsonResponse get(@QueryParam("milestone") String codiceMilestone, @QueryParam("tag") String tag) {

		// TODO sarebbe bello restituire il dettaglio degli eventi avvenuti...
		StringJsonResponse ret = new StringJsonResponse();

		try {
			ret.data = Integer.toString(wsPolling.calcolaPolling(codiceMilestone, tag));

		} catch (KeyNotFoundException e) {
			ret.errorCode = "20";
			ret.errorMessage = e.getMessage();

		} catch (Exception exc) {
			LOG.error("Unexpected exception while calculating polling", exc);
			ret.errorCode = "1"; // FIXME
			ret.errorMessage = exc.getMessage();
		}

		return ret;
	}

	/* ---- TEST RESOURCES ---- */
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Polling");
		return "ok Polling";
	}
}