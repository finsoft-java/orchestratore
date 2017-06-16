package it.finsoft.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.Milestone;

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
	 * A rigore, potrebbe esisterne pi� di uno, nel caso restituiamo il primo.
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

	public String findDescFoglieByIdMilestone(Long idMilestone) {
		List<Milestone> foglie = milestoneManager.getFoglie(milestoneManager.findById(idMilestone));
		String listDescFoglie = "";
		/*
		 * --- ciclo for di Francesco --- for (int i = 0; i < foglie.size();
		 * i++) { listDescFoglie += ("< " + foglie.get(i).getDescrizione() +
		 * " >,"); } return listDescFoglie;
		 */

		// modificato ciclo for in un ciclo foreach in maniera che venga
		// controllata la presenza di
		// una descrizioneTag [getDescrizioneTag()] all'interno di una
		// determinata milestone, in caso affermativo
		// prende il valore del campo altrimenti va a recuperare la descrizione
		// [getDescrizione()] della milestone

		for (Milestone milestone : foglie) {
			if (milestone.getDescrizioneTag() != null) {
				listDescFoglie += ("< " + milestone.getDescrizioneTag() + " >,");
			} else {
				listDescFoglie += ("< " + milestone.getDescrizione() + " >,");
			}
		}
		return listDescFoglie;
	}

	public CalendarioMilestone findCalPolling(Milestone milestone, String tag) {
		return em
				.createQuery("FROM CalendarioMilestone WHERE milestone=:milestone AND tag=:tag",
						CalendarioMilestone.class)
				.setParameter("milestone", milestone).setParameter("tag", tag).getSingleResult();
	}

	public List<String> findTags(List<Milestone> milestoneFoglie, Calendario cal) {
		List<String> result = new ArrayList<>();
		for (Milestone milestone : milestoneFoglie) {
			CalendarioMilestone calM = em
					.createQuery("FROM CalendarioMilestone WHERE milestone=:milestone AND calendario=:calendario",
							CalendarioMilestone.class)
					.setParameter("milestone", milestone).setParameter("calendario", cal).getSingleResult();
			result.add(calM.getTag());
		}
		return result;
	}

}
