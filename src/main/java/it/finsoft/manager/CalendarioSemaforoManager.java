package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import it.finsoft.entity.CalendarioSemaforo;

@Stateless
public class CalendarioSemaforoManager {

	//@PersistenceContext(unitName = "persistenceUnit")
	@Inject
	private EntityManager em;

	public CalendarioSemaforo save(CalendarioSemaforo tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		CalendarioSemaforo c = em.find(CalendarioSemaforo.class, id);
		em.remove(c);
	}

	public CalendarioSemaforo findById(Long id) {
		return em.find(CalendarioSemaforo.class, id);
	}

	public List<CalendarioSemaforo> findAll() {
		return em.createQuery("FROM CalendarioSemaforo", CalendarioSemaforo.class).getResultList();
	}

}
