package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.manager.MilestoneManager;

@Stateless
public class MilestoneMilestoneManager {

	@Inject
	MilestoneManager milestoneManager;

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public MilestoneMilestone save(MilestoneMilestone tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		MilestoneMilestone c = em.find(MilestoneMilestone.class, id);
		em.remove(c);
	}

	public MilestoneMilestone findById(Long id) {
		return em.find(MilestoneMilestone.class, id);
	}

	public List<MilestoneMilestone> findAll() {
		return em.createQuery("FROM MilestoneMilestone", MilestoneMilestone.class).getResultList();
	}

	// CONTROLLO PER EVITARE CONFLITTI E LOOP NELLE MILESTONEMILESTONE IN FASE
	// DI CREAZIONE
	public MilestoneMilestone preCreate(MilestoneMilestone mMilestone) {
		long idMil = mMilestone.getMilestone().getIdMilestone();
		long idMilCh = mMilestone.getMilestoneChild().getIdMilestone();
		Milestone master = em.find(Milestone.class, idMil);
		Milestone child = em.find(Milestone.class, idMilCh);
		System.out.println(master + " - " + child);
		System.out.println(master.getMilestoneMilestone().size() + " - " + child.getMilestoneMilestone().size());
		// check via getHierarchy //
		boolean ok = true;
		List<Milestone> mListA = milestoneManager.getHierarchy(master);
		List<Milestone> mListB = milestoneManager.getHierarchy(child);
		System.out.println(mListA.size());
		System.out.println(mListB.size());
		for (int i = 0; i < mListA.size(); i++) {
			System.out.println(mListA.get(i).getIdMilestone() + " - " + child.getIdMilestone());
			if (mListA.get(i).getIdMilestone().equals(child.getIdMilestone())) {
				ok = false;
				break;
			}
		}
		for (int i = 0; i < mListB.size(); i++) {
			System.out.println(mListB.get(i).getIdMilestone() + " - " + master.getIdMilestone());
			if (mListB.get(i).getIdMilestone().equals(master.getIdMilestone())) {
				ok = false;
				break;
			}
		}

		/*
		 * if (milestoneA.getIdMilestone().equals(milestoneB.getIdMilestone()))
		 * { // OK // equals sulla milestone restituisce: //
		 * it.finsoft.entity.Milestone@5f7b9c21 equals //
		 * it.finsoft.entity.Milestone@3e730583 // funziona sull'ID // ERRORE
		 * LOOP System.out.println("nodo=foglia"); ok = false; } else {
		 * List<MilestoneMilestone> milestoneMilestoneList = em
		 * .createQuery("FROM MilestoneMilestone WHERE milestone= :milestoneA AND milestoneChild= :milestoneB"
		 * , MilestoneMilestone.class) .setParameter("milestoneA",
		 * milestoneA).setParameter("milestoneB", milestoneB).getResultList();
		 * if (!milestoneMilestoneList.isEmpty()) { // OK // E' gia presente una
		 * MilestoneMilestone fatta cosi' System.out.println("gia' presente");
		 * ok = false; }
		 * 
		 * // FATTO COSI NON FUNZIONA CORRETTAMENTE
		 * 
		 * List<Milestone> mListA = milestoneManager.getHierarchy(milestoneA);
		 * List<Milestone> mListB = milestoneManager.getHierarchy(milestoneB);
		 * 
		 * for (int i = 0; i < mListA.size(); i++) {
		 * System.out.println(mListA.get(i).getIdMilestone() + " - " +
		 * milestoneB.getIdMilestone()); if
		 * (mListA.get(i).getIdMilestone().equals(milestoneB.getIdMilestone()))
		 * {// OK // // // ERRORE // LOOP
		 * 
		 * ok = false; break; } } for (int i = 0; i < mListB.size(); i++) {
		 * System.out.println(mListB.get(i).getIdMilestone() + " - " +
		 * milestoneA.getIdMilestone()); if
		 * (mListB.get(i).getIdMilestone().equals(milestoneA.getIdMilestone()))
		 * { ok = false; } }
		 * 
		 * }
		 */
		if (ok == true) {
			return save(mMilestone);
		} else {
			return null;
		}
	}
}
