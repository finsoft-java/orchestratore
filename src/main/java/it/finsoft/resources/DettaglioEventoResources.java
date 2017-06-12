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

import it.finsoft.entity.DettaglioEvento;
import it.finsoft.manager.DettaglioEventoManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class DettaglioEventoResources {

	@Inject
	DettaglioEventoManager dettaglioEventoManager;

	@GET
	@Path("DettagliEvento")
	public List<DettaglioEvento> findAll() {
		return dettaglioEventoManager.findAll();
	}

	@GET
	@Path("DettagliEvento({id})")
	public DettaglioEvento findById(@PathParam("id") Long id) {
		return dettaglioEventoManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("DettagliEvento({id})")
	public DettaglioEvento create(DettaglioEvento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return dettaglioEventoManager.save(cal);
	}

	@DELETE
	@Path("DettagliEvento({id})")
	public void delete(@PathParam("id") Long id) {
		dettaglioEventoManager.remove(id);
	}

	@PUT
	@Path("DettagliEvento({id})") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, DettaglioEvento m) {
		m.setIdDettaglioEvento(id);
		dettaglioEventoManager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("DettagliEvento/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok DettagliEvento");
		return "ok DettagliEvento";
	}
	/* ---- TEST RESOURCES ---- */

}
