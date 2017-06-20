package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSPollingManager;

@Stateless
@Path("Semaforo")
@Produces(MediaType.APPLICATION_JSON)
public class WSSemaforo {

	@Inject
	WSPollingManager wsManager;

	public final static Logger LOG = Logger.getLogger(WSManager.class);

	/**
	 * Questo è il servizio web del Semaforo: dato un codice milestone e una
	 * relativa tag, dice se il programma in questione è già stato eseguito
	 * oppure no.
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return 2 se è stato eseguito, 0 se non è stato eseguito, 1 se è una
	 *         milestone aggregata che è stata eseguita parzialmente.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int get(@QueryParam("milestone") String codiceMilestone, @QueryParam("tag") String tag) {
		return wsManager.calcolaSemaforo(codiceMilestone, tag);
	}

}
