package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import it.finsoft.entity.SemaforoMilestone;

@Stateless
public class SemaforoMilestoneManager {

	//@PersistenceContext(unitName = "persistenceUnit")
	@Inject
	private EntityManager em;

	public SemaforoMilestone save(SemaforoMilestone tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		SemaforoMilestone c = em.find(SemaforoMilestone.class, id);
		em.remove(c);
	}

	public SemaforoMilestone findById(Long id) {
		return em.find(SemaforoMilestone.class, id);
	}

	public List<SemaforoMilestone> findAll() {
		return em.createQuery("FROM SemaforoMilestone", SemaforoMilestone.class).getResultList();
	}

}