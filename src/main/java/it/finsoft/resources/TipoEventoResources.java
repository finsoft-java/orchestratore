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

import it.finsoft.entity.TipoEvento;
import it.finsoft.manager.TipoEventoManager;

@Stateless
@Path("resources/calendariMilestones")
@Produces(MediaType.APPLICATION_JSON)
public class TipoEventoResources {
	
	@Inject
	TipoEventoManager manager;
	
    @GET
    public List<TipoEvento> findAll() {
        return manager.findAll();
    }
    
    @GET
    @Path("{id}")
    public TipoEvento findById(@PathParam("id") long id) {
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
    public TipoEvento create(TipoEvento te) {
        return manager.save(te);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") long id, TipoEvento te) {
        te.setIdTipo(id);
        manager.save(te);
    }
}