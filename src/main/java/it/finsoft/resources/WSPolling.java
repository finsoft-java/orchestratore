package it.finsoft.resources;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.Semaforo;
import it.finsoft.entity.TipoEvento;
import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.SemaforoManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	@Inject
	EntitaManager managerEnt;
	@Inject
	EventoManager managerEvn;
	@Inject
	SemaforoManager managerSem;
	@PersistenceContext
	EntityManager em;

	@GET
	// TODO: restituire sia un Boolean, sia una List<Evento>
	public Collection<Milestone> get(

		@QueryParam("semaforo") String semaforo, @QueryParam(value = "tags") final List<String> tags) {

		//System.out.println(semaforo + " " + tags);
		Semaforo Sm = managerSem.findByCod(semaforo);
		//System.out.println(Sm.toString());
		Long idSm = Sm.getIdSemaforo();
		System.out.println(idSm);
		//System.out.println(Sm.getSemaforiMilestones());
		// throw new UnsupportedOperationException("TODO");
		Collection<Milestone> test = Sm.getSemaforiMilestones();
		for (Milestone milestone : test) {
			Entita ent = milestone.getEntita();
			TipoEvento tp = milestone.getTipoEvento();	
			List<Evento> tmp = em.createQuery("FROM Evento WHERE entita = :ent AND :tipoEvento = :tp", Evento.class)
					.setParameter("ent", ent)
					.setParameter("tp", tp)
					.getResultList();
			System.out.println(tmp);
		}
		
		
		/*
		Entita ent = ml;
		TipoEvento tp = ml.getTipoEvento();
		
		tmp = em.createQuery("FROM Evento WHERE entita = :ent AND :tipoEvento = :tp", Evento.class)
				.setParameter("ent", ent)
				.setParameter("tp", tp)
				.getResultList();		
		System.out.println(tmp);*/
		return test;
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
