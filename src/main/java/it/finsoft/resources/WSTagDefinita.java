package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;
import it.finsoft.util.StringJsonResponse;

/**
 * Questo servizio risponde alla domanda: per la milestone (aggregata) data, è
 * stata correttamente definita la tag data? Per le milestone atomiche la
 * risposta è sempre positiva.
 */
@Stateless
@Path("secured/TagBenDefinita")
@Produces({ MediaType.APPLICATION_JSON })
public class WSTagDefinita {

	@Inject
	WSManager manager;

	@POST
	public StringJsonResponse get(@QueryParam("codiceMilestone") String codiceMilestone, @QueryParam("tag") String tag) {

		StringJsonResponse ret = new StringJsonResponse();
		try {

			boolean benDef = manager.tagBenDefinita(codiceMilestone, tag);
			ret.data = benDef ? "1" : "0";

		} catch (Exception exc) {
			ret.errorCode = "1"; // FIXME
			ret.errorMessage = exc.getMessage();
		}
		return ret;
	}
}
