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
@Path("resources/calendariMilestones")
@Produces(MediaType.APPLICATION_JSON)
public class CalendarioMilestoneResources {
	
	@Inject
	CalendarioMilestoneManager manager;
	
    @GET
    public List<CalendarioMilestone> findAll() {
        return manager.findAll();
    }
    
    @GET
    @Path("{id}")
    public CalendarioMilestone findById(@PathParam("id") long id) {
        return manager.findById(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
    	manager.remove(id);
    }
    @POST
    @Path("crea")
    @Consumes(MediaType.APPLICATION_JSON)
    public CalendarioMilestone create(CalendarioMilestone cm) {
        return manager.save(cm);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") long id, CalendarioMilestone cm) {
        cm.setIdCalendarioMilestone(id);
        manager.save(cm);
    }

}
