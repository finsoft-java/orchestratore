package it.finsoft.manager;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;
import it.finsoft.util.BusinessException;

@Stateless
public class WSPollingManager {

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

	// ----------Polling-----------//

	/**
	 * Calcolo del polling. Deve restituire 0 se l'evento non si e' verificato,
	 * 1 se si e' verificato in parte (per le aggregate), 2 se si e' verificato
	 * completamente.
	 * 
	 * @throws KeyNotFoundException
	 *             se la milestone non e' ben definita
	 * @throws BusinessException 
	 */
	public int calcolaPolling(String codiceMilestone, String tag) throws BusinessException {
		Milestone milestone = milestoneManager.findByCod(codiceMilestone);
		if (milestone == null)
			throw new BusinessException("Milestone '" + codiceMilestone + "' inesistente");
		return calcolaPolling(milestone, tag);
	}

	/**
	 * Calcolo del polling. Deve restituire 0 se l'evento non si e' verificato,
	 * 1 se si e' verificato in parte (per le aggregate), 2 se si e' verificato
	 * completamente.
	 * 
	 * @throws BusinessException
	 *             se la milestone non e' ben definita
	 */
	public int calcolaPolling(CalendarioMilestone calendarioMilestone) throws BusinessException {
		Milestone milestone = calendarioMilestone.getMilestone();
		String tag = calendarioMilestone.getTag();
		return calcolaPolling(milestone, tag);
	}

	/**
	 * Calcolo del polling. Deve restituire 0 se l'evento non si e' verificato,
	 * 1 se si e' verificato in parte (per le aggregate), 2 se si e' verificato
	 * completamente.
	 * 
	 * @throws BusinessException
	 *             se la milestone non e' ben definita
	 */
	private int calcolaPolling(Milestone milestone, String tag) throws BusinessException {

		TipoEvento tp = milestone.getTipoEvento(); // prendo il tipo evento
		Entita ent = milestone.getEntita(); // prendo l'entita

		if (!milestoneManager.milestoneAggregata(milestone)) {

			// (milestone atomica)
			// il semaforo e' verde se l'evento si e' verificato

			List<Evento> evList = eventoManager.findBy(tag, ent, tp);
			return (evList.isEmpty()) ? 0 : 2;

		} else {

			// (milestone aggregata)
			// il semaforo dipende, ricorsivamente, da tutte le milestone
			// "componenti" dell'aggregato
			// le tag devono essere reperite da calendario

			Map<String, String> mapTags = calendarioMilestoneManager.findTagComponenti(milestone, tag);

			int counter = 0;
			for (MilestoneMilestone mm : milestone.getMilestoneMilestone()) {
				Milestone milestoneChild = mm.getMilestoneComponente();
				String tagChild = mapTags.get(milestoneChild.getCodice());
				counter += calcolaPolling(milestoneChild, tagChild);
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

}
