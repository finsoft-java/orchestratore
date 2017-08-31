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
 * Questo servizio, dati certi parametri, dovrebbe creare e fornire un codice di
 * partizione. Questo servizio dovrebbe essere sempre allineato con l'omonima
 * funzione di U7B20.
 * 
 */
@Stateless
@Path("PartCol")
public class WSPartCol {

	@Inject
	WSManager manager;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response get(@FormParam("nomeTabella") String nomeTabella, @FormParam("annoMese") String annoMese,
			@FormParam("giro") String giro, @FormParam("perimetro") String perimetro, @FormParam("abi") String abi) {

		try {

			try {
				String partCol = manager.getPartCol(nomeTabella, annoMese, giro, perimetro, abi);
				return Response.ok(partCol, MediaType.TEXT_PLAIN).build();

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
