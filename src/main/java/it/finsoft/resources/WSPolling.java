package it.finsoft.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
	SemaforoManager managerSem;
	/*
	 * @PersistenceContext EntityManager em;
	 */
	@Inject
	EventoManager managerEvt;

	/*
	 * @GET // TODO: restituire sia un Boolean, sia una List<Evento> public
	 * List<Evento> get(
	 * 
	 * @QueryParam("semaforo") String semaforo, @QueryParam(value = "tags")
	 * List<String> tags) {
	 * 
	 * System.out.println("Parametri di ricerca: Semaforo " + semaforo +
	 * " Tags " + tags); // Non visualizza i tags BLOCCANTE PER LA RICERCA
	 * Semaforo Sm = managerSem.findByCod(semaforo);
	 * System.out.println(tags.size()); // Tags [] vuoto??? // RISOLTO, input
	 * name cambiato da tag a tags sull'index.jsp Collection<Milestone> test =
	 * Sm.getSemaforiMilestones(); // thr for (Milestone milestone : test) {
	 * Entita ent = milestone.getEntita(); TipoEvento tp =
	 * milestone.getTipoEvento(); } return null; }
	 */

	@GET
	public DatiPolling get(@QueryParam("semaforo") String semaforo, @QueryParam(value = "tag") List<String> tags) {

		DatiPolling result = new DatiPolling();
		result.semaforoOk = Boolean.TRUE;
		result.eventi = new ArrayList<>();
		System.out.println("Parametri di ricerca: Semaforo " + semaforo + " Tag " + tags);
		// Non visualizza i tags BLOCCANTE PER LA RICERCA
		Semaforo Sm = managerSem.findByCod(semaforo);
		// System.out.println(tags.size()); // Tags [] vuoto???
		// RISOLTO, value cambiato da tag a tags nel QueryParam del metodo GET
		Collection<Milestone> milestones = Sm.getSemaforiMilestones();
		System.out.println(milestones.size());
		System.out.println(tags.size());
		// throw new UnsupportedOperationException("TODO");
		String tag = "";
		int i = 0;
		for (Milestone milestone : milestones) {
			//Sembra funzionare, prevede solamente che i tag siano inseriti in ordine, alternativamente devo attrezzarmi per uno scorrimento.
				tag = tags.get(i);
				System.out.println(tag);
				Entita ent = milestone.getEntita();
				TipoEvento tp = milestone.getTipoEvento();
				// for (String tag : tags) {
				List<Evento> tmp = null;
				tmp = managerEvt.findPolling(tag, ent, tp);
				System.out.println(tmp);
				i++;
				if (tmp.isEmpty()) {
					result.semaforoOk = Boolean.FALSE;
				}
				result.eventi.addAll(tmp);

				// }
			}
		
		return result;
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

	public static class DatiPolling {
		public Boolean semaforoOk;
		public List<Evento> eventi;
	}
}
