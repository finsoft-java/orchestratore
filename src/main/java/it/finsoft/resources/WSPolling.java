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

import org.jboss.logging.Logger;

import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.Semaforo;
import it.finsoft.entity.SemaforoMilestone;
import it.finsoft.entity.TipoEvento;

import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.SemaforoManager;
import it.finsoft.manager.SemaforoMilestoneManager;

@Stateless
@Path("polling")
@Produces({ MediaType.APPLICATION_JSON })
public class WSPolling {

	public static final Logger LOG = Logger.getLogger(WSPolling.class);

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
	public DatiPolling get(@QueryParam("semaforo") String codSemaforo, @QueryParam(value = "tag") List<String> tags) {

		DatiPolling result = new DatiPolling();

		LOG.info("Parametri di ricerca: Semaforo " + codSemaforo + " Tag " + tags);
		Semaforo semaforo = managerSem.findByCod(codSemaforo);
		// List<SemaforoMilestone> SMilestone = managerSM.findBySemaforo(Sm);
		List<SemaforoMilestone> semMilestones = semaforo.getSemaforoMilestones();// getSemaforoMilestones
																					// vuoto???
		result.expectedMilestones = semMilestones.size();
		System.out.println(semMilestones.size());
		int i = 0; // temporaneamente ripristinato il contatore i, da FIXARE
		for (SemaforoMilestone sc : semMilestones) {
			// Sembra funzionare, prevede solamente che i tag siano inseriti in
			// ordine, alternativamente devo attrezzarmi per uno scorrimento.
			// tag = tags.get(i);
			Milestone m = sc.getMilestone();
			String tag = tags.get(i);
			// System.out.println(tag);
			Entita ent = m.getEntita();
			TipoEvento tp = m.getTipoEvento();
			// for (String tag : tags) {
			List<Evento> tmp = null;
			tmp = managerEvt.findPolling(tag, ent, tp);
			System.out.println(tmp);
			if (tmp.isEmpty()) {
				result.semaforoOk = Boolean.FALSE;
			} else {
				++result.okMilestones;
			}
			result.eventi.addAll(tmp);
			i++;
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

	public static class DatiPolling {
		public Boolean semaforoOk = Boolean.TRUE;
		public Integer expectedMilestones = 0;
		public Integer okMilestones = 0;
		public List<Evento> eventi = new ArrayList<>();
		public String errorMessage = null;
	}
}
