package it.finsoft.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("reset")
public class WSReset {

	@PersistenceContext
	EntityManager manager;
	/*
	 * Questo e' un reset in GET quando si richiama la pagina, svuota le tabelle
	 * indicate nello scriptsql.txt e le ripopola, e' da ottimizzare ma per il
	 * momento funziona (ritorna un errore sulla prima istruzione)
	 */

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String reset() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("C:/workspace/orchestratore/src/main/resources/scriptsql.txt"));

		String sql = "";
		while (reader.ready() == true) {
			try {
				sql = reader.readLine().toString();
				Query q = manager.createNativeQuery(sql);
				q.executeUpdate();
				System.out.println(sql);
			} catch (Exception e) {
				break;
			}
		}
		reader.close();
		System.out.println("RESET effettuato con successo");
		return "RESET dati predefiniti DB effettuato";
	}
  
}
