package it.finsoft.resources;

import java.util.ArrayList;
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
import it.finsoft.entity.Azione;
import it.finsoft.entity.SemaforoMilestone;
import it.finsoft.entity.TipoEvento;

import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.SemaforoManager;
import it.finsoft.manager.UtilityChecker;

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
	@Inject
	UtilityChecker syntax;

	@GET
	public DatiPolling get(@QueryParam("semaforo") String codSemaforo, @QueryParam(value = "tag") List<String> tags) {

		DatiPolling result = new DatiPolling();

		LOG.info("Parametri di ricerca: Semaforo " + codSemaforo + " Tag " + tags);
		codSemaforo = syntax.trimToUp(codSemaforo);
		try {
			Azione azione = managerSem.findByCod(codSemaforo);
			List<SemaforoMilestone> semMilestones = azione.getSemaforoMilestones();
			result.expectedMilestones = semMilestones.size();
			System.out.println(semMilestones.size());
			System.out.println(semMilestones);
			for (int i = 0; i < semMilestones.size(); i++) {
				SemaforoMilestone sc = semMilestones.get(i);
				Milestone m = sc.getMilestone();
				String tag = tags.get(i);
				tag = syntax.trimToUp(tag);
				Entita ent = m.getEntita();
				TipoEvento tp = m.getTipoEvento();
				List<Evento> tmp = null;
				tmp = managerEvt.findPolling(tag, ent, tp);
				System.out.println(tmp);
				if (tmp.isEmpty()) {
					result.semaforoOk = Boolean.FALSE;
					result.tagNonVerificati.add(i + " - " + tag);
				} else {
					++result.okMilestones;
				}
				result.eventi.addAll(tmp);
			}
		} catch (Exception sqlError) {
			LOG.error("ERROR: il Semaforo: " + codSemaforo
					+ " non e' stato trovato, controllare la sintassi o la presenza effettiva sul database");
			result.errorMessage = "ERROR: il Semaforo: " + codSemaforo
					+ " non e' stato trovato, controllare la sintassi o la presenza effettiva sul database";
			result.semaforoOk = false;
		}
		return result;
	}

	public static class DatiPolling {
		public Boolean semaforoOk = Boolean.TRUE;
		public Integer expectedMilestones = 0;
		public Integer okMilestones = 0;
		public List<Evento> eventi = new ArrayList<>();
		public String errorMessage = null;
		public List<String> tagNonVerificati = new ArrayList<>();
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
}
