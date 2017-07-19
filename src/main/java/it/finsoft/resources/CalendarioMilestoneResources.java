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

import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.manager.CalendarioMilestoneManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class CalendarioMilestoneResources {

	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;

	@GET
	@Path("CalendarioMilestones")
	public List<CalendarioMilestone> findAll() {
		return calendarioMilestoneManager.findAll();
	}

	@GET
	@Path("CalendarioMilestones({id})")
	public CalendarioMilestone findById(@PathParam("id") Long id) {
		return calendarioMilestoneManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("CalendarioMilestones")
	public CalendarioMilestone create(CalendarioMilestone cal) {
		System.out.println("post resources, salvo entita " + cal);
		return calendarioMilestoneManager.save(cal);
	}

	@DELETE
	@Path("CalendarioMilestones({id})")
	public void delete(@PathParam("id") Long id) {
		calendarioMilestoneManager.remove(id);
	}

	@PUT
	@Path("CalendarioMilestones({id})") // richiede di inserire (in json) tutti i
										// campi obbligatori
	public void update(@PathParam("id") Long id, CalendarioMilestone m) {
		m.setIdCalendarioMilestone(id);
		calendarioMilestoneManager.save(m);
	}
	/*
	 * { "milestone" : { "idMilestone" : 1 }, "calendario" : { "idCalendario" :
	 * 1 }, "tag" : "fasdfafa" }
	 */

	/* ---- RELAZIONI ---- */
	
	@GET
	@Path("Calendari({idc})/Milestone")
	public List<CalendarioMilestone> findByIdCalendario(@PathParam("idc") Long idCalendario) {
		return calendarioMilestoneManager.findByIdCalendario(idCalendario);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Calendari({idc})/Milestone")
	public CalendarioMilestone create(@PathParam("idc") Long idCalendario, CalendarioMilestone cal) {
		return calendarioMilestoneManager.save(idCalendario, cal);
	}
	
	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("CalendarioMilestones/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok CalendarioMilestones");
		return "ok CalendarioMilestones";
	}
	/* ---- TEST RESOURCES ---- */

}
