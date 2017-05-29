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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.CalendarioSemaforo;
import it.finsoft.manager.CalendarioManager;
import it.finsoft.manager.CalendarioSemaforoManager;
import it.finsoft.manager.SemaforoManager;

@Stateless
@Path("resources/calendarisemafori")
@Produces({ MediaType.APPLICATION_JSON })
public class CalendarioSemaforoResources {

	@Inject
	CalendarioSemaforoManager manager;

	/* test inserimento calendari semafori */
	CalendarioSemaforoManager csm;
	CalendarioManager cm;
	SemaforoManager sm;
	/* test inserimento calendari semafori */

	@GET
	public List<CalendarioSemaforo> findAll() {
		return manager.findAll();
	}

	@GET
	@Path("{id}")
	public CalendarioSemaforo findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public CalendarioSemaforo create(CalendarioSemaforo cal) {
		System.out.println("post resources, salvo entita " + cal);
		return manager.save(cal);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		manager.remove(id);
	}

	@PUT
	@Path("{id}") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, CalendarioSemaforo m) {
		m.setIdCalendarioSemaforo(id);
		manager.save(m);
	}
	/*
	 * { "milestone" : { "idMilestone" : 1 }, "calendario" : { "idCalendario" :
	 * 1 }, "tag" : "fasdfafa" }
	 */


	/* Aggiunta per inserimento in get via http */
	@GET
	@Path("inscalsem")
	public CalendarioSemaforo create(@QueryParam("tags") String tags, @QueryParam("semaforo") Long ids,
			@QueryParam("calendario") Long idc) {
		CalendarioSemaforo cals = new CalendarioSemaforo();
		cals.setSemaforo(sm.findById(ids));
		cals.setCalendario(cm.findById(idc));
		cals.setTags(tags);
		;
		return csm.save(cals);
	}
	/* Aggiunta per inserimento in get via http */


	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok calendarisemafori");
		return "ok calendarimilestones";
	}
	/* ---- TEST RESOURCES ---- */

}
