package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.Milestone;

@Stateless
public class CalendarioManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Calendario save(Calendario tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Calendario c = em.find(Calendario.class, id);
		em.remove(c);
	}

	public Calendario findById(Long id) {
		return em.find(Calendario.class, id);
	}

	public List<Calendario> findAll() {
		return em.createQuery("FROM Calendario", Calendario.class).getResultList();
	}

}
