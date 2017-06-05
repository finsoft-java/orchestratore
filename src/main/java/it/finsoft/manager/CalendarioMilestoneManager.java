package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.CalendarioMilestone;

@Stateless
public class CalendarioMilestoneManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public CalendarioMilestone save(CalendarioMilestone tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		CalendarioMilestone c = em.find(CalendarioMilestone.class, id);
		em.remove(c);
	}

	public CalendarioMilestone findById(Long id) {
		return em.find(CalendarioMilestone.class, id);
	}

	public List<CalendarioMilestone> findAll() {
		return em.createQuery("FROM CalendarioMilestone", CalendarioMilestone.class).getResultList();
	}

}
