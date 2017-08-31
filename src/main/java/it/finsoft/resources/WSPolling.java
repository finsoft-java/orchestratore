package it.finsoft.resources;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSPollingManager;
import it.finsoft.util.BusinessException;

@Stateless
@Path("Polling")
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
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response get(@FormParam("milestone") String codiceMilestone, @FormParam("tag") String tag) {

		try {

			try {
				String data = Integer.toString(wsPolling.calcolaPolling(codiceMilestone, tag));
				return Response.ok(data, MediaType.TEXT_PLAIN).build();

			} catch (EJBTransactionRolledbackException e) {
				// Brutto sistema per catturare la BusinessException dentro una
				// EJB
				if (e.getCause() != null && e.getCause().getCause() != null
						&& e.getCause().getCause() instanceof BusinessException) {

					throw (BusinessException) e.getCause().getCause();

				} else {
					throw e;
				}
			}

		} catch (BusinessException exc) {
			return Response.status(Response.Status.BAD_REQUEST).entity(exc.getMessage()).build();

		} catch (Exception exc) {
			LOG.error("Unexpected exception while calculating polling", exc);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}