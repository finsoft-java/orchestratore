package it.finsoft.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;

@Stateless
@Path("Reset")
public class WSReset {

	@Inject
	WSManager wsManager;

	/**
	 * Questo e' un reset in GET quando si richiama la pagina, svuota le tabelle
	 * indicate nello script.sql e le ripopola, e' da ottimizzare ma per il
	 * momento funziona (ritorna un errore sulla prima istruzione)
	 * 
	 * Puo' essere sostituito da javax.persistence.sql-load-script-source
	 **/
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String reset() throws FileNotFoundException, IOException {
		return wsManager.resetDb();
	}

}
