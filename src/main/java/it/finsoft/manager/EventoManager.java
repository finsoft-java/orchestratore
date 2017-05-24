package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

<<<<<<< HEAD
import it.finsoft.entity.Entita;
=======
import org.jboss.logging.Logger;

>>>>>>> branch 'master' of https://github.com/finsoft-java/orchestratore.git
import it.finsoft.entity.Evento;
import it.finsoft.entity.TipoEvento;

@Stateless
public class EventoManager {

	public static final Logger LOG = Logger.getLogger(EventoManager.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Evento save(Evento tosave) {
		LOG.info("post manager ...." + tosave.toString());
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Evento c = em.find(Evento.class, id);
		em.remove(c);
	}

	public Evento findById(Long id) {
		return em.find(Evento.class, id);
	}

	/*
	 * DA inserire un metodo che esegua il "find by tag"
	 * 
	 */

	public List<Evento> findAll() {
		return em.createQuery("FROM Evento", Evento.class).getResultList();
	}

	public List<Evento> findByTag(String tag) {
		return em.createQuery("FROM Evento WHERE tag= :tag", Evento.class).setParameter("tag", tag).getResultList();
	}

	public List<Evento> findPolling(String tag, Entita ent, TipoEvento tp) {
		System.out.println("ok Manager");
		return em.createQuery("FROM Evento WHERE tag= :tag AND entita = :ent AND tipoEvento = :tp", Evento.class)
				.setParameter("tag", tag).setParameter("ent", ent).setParameter("tp", tp).getResultList();
	}

}
