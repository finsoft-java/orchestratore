package it.finsoft.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.messaging.bridge.api.KeyNotFoundException;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;

@Stateless
public class CalendarioMilestoneManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;
	@Inject
	CalendarioManager calendarioManager;
	@Inject
	MilestoneManager milestoneManager;

	public CalendarioMilestone save(CalendarioMilestone tosave) {
		return em.merge(tosave);
	}

	public CalendarioMilestone save(Long idCalendario, CalendarioMilestone tosave) {
		tosave.setCalendario(calendarioManager.findById(idCalendario));
		return em.merge(tosave);
	}

	public void remove(Long id) {
		CalendarioMilestone c = em.find(CalendarioMilestone.class, id);
		em.remove(c);
	}

	public CalendarioMilestone findById(Long id) {
		return em.find(CalendarioMilestone.class, id);
	}

	/**
	 * A rigore, potrebbe esisterne piu' di uno, nel caso restituiamo il primo.
	 */
	public CalendarioMilestone findByIdCalendarioIdMilestone(Long idCalendario, Long idMilestone) {
		List<CalendarioMilestone> list = em
				.createQuery(
						"from CalendarioMilestone where calendario.id = :idCalendario and milestone.id = :idMilestone",
						CalendarioMilestone.class)
				.setParameter("idCalendario", idCalendario).setParameter("idMilestone", idMilestone).getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	public List<CalendarioMilestone> findByIdCalendario(Long idCalendario) {
		List<CalendarioMilestone> list = em
				.createQuery("from CalendarioMilestone where calendario.id = :idCalendario", CalendarioMilestone.class)
				.setParameter("idCalendario", idCalendario).getResultList();
		return list;
	}

	public List<CalendarioMilestone> findAll() {
		return em.createQuery("FROM CalendarioMilestone", CalendarioMilestone.class).getResultList();
	}

	/**
	 * Cerca una milestone/tag all'interno di tutti i calendari; ci aspettiamo
	 * che ne trovi solamente uno, ad ogni modo se ce ne fossero di più
	 * restituiamo sempre solo l'ultimo per dataOraPreviste
	 * 
	 * @param milestone
	 * @param tag
	 * @return
	 */
	public Calendario findUltimoCalendario(Milestone milestone, String tag) {
		List<CalendarioMilestone> list = em
				.createQuery(
						"FROM CalendarioMilestone WHERE milestone = :milestone AND tag = :tag order by dataOraPreviste desc",
						CalendarioMilestone.class)
				.setParameter("milestone", milestone).setParameter("tag", tag).getResultList();
		return (list.isEmpty()) ? null : list.get(0).getCalendario();
	}

	/**
	 * Crea una mappa codice milestone / tag di tutte le milestone in
	 * calendario. In particolare, se la stessa milestone dovesse apparire più
	 * volte, ne verrà presa una a caso.
	 */
	public Map<String, String> getMapMilestonesTags(Calendario calendario) {
		Map<String, String> map = new HashMap<String, String>();
		for (CalendarioMilestone calMil : calendario.getCalendarioMilestone()) {
			map.put(calMil.getMilestone().getCodice(), calMil.getTag());
		}
		return map;
	}

	/**
	 * Crea una mappa codice milestone / tag di tutte le milestone componenti.
	 * Le tag vengono cercate nello <b>stesso</b> calendario della milestone
	 * padre. Nel caso di milestone atomica la mappa restituita sarà vuota.
	 * 
	 * @throws KeyNotFoundException
	 *             se la milestone non è ben definita
	 * 
	 */
	public Map<String, String> findTagComponenti(Milestone milestone, String tag) throws KeyNotFoundException {

		Map<String, String> map = new HashMap<String, String>();

		Calendario cal = findUltimoCalendario(milestone, tag);
		Map<String, String> mapCompleta = getMapMilestonesTags(cal);

		for (MilestoneMilestone mmChild : milestone.getMilestoneMilestone()) {
			String codMilestone = mmChild.getMilestone().getCodice();
			if (!mapCompleta.containsKey(codMilestone))
				throw new KeyNotFoundException("Il calendario " + cal.getDescrizione()
						+ " non contiene una tag per la milestone " + codMilestone);
			map.put(codMilestone, mapCompleta.get(codMilestone));
		}
		return map;
	}
}
