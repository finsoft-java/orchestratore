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
import it.finsoft.entity.Evento;
import it.finsoft.manager.EventoManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class EventoResources {

	@Inject
	EventoManager manager;

	@GET
	@Path("Eventi")
	public List<Evento> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("Eventi({id})")
	public Evento findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Path("Eventi({id})")
	@Consumes(MediaType.APPLICATION_JSON)
	public Evento create(Evento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("Eventi({id})")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("Eventi({id})") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, Evento m) {
		m.setIdEvento(id);
		manager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Eventi/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok eventi");
		return "ok eventi";
	}
	/* ---- TEST RESOURCES ---- */
}
