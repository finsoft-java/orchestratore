package it.finsoft.resources;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.finsoft.manager.WSManager;
import it.finsoft.util.BusinessException;

/**
 * Questo servizio risponde alla domanda: per la milestone (aggregata) data, è
 * stata correttamente definita la tag data? Per le milestone atomiche la
 * risposta è sempre positiva.
 */
@Stateless
@Path("TagBenDefinita")
public class WSTagDefinita {

	@Inject
	WSManager manager;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response get(@FormParam("codiceMilestone") String codiceMilestone, @FormParam("tag") String tag) {

		try {

			try {
				boolean data = manager.tagBenDefinita(codiceMilestone, tag);
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
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
