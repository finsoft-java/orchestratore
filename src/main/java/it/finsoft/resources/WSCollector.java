package it.finsoft.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import it.finsoft.manager.WSManager;
import it.finsoft.util.CommonJsonResponse;

@Stateless
@Path("Collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {

	@Inject
	WSManager wsManager;

	/**
	 * Metodo GET per inserire dati via http esegue due query per risolvere dal
	 * codiceEnt a Entita e da codiceTipi a TipoEvento
	 */
	@GET
	public CommonJsonResponse insertEvent(@QueryParam("entita") String codiceEntita,
			@QueryParam("tipoEvento") String codiceTipoEvento, @QueryParam("tag") String tag, @Context UriInfo uriInfo) {

		CommonJsonResponse ret = new CommonJsonResponse();

		try {
			MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
			Map<String, String> dettagli = new HashMap<String, String>();
			for (String key : queryParams.keySet()) {
				if (!"entita".equals(key) && !"tipiEvento".equals(key) && !"tag".equals(key)) {
					dettagli.put(key, queryParams.getFirst(key));
				}
			}

			wsManager.insertEvento(codiceEntita, codiceTipoEvento, tag, dettagli);

		} catch (Exception exc) {
			ret.errorCode = "1"; // FIXME
			ret.errorMessage = exc.getMessage();
		}

		return ret;
		// FIXME con JAX-RS 1 ho dovuto inibire l'output DatiCollector

	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok collector");
		return "ok collector";
	}
	/* ---- TEST RESOURCES ---- */

}
