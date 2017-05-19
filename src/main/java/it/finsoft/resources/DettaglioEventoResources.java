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
@Path("resources/calendariMilestones")
@Produces(MediaType.APPLICATION_JSON)
public class DettaglioEventoResources {
	
	@Inject
	DettaglioEventoManager manager;
	
    @GET
    public List<DettaglioEvento> findAll() {
        return manager.findAll();
    }
    
    @GET
    @Path("{id}")
    public DettaglioEvento findById(@PathParam("id") long id) {
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
    public DettaglioEvento create(DettaglioEvento de) {
        return manager.save(de);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") long id, DettaglioEvento de) {
        de.setidDettaglioEvento(id);
        manager.save(de);
    }
}