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
@Path("resources/entita")
@Produces(MediaType.APPLICATION_JSON)
public class EntitaResources {
	
	@Inject
	EntitaManager manager;
	
    @GET
    public List<Entita> findAll() {
        return manager.findAll();
    }
    
    @GET
    @Path("{id}")
    public Entita findById(@PathParam("id") long id) {
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
    public Entita create(Entita e) {
        return manager.save(e);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") long id, Entita e) {
        e.setIdEntita(id);
        manager.save(e);
    }
}