package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSPollingManager;
import it.finsoft.util.KeyNotFoundException;

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
	 * relativa tag, dice se la milestone è stata raggiunta oppure no. Ha senso
	 * sia per le milestone atomiche (l'evento è avvenuto?) sia per le aggregate
	 * (gli eventi prerequisiti sono avvenuti?).
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return Deve restituire 0 se l'evento non si è verificato, 1 se si è
	 *         verificato in parte (per le aggregate), 2 se si è verificato
	 *         completamente.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@QueryParam("milestone") String codiceMilestone, @QueryParam("tag") String tag) {

		// TODO sarebbe bello restituire il dettaglio degli eventi avvenuti...

		try {
			return Integer.toString(wsPolling.calcolaPolling(codiceMilestone, tag));
		} catch (KeyNotFoundException e) {
			return e.getMessage();
		}
	}

	/* ---- TEST RESOURCES ---- */
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Polling");
		return "ok Polling";
	}
}