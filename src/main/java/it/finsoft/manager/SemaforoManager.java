package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import it.finsoft.entity.Semaforo;

@Stateless
public class SemaforoManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Semaforo save(Semaforo tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Semaforo c = em.find(Semaforo.class, id);
		em.remove(c);
	}

	public Semaforo findById(long id) {
		return em.find(Semaforo.class, id);
	}

	public List<Semaforo> findAll() {
		List<Semaforo> list = em.createQuery("FROM Semaforo", Semaforo.class).getResultList();
		for (Semaforo l : list) {
			System.out.println("l=" + l);
			System.out.println("m=" + l.getSemaforoMilestones());
		}

		return list;

	}

	public Semaforo findByCod(String cod) {
		Semaforo semaforo = em.createQuery("FROM Semaforo WHERE codice = :cod", Semaforo.class).setParameter("cod", cod)
				.getSingleResult();
		return semaforo;

	}

}