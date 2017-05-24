package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import it.finsoft.entity.Semaforo;
import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.SemaforoManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	EntitaManager managerEnt;
	@Inject
	SemaforoManager managerSem;
	@PersistenceContext
	EntityManager em;

	@GET
	// TODO: restituire sia un Boolean, sia una List<Evento>
	public Boolean get(

			@QueryParam("semaforo") String semaforo, @QueryParam(value = "tags") final List<String> tags) {

		System.out.println(semaforo + " " + tags);
		Semaforo Sm = managerSem.findByCod(semaforo);
		System.out.println(Sm.toString());
		Long idSm = Sm.getIdSemaforo();
		System.out.println(idSm);
		System.out.println(Sm.getSemaforiMilestones());
		// throw new UnsupportedOperationException("TODO");
		return null;
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok polling");
		return "ok polling";
	}
	/* ---- TEST RESOURCES ---- */

	/*
	 * public Response getEventoAsJSON(@Context HttpHeaders httpHeaders, Entita
	 * entita1) { List<Entita> ents = em.findAll(); ents.add(entita1);
	 * System.out.println("post resources, creo entita1 " + ents);
	 * em.save(entita1); System.out.println("post resources, salvo entita1 " +
	 * ents); return Response.ok(ents).build(); }
	 */

}
