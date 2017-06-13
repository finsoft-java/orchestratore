package it.finsoft.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		List<CalendarioMilestone> list = em.createQuery(
				"from CalendarioMilestone where calendario.id = :idCalendario and milestone.id = :idMilestone",
				CalendarioMilestone.class)
				.setParameter("idCalendario", idCalendario)
				.setParameter("idMilestone", idMilestone)
				.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	public List<CalendarioMilestone> findByIdCalendario(Long idCalendario) {
		List<CalendarioMilestone> list = em.createQuery(
				"from CalendarioMilestone where calendario.id = :idCalendario",
				CalendarioMilestone.class)
				.setParameter("idCalendario", idCalendario)
				.getResultList();
		return list;
	}

	public List<CalendarioMilestone> findAll() {
		return em.createQuery("FROM CalendarioMilestone", CalendarioMilestone.class).getResultList();
	}
	
	public List<String> findDescFoglieByIdMilestone(Long idMilestone){
		List<Milestone> foglie = milestoneManager.getFoglie(milestoneManager.findById(idMilestone));
		List<String> listDescFoglie = new ArrayList<>();
		
		for (int i = 0; i < foglie.size(); i++) {		
			listDescFoglie.add("< " + foglie.get(i).getDescrizione() + " >");
		}
		return listDescFoglie;
	}

}
