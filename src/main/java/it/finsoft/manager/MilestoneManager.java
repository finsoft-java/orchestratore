package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Milestone;

@Stateless
public class MilestoneManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

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

	public Milestone findByDesc(String desc) {
		return em.createQuery("FROM Milestone WHERE descrizione= :desc", Milestone.class).setParameter("desc", desc)
				.getSingleResult();
	}

	public List<Milestone> findAll() {
		return em.createQuery("FROM Milestone", Milestone.class).getResultList();
	}

}
