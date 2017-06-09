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

import it.finsoft.entity.Calendario;
import it.finsoft.manager.CalendarioManager;

@Stateless
@Path("resources") //aggiunto il suffisso /calendari per testare la risoluzione dell'errore:
//Conflicting URI templates. The URI template /resources for root resource class it.finsoft.resources.CalendarioResources 
//and the URI template /resources transform to the same regular expression /resources(/.*)?
//causato dal fatto che non gli piace trovare due @Path identici (es: "resources")
@Produces({ MediaType.APPLICATION_JSON })
public class CalendarioResources {

	@Inject
	CalendarioManager manager;

	@GET
	@Path("Calendari")
	public List<Calendario> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("Calendari({id})")
	public Calendario findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Calendari({id})")
	public Calendario create(Calendario cal) {
		System.out.println("post resources, salvo entita " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("Calendari({id})")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("Calendari({id})") // richiede di inserire (in json) tutti i campi
								// obbligatori
	public void update(@PathParam("id") Long id, Calendario m) {
		m.setIdCalendario(id);
		manager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("Calendari/Test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok calendari");
		return "ok calendari";
	}
	/* ---- TEST RESOURCES ---- */

}
