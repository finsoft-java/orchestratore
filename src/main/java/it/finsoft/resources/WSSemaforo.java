package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.manager.CalendarioMilestoneManager;
import it.finsoft.manager.WSManager;

@Stateless
@Path("Semaforo")
@Produces(MediaType.APPLICATION_JSON)
public class WSSemaforo {

	@Inject
	WSManager wsManager;
	
	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String Light(@PathParam("id") long idCalM) {
		CalendarioMilestone calendarioMilestone=calendarioMilestoneManager.findById(idCalM);
		return wsManager.getLight(calendarioMilestone) ? "1" : "0";
	}
}
