package it.finsoft.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import it.finsoft.entity.Azione;
import it.finsoft.manager.AzioneManager;


@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class AzioneResources {

	@Inject
	AzioneManager azioneManager;
	
	@GET
	@Path("Azioni")
	public List<Azione> findAll() {
		return azioneManager.findAll();
	}

	@GET
	@Path("Azioni({id})")
	public Azione findById(@PathParam("id") Long id) {
		return azioneManager.findById(id);
	}
	
	@POST
	@Path("Azioni({id})")
	@Consumes(MediaType.APPLICATION_JSON)
	public Azione create(Azione cal) {
		System.out.println("post resources, salvo azione " + cal);
		return azioneManager.save(cal);
	}

	@DELETE
	@Path("Azioni({id})")
	public void delete(@PathParam("id") Long id) {
		azioneManager.remove(id);
	}

	@PUT
	@Path("Azioni({id})")//richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, Azione m) {
		m.setIdAzione(id);
		azioneManager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Azioni/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Azioni");
		return "ok Azioni";
	}
	/* ---- TEST RESOURCES ---- */

}
