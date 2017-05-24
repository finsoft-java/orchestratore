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
		System.out.println("post manager ...." + tosave.toString());
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
		return em.createQuery("FROM Evento", Evento.class).getResultList();
	}
	
	/*Aggiunta per test*/
	public List<Evento> findByTag(String tag) {
		return em.createQuery("FROM Evento WHERE tag= :tag", Evento.class)
				.setParameter("tag", tag)
				.getResultList();
	}


}
