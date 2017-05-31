package it.finsoft.manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WSManager {

	@PersistenceContext
	EntityManager em;
}
