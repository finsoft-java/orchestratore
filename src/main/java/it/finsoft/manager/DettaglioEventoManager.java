package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import it.finsoft.entity.DettaglioEvento;

@Stateless
public class DettaglioEventoManager {

	//@PersistenceContext(unitName = "persistenceUnit")
	@Inject
	private EntityManager em;

	public DettaglioEvento save(DettaglioEvento tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		DettaglioEvento c = em.find(DettaglioEvento.class, id);
		em.remove(c);
	}

	public DettaglioEvento findById(Long id) {
		return em.find(DettaglioEvento.class, id);
	}

	public List<DettaglioEvento> findAll() {
		return em.createQuery("FROM DettaglioEvento", DettaglioEvento.class).getResultList();
	}

}
