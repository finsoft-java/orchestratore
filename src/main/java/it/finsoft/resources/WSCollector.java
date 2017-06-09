package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSManager.DatiCollector;

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
	public DatiCollector create(@QueryParam("entita") String codiceEnt, @QueryParam("tipiEvento") String codiceTipi,
			@QueryParam("tag") String tag, @QueryParam("key") List<String> keys,
			@QueryParam("valore") List<String> values) {
		
		return wsManager.getCollector(codiceEnt, codiceTipi, tag, keys, values);

	}
	
	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Collector/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok collector");
		return "ok collector";
	}
	/* ---- TEST RESOURCES ---- */

}
