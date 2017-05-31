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
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;

import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.MilestoneManager;
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
	MilestoneManager managerMil;
	@Inject
	EventoManager managerEvt;
	@Inject
	UtilityChecker syntax;

	@GET
	public DatiPolling get(@QueryParam("milestone") String descMilestone, @QueryParam(value = "tag") List<String> tags) {

		DatiPolling result = new DatiPolling();

		LOG.info("Parametri di ricerca: Semaforo " + descMilestone + " Tag " + tags);
		descMilestone = syntax.trimToUp(descMilestone);
		try {
			Milestone milestone = managerMil.findByDesc(descMilestone); //esempio se descrizione e' un campo univoco (bisognera crearne uno ex CODICE)
			List<MilestoneMilestone> milestoneMilestones = milestone.getMilestoneMilestone();
			result.expectedMilestones = milestoneMilestones.size();
			System.out.println(milestoneMilestones.size());
			System.out.println(milestoneMilestones);
			for (int i = 0; i < milestoneMilestones.size(); i++) {
				MilestoneMilestone sc = milestoneMilestones.get(i);
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
			LOG.error("ERROR: il Semaforo: " + descMilestone
					+ " non e' stato trovato, controllare la sintassi o la presenza effettiva sul database");
			result.errorMessage = "ERROR: il Semaforo: " + descMilestone
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
