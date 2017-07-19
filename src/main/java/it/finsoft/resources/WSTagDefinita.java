package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;

/**
 * Questo servizio risponde alla domanda: per la milestone (aggregata) data, è
 * stata correttamente definita la tag data? Per le milestone atomiche la
 * risposta è sempre positiva.
 */
@Stateless
@Path("TagBenDefinita")
@Produces({ MediaType.TEXT_PLAIN })
public class WSTagDefinita {

	@Inject
	WSManager manager;
	
	@GET
	public Boolean get(@QueryParam("codiceMilestone") String codiceMilestone, @QueryParam("tag") String tag) {

		return manager.tagBenDefinita(codiceMilestone, tag);
	}
}
