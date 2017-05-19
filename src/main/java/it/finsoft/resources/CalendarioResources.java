package it.finsoft.resources;

import java.util.List;
import java.util.Objects;

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
import it.finsoft.entity.Calendario;
import it.finsoft.manager.CalendarioManager;

@Stateless
@Path("resources/calendari")
@Produces({ MediaType.APPLICATION_JSON })
public class CalendarioResources {

	@Inject
	CalendarioManager manager;

	@GET
	public List<Calendario> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("{id}")
	public Calendario findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Calendario create(Calendario cal) {
		System.out.println("post resources, salvo entita " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, Calendario m) {
		if (!Objects.equals(id, m.getIdCalendario())) {
			System.out.println("generare errore..");
		}
		manager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok calendari");
		return "ok calendari";
	}
	/* ---- TEST RESOURCES ---- */

}