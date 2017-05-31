package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.MilestoneMilestone;

@Stateless
public class MilestoneMilestoneManager {

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

}