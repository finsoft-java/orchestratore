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
 * Questo servizio, dati certi parametri, dovrebbe creare e fornire un codice di partizione.
 * Questo servizio dovrebbe essere sempre allineato con l'omonima funzione di U7B20.
 *
 */
@Stateless
@Path("PartCol")
@Produces({ MediaType.TEXT_PLAIN })
public class WSPartCol {

	@Inject
	WSManager manager;

	@GET
	public String get(@QueryParam("anno") String anno, @QueryParam("mese") String mese, @QueryParam("giro") String giro,
			@QueryParam("lettera") String lettera) {

		// TODO
		// es. P2017051LS
		// Ma devo accedere alla funzione GET_PART_COL di U7B20...
		return "TODO";
	}
}
