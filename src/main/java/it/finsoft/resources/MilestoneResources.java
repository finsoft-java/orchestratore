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

import it.finsoft.entity.Milestone;
import it.finsoft.manager.MilestoneManager;

@Stateless
@Path("resources/calendariMilestones")
@Produces(MediaType.APPLICATION_JSON)
public class MilestoneResources {
	
	@Inject
	MilestoneManager manager;
	
    @GET
    public List<Milestone> findAll() {
        return manager.findAll();
    }
    
    @GET
    @Path("{id}")
    public Milestone findById(@PathParam("id") long id) {
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
    public Milestone create(Milestone m) {
        return manager.save(m);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") long id, Milestone m) {
    	m.setidMilestone(id);
        manager.save(m);
    }
}
