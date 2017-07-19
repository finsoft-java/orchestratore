package it.finsoft.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneConSemaforo;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;

public class WSPollingManager {
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@Inject
	MilestoneManager milestoneManager;

	@Inject
	UtilityCheck utilityCheck;

	@Inject
	CalendarioManager calendarioManager;

	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;

	@Inject
	EventoManager eventoManager;

	public final static Logger LOG = Logger.getLogger(WSManager.class);

	// ------------------------------------Polling0--------------------------------------//

	// FIXME obsoleta
	public MilestoneConSemaforo getPolling0(Milestone milestone, String tag) {
		MilestoneConSemaforo result = new MilestoneConSemaforo();
		result.setIdMilestone(milestone.getIdMilestone());
		result.setTipoEvento(milestone.getTipoEvento());
		result.setEntita(milestone.getEntita());
		result.setDescrizione(milestone.getDescrizione());
		result.setAzione(milestone.getAzione());
		result.getTags().add(tag);

		LOG.info("Parametri di ricerca: Milestone " + milestone + " Tag " + tag);

		List<Evento> tmp = null;
		tmp = eventoManager.findBy(tag, milestone.getEntita(), milestone.getTipoEvento());
		System.out.println(tmp);
		if (tmp.isEmpty()) {
			result.setSemaforo(false);
		} else {
			result.setSemaforo(true);
			result.getEventi().addAll(tmp);
		}

		return result;
	}

	// ------------------------------------PollingFoglie--------------------------------------//
	// controlla che si siano verificati tutti gli eventi presenti al livello
	// pi� basso dell'albero
	// controlla che si siano verificati tutti gli eventi presenti al livello
	// pi� basso dell'albero
	// a partire da una milestone e da tutti i vari tag
	// ritorna un booleano, eventualmente si pu� modificare per sviluppi futuri
	//
	// FIXME obsoleta
	public boolean getPollingFoglie(Milestone milestone, List<String> tags) {
		List<MilestoneConSemaforo> foglieConSemaforo = new ArrayList<MilestoneConSemaforo>();
		List<Milestone> foglie = milestoneManager.getFoglie(milestone);

		for (int i = 0; i < foglie.size(); i++) {
			String tag = "";
			try { // ho aggiunto solo questo try per evitare che vada in errore
					// se non vengono passati i tag, o non ne vengono passati a
					// sufficienza
				tag = tags.get(i);
				tag = utilityCheck.trimToUp(tag);
			} catch (IndexOutOfBoundsException e) {
				LOG.error("ERROR:non sono stati passati sufficienti tag");
			}
			MilestoneConSemaforo ms = getPolling0(foglie.get(i), tag);
			if (ms.isSemaforo())
				foglieConSemaforo.add(ms);

		}

		if (foglie.size() == foglieConSemaforo.size())
			return true;
		else
			return false;
	}

	// ------------------------------------PollingFoglieByDescr-----------------------------------//

	// FIXME obsoleta
	public boolean getPollingFoglieByDescr(String descMilestone, List<String> tags) {
		descMilestone = utilityCheck.toUp(descMilestone);
		LOG.info("Parametri di ricerca: Milestone " + descMilestone + " Tag " + tags);
		Milestone milestone = null;
		try {
			milestone = milestoneManager.findByCod(descMilestone.toUpperCase());
		} catch (Exception sqlError) {
			LOG.error("ERROR: La Milestone: " + descMilestone
					+ " non e' stata trovata, controllare la sintassi o la presenza effettiva sul database");
		}

		return getPollingFoglie(milestone, tags);
	}

	// ------------------------------------WSPollingStandard1L(boolean)--------------------------------------------------//

	// FIXME obsoleta
	public boolean getPolling1LByDescr(String descMilestone, List<String> tags) {
		descMilestone = utilityCheck.toUp(descMilestone);
		LOG.info("Parametri di ricerca: Milestone " + descMilestone + " Tag " + tags);
		Milestone milestone = null;
		try {
			milestone = milestoneManager.findByCod(descMilestone.toUpperCase());
		} catch (Exception sqlError) {
			LOG.error("ERROR: La Milestone: " + descMilestone
					+ " non e' stata trovata, controllare la sintassi o la presenza effettiva sul database");
		}

		return getPolling1L(milestone, tags);
	}

	// FIXME obsoleta
	public boolean getPolling1L(Milestone milestone, List<String> tags) {
		List<MilestoneMilestone> milestoneMilestones = milestone.getMilestoneMilestone();
		List<Evento> eventiVerificati = new ArrayList<Evento>();
		if (milestoneMilestones.isEmpty()) {
			String tag = "";
			try {
				tag = tags.get(0);
				tag = utilityCheck.trimToUp(tag);
			} catch (Exception e) {
				LOG.error("ERROR:non sono stati passati sufficienti tag");
			}
			eventiVerificati.addAll(eventoManager.findBy(tag, milestone.getEntita(), milestone.getTipoEvento()));
		}
		for (int i = 0; i < milestoneMilestones.size(); i++) {
			MilestoneMilestone sc = milestoneMilestones.get(i);
			Milestone m = sc.getMilestoneComponente();
			String tag = "";
			try {
				tag = tags.get(i);
				tag = utilityCheck.trimToUp(tag);
			} catch (Exception e) {
				LOG.error("ERROR:non sono stati passati sufficienti tag");
			}
			Entita ent = m.getEntita();
			TipoEvento tp = m.getTipoEvento();
			eventiVerificati.addAll(eventoManager.findBy(tag, ent, tp));
		}
		if (eventiVerificati.size() == milestoneMilestones.size())
			return true;
		else
			return false;
	}

	// ----------Funzione Semaforo sul monitor Calendario-----------//

	/**
	 * Calcolo del semaforo
	 * 
	 */
	public int calcolaSemaforo(String codiceMilestone, String tag) {
		Milestone milestone = milestoneManager.findByCod(codiceMilestone);
		Calendario calendario = calendarioMilestoneManager.findUltimoCalendario(milestone, tag);
		Map<String, String> mapCalendario = calendarioMilestoneManager.getMapMilestonesTags(calendario);
		return calcolaSemaforo(milestone, tag, mapCalendario);
	}

	/**
	 * Calcolo del semaforo
	 * 
	 */
	public int calcolaSemaforo(CalendarioMilestone calendarioMilestone) {
		Milestone milestone = calendarioMilestone.getMilestone();
		String tag = calendarioMilestone.getTag(); // prendo il tag
		Calendario calendario = calendarioMilestone.getCalendario();
		Map<String, String> mapCalendario = calendarioMilestoneManager.getMapMilestonesTags(calendario);
		return calcolaSemaforo(milestone, tag, mapCalendario);
	}

	private int calcolaSemaforo(Milestone milestone, String tag, Map<String, String> mapCalendario) {

		TipoEvento tp = milestone.getTipoEvento(); // prendo il tipo evento
		Entita ent = milestone.getEntita(); // prendo l'entita

		if (ent != null & tp != null) { // se tipo evento e entita non sono
										// vuoti
										// (milestone effettiva)

			// il semaforo è verde se l'evento si è verificato
			List<Evento> evList = eventoManager.findBy(tag, ent, tp);

			return (evList.isEmpty()) ? 0 : 2;

		} else { // se tipo evento e entita sono null (milestone aggregata)

			// il semaforo è dipende, ricorsivamente, da tutte le milestone
			// "componenti" dell'aggregato
			int counter = 0;
			for (MilestoneMilestone mm : milestone.getMilestoneMilestone()) {
				Milestone milestoneChild = mm.getMilestoneComponente();
				String tagChild = mapCalendario.get(milestoneChild.getCodice());
				counter += calcolaSemaforo(milestoneChild, tagChild, mapCalendario);
			}
			if (counter == 0) {
				return 0;
			} else if (counter == 2 * milestone.getMilestoneMilestone().size()) {
				return 2;
			} else {
				return 1;
			}
		}
	}

	// ----------Polling-----------//

	/**
	 * Calcolo del polling
	 * 
	 */
	public boolean calcolaPolling(String codiceMilestone, String tag) {
		Milestone milestone = milestoneManager.findByCod(codiceMilestone);
		Calendario calendario = calendarioMilestoneManager.findUltimoCalendario(milestone, tag);
		Map<String, String> mapCalendario = calendarioMilestoneManager.getMapMilestonesTags(calendario);
		return calcolaPolling(milestone, tag, mapCalendario);
	}

	/**
	 * Calcolo del polling
	 * 
	 */
	public boolean calcolaPolling(CalendarioMilestone calendarioMilestone) {
		Milestone milestone = calendarioMilestone.getMilestone();
		String tag = calendarioMilestone.getTag(); // prendo il tag
		Calendario calendario = calendarioMilestone.getCalendario();
		Map<String, String> mapCalendario = calendarioMilestoneManager.getMapMilestonesTags(calendario);
		return calcolaPolling(milestone, tag, mapCalendario);
	}

	private boolean calcolaPolling(Milestone milestone, String tag, Map<String, String> mapCalendario) {

		TipoEvento tp = milestone.getTipoEvento(); // prendo il tipo evento
		Entita ent = milestone.getEntita(); // prendo l'entita

		if (ent != null & tp != null) { // se tipo evento e entita non sono
										// vuoti
										// (milestone effettiva)

			// Il polling restituisce true se tutte le milestone immediatamente
			// precedenti si sono verificate. Non è ricorsivo.
			for (MilestoneMilestone mm : milestone.getMilestoneMilestone()) {
				Milestone milestoneChild = mm.getMilestoneComponente();
				String tagChild = mapCalendario.get(milestoneChild.getCodice());
				List<Evento> eventi = eventoManager.findBy(tagChild, milestoneChild.getEntita(),
						milestoneChild.getTipoEvento());
				if (eventi.isEmpty())
					return false;
			}

			return true;

		} else { // se tipo evento e entita sono null (milestone aggregata)
			throw new IllegalArgumentException(
					"Non e' possibile calcolare il polling di una milestone aggregata: " + milestone.getCodice());
		}
	}

}
