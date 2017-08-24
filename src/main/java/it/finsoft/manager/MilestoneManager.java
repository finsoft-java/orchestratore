package it.finsoft.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;

@Stateless
public class MilestoneManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@Inject
	UtilityCheck utilityCheck;

	public Milestone save(Milestone tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Milestone c = em.find(Milestone.class, id);
		em.remove(c);
	}

	public Milestone findById(Long id) {
		return em.find(Milestone.class, id);
	}

	public Milestone findByCod(String codiceMilestone) {

		codiceMilestone = utilityCheck.trimToUp(codiceMilestone);

		return em.createQuery("FROM Milestone WHERE codice= :cod", Milestone.class).setParameter("cod", codiceMilestone)
				.getSingleResult();
	}

	public List<Milestone> findAll() {
		return em.createQuery("FROM Milestone", Milestone.class).getResultList();
	}

	public List<Milestone> getHierarchy(Milestone milestone) {
		List<Milestone> output = new ArrayList<>();
		List<MilestoneMilestone> ml = milestone.getMilestoneMilestone();
		output.add(milestone);
		for (MilestoneMilestone milestoneMilestone : ml) {
			List<Milestone> tmp = getHierarchy(milestoneMilestone.getMilestoneComponente());
			for (Milestone m : tmp) {
				if (!output.contains(m)) {
					output.add(m);
				}
			}
		}
		return output;
	}

	public List<Milestone> getFoglie(Milestone milestone) {
		List<Milestone> output = new ArrayList<>();
		List<MilestoneMilestone> ml = milestone.getMilestoneMilestone();
		System.out.println(ml);
		if (ml.isEmpty()) {
			output.add(milestone);
		}
		for (MilestoneMilestone milestoneMilestone : ml) {
			List<Milestone> tmp = getFoglie(milestoneMilestone.getMilestoneComponente());
			for (Milestone m : tmp) {
				if (!output.contains(m)) {
					output.add(m);
				}
			}
		}
		return output;
	}

	/**
	 * Ci dice se la milestone è atomica o aggregata
	 * 
	 * @param milestone
	 * @return
	 */
	public boolean milestoneAggregata(Milestone milestone) {
		return milestone != null && milestone.getTipoEvento() == null && milestone.getEntita() == null;
	}

}