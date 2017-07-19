package it.finsoft.manager;

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
