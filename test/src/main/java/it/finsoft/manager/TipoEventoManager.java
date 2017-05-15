package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

	public TipoEvento findById(Long id){
		return em.find(TipoEvento.class, id);
	}
	
	public List<TipoEvento> findAll() {		
		return em.createQuery("FROM TipiEvento", TipoEvento.class).getResultList();
	}

}
