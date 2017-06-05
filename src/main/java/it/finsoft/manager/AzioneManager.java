package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import it.finsoft.entity.Azione;

@Stateless
public class AzioneManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Azione save(Azione tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Azione c = em.find(Azione.class, id);
		em.remove(c);
	}

	public Azione findById(long id) {
		return em.find(Azione.class, id);
	}

	public List<Azione> findAll() {
		return em.createQuery("FROM Azione", Azione.class).getResultList();
	}

}