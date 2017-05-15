package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Evento;

@Stateless
public class EventoManager {
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;	
	

	public Evento save(Evento tosave) {
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Evento c = em.find(Evento.class, id);
		em.remove(c);
	}

	public Evento findById(Long id){
		return em.find(Evento.class, id);
	}
	
	/*
	 * DA inserire un metodo che esegua il "find by tag"
	 * 
	 */
	
	public List<Evento> findAll() {		
		return em.createQuery("FROM Eventi", Evento.class).getResultList();
	}

}
