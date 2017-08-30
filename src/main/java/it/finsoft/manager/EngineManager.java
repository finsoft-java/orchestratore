package it.finsoft.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import it.finsoft.entity.Engine;
import it.finsoft.util.MD5;

@Stateless
public class EngineManager {

	public static final Logger LOG = Logger.getLogger(EngineManager.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Engine save(Engine tosave) {
		LOG.info("post manager ...." + tosave.toString());
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Engine c = em.find(Engine.class, id);
		em.remove(c);
	}

	public Engine findById(Long id) {
		return em.find(Engine.class, id);
	}

	public List<Engine> findAll() {
		return em.createQuery("FROM Engine", Engine.class).getResultList();
	}

	/**
	 * Restituisce l'engine con username e password dati, purchè sia ancora
	 * attivo
	 * 
	 * @param username
	 * @param clearTextPassword
	 * @return null if not found
	 * @throws NonUniqueResultException
	 */
	public Engine findByUsernamePassword(String engineName, String clearTextPassword) {
		String encPassword = MD5.digest(clearTextPassword);
		try {
			return em
					.createQuery("FROM Engine WHERE name = :n AND encryptedPassword = :p AND fineValidita > :t ",
							Engine.class)
					.setParameter("n", engineName).setParameter("p", encPassword).setParameter("t", new Date())
					.getSingleResult();

		} catch (NoResultException exc) {
			return null;
		}
	}

}
