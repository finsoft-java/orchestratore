package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;
import it.finsoft.util.StringJsonResponse;

/**
 * Questo servizio, dati certi parametri, dovrebbe creare e fornire un codice di
 * partizione. Questo servizio dovrebbe essere sempre allineato con l'omonima
 * funzione di U7B20.
 * 
 */
@Stateless
@Path("PartCol")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPartCol {

	@Inject
	WSManager manager;

	@GET
	public StringJsonResponse get(@QueryParam("nomeTabella") String nomeTabella, @QueryParam("annoMese") String annoMese,
			@QueryParam("giro") String giro, @QueryParam("perimetro") String perimetro, @QueryParam("abi") String abi) {

		StringJsonResponse ret = new StringJsonResponse();
		try {

			String partCol = manager.getPartCol(nomeTabella, annoMese, giro, perimetro, abi);
			ret.data = partCol;

		} catch (Exception exc) {
			ret.errorCode = "1"; // FIXME
			ret.errorMessage = exc.getMessage();
		}
		return ret;
	}
}
