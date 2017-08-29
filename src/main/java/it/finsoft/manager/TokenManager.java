package it.finsoft.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import it.finsoft.entity.Token;

@Stateless
public class TokenManager {

	public static final Logger LOG = Logger.getLogger(TokenManager.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Token save(Token tosave) {
		LOG.info("post manager ...." + tosave.toString());
		return em.merge(tosave);
	}

	public void remove(Long id) {
		Token c = em.find(Token.class, id);
		em.remove(c);
	}

	public Token findById(Long id) {
		return em.find(Token.class, id);
	}

	public List<Token> findAll() {
		return em.createQuery("FROM Token", Token.class).getResultList();
	}

}
