package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Entita;

@Stateless
public class EntitaManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Entita save(Entita tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Entita c = em.find(Entita.class, id);
		em.remove(c);
	}

	public Entita findById(Long id) {
		return em.find(Entita.class, id);
	}

	public List<Entita> findAll() {
		return em.createQuery("FROM Entita", Entita.class).getResultList();
	}

	public Entita findByCod(String cod) {
		System.out.println(cod);
		Entita test = em.createQuery("FROM Entita WHERE codice = :cod", Entita.class).setParameter("cod", cod)
				.getSingleResult();
		System.out.println(test);
		return test;
	}

}
