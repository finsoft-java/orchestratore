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

import it.finsoft.entity.Semaforo;
import it.finsoft.manager.SemaforoManager;

@Stateless
@Path("resources/semafori")
@Produces({ MediaType.APPLICATION_JSON })
public class SemaforoResources {

	@Inject
	SemaforoManager manager;

	@GET
	@Path("{id}")
	public Semaforo findById(@PathParam("id") Long id) {
		return manager.findById(id);
	}

	@GET
	public List<Semaforo> findAll() {
		return manager.findAll();
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") long id) {
		manager.remove(id);
	}

	@POST
	@Path("crea")
	@Consumes(MediaType.APPLICATION_JSON)
	public Semaforo create(Semaforo te) {
		return manager.save(te);
	}

	@PUT
	@Path("{id}")
	public void update(@PathParam("id") long id, Semaforo s) {
		s.setIdSemaforo(id);
		manager.save(s);
	}
}
