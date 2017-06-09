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
import it.finsoft.entity.Entita;
import it.finsoft.manager.EntitaManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class EntitaResources {

	@Inject
	EntitaManager manager;

	@GET
	@Path("Entita")
	public List<Entita> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("Entita({id})")
	public Entita findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Entita({id})")
	public Entita create(Entita cal) {
		System.out.println("post resources, salvo entita " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("Entita({id})")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("Entita({id})") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, Entita m) {
		m.setIdEntita(id);
		manager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Entita/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok Entita");
		return "ok Entita";
	}
	/* ---- TEST RESOURCES ---- */

}
