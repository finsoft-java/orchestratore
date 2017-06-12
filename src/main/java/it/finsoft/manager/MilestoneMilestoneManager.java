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
	// DI CREAZIONE E UPDATE
	// IMPORTANTE: NON VALIDA L'UPDATE SE VIENE CAMBIATO SOLO IL CAMPO
	// ORDINAMENTO
	// ES:
	/*
	 * { "milestone":{ "idMilestone":1 }, "milestoneChild":{ "idMilestone":4 },
	 * "ordinamento":1 } AGGIORNATO CON { "milestone":{ "idMilestone":1 },
	 * "milestoneChild":{ "idMilestone":4 }, "ordinamento":2 } per il momento
	 * non la ritiene una modifica valida
	 */
	public MilestoneMilestone preCheck(MilestoneMilestone mMilestone) {
		long idMil = mMilestone.getMilestone().getIdMilestone();
		long idMilCh = mMilestone.getMilestoneChild().getIdMilestone();
		Milestone master = em.find(Milestone.class, idMil);
		Milestone child = em.find(Milestone.class, idMilCh);
		boolean ok = true;
		List<Milestone> mListB = milestoneManager.getHierarchy(child);
		// check per la presenza di una MilestoneMilestone uguale nel db
		List<MilestoneMilestone> milestoneMilestoneList = em
				.createQuery("FROM MilestoneMilestone WHERE milestone= :milestoneA AND milestoneChild= :milestoneB",
						MilestoneMilestone.class)
				.setParameter("milestoneA", master).setParameter("milestoneB", child).getResultList();
		if (!milestoneMilestoneList.isEmpty()) {
			ok = false;
		}
		// check via getHierarchy se nella gerarchia della child e' presente la
		// master//
		for (int i = 0; i < mListB.size(); i++) {
			System.out.println(mListB.get(i).getIdMilestone() + " - " + master.getIdMilestone());
			if (mListB.get(i).getIdMilestone().equals(master.getIdMilestone())) {
				ok = false;
				break;
			}
		}
		if (ok == true) {
			return save(mMilestone);
		} else {
			return null;
		}
	}
}
