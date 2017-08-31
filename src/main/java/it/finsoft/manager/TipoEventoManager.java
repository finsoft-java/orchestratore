package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.TipoEvento;

@Stateless
public class TipoEventoManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public TipoEvento save(TipoEvento tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		TipoEvento c = em.find(TipoEvento.class, id);
		em.remove(c);
	}

	public TipoEvento findById(Long id) {
		return em.find(TipoEvento.class, id);
	}

	public List<TipoEvento> findAll() {
		return em.createQuery("FROM TipoEvento", TipoEvento.class).getResultList();
	}

	public TipoEvento findByCod(String cod) {
		try {
			return em.createQuery("FROM TipoEvento WHERE codice=  :cod", TipoEvento.class).setParameter("cod", cod)
					.getSingleResult();
		} catch (NoResultException exc) {
			return null;
		}
	}

}
