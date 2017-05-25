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
	EventoManager managerEvn;
	@Inject
	SemaforoManager managerSem;
	@Inject
	EventoManager managerEvt;
	/*
	 * @PersistenceContext EntityManager em;
	 */

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

		// TODO: restituire sia un Boolean, sia una List<Evento>

		Semaforo Sm = managerSem.findByCod(semaforo);

		// System.out.println(tags.size()); // Tags [] vuoto???
		// RISOLTO, value cambiato da tag a tags nel QueryParam del metodo GET
		Collection<Milestone> milestones = Sm.getSemaforiMilestones();
		result.expectedMilestones = milestones.size();
		System.out.println(milestones.size());
		System.out.println(tags.size());
		// throw new UnsupportedOperationException("TODO");
		String tag = "";
		int i = 0;
		result.okMilestones = 0;
		for (Milestone milestone : milestones) {
			// Sembra funzionare, prevede solamente che i tag siano inseriti in
			// ordine, alternativamente devo attrezzarmi per uno scorrimento.
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
			} else {
				++result.okMilestones;
			}
			result.eventi.addAll(tmp);

			// }
		}

		return result;
	}
	/*
	 * ricerca CODICE + TAG
	 * 
	 * @GET // TODO: restituire sia un Boolean, sia una List<Evento> public
	 * Collection<Milestone> get(
	 * 
	 * @QueryParam("semaforo") String semaforo, @QueryParam(value = "tags")
	 * final List<String> tags) {
	 * 
	 * // System.out.println(semaforo + " " + tags); Semaforo Sm =
	 * managerSem.findByCod(semaforo); // System.out.println(Sm.toString());
	 * Evento ev = (Evento) managerEvn.findByTag(tags); Long idSm =
	 * Sm.getIdSemaforo(); System.out.println(idSm); //
	 * System.out.println(Sm.getSemaforiMilestones()); // throw new
	 * UnsupportedOperationException("TODO"); Collection<Milestone> test =
	 * Sm.getSemaforiMilestones(); for (Milestone milestone : test) { Entita ent
	 * = milestone.getEntita();
	 * 
	 * 
	 * String evn = ev.getTag();
	 * 
	 * List<Evento> tmp =
	 * em.createQuery("FROM Evento WHERE entita = :ent AND tag = :evn",
	 * Evento.class) .setParameter("ent", ent).setParameter("evn", evn)
	 * .getResultList(); System.out.println(tmp); }
	 * 
	 * return test; }
	 */

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok polling");
		return "ok polling";
	}
	/* ---- TEST RESOURCES ---- */

	public static class DatiPolling {
		public Boolean semaforoOk;
		public Integer expectedMilestones;
		public Integer okMilestones;
		public List<Evento> eventi;
	}
}
