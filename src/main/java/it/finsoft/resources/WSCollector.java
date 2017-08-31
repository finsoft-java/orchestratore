package it.finsoft.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import it.finsoft.manager.WSManager;
import it.finsoft.util.BusinessException;

@Stateless
@Path("secured/Collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {

	@Inject
	WSManager wsManager;

	public final static Logger LOG = Logger.getLogger(WSManager.class);

	/**
	 * Metodo POST per inserire dati via HTTP
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertEvent(@FormParam("entita") String codiceEntita,
			@FormParam("tipoEvento") String codiceTipoEvento, @FormParam("tag") String tag, @Context UriInfo uriInfo) {

		try {
			MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
			Map<String, String> dettagli = new HashMap<String, String>();
			for (String key : queryParams.keySet()) {
				if (!"entita".equals(key) && !"tipiEvento".equals(key) && !"tag".equals(key)) {
					dettagli.put(key, queryParams.getFirst(key));
				}
			}

			try {
				wsManager.insertEvento(codiceEntita, codiceTipoEvento, tag, dettagli);
				return Response.ok().build();

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
