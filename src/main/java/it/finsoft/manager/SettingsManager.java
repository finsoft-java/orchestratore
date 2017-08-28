package it.finsoft.manager;

import it.finsoft.entity.Settings;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Questo manager è più semplice degli altri perchè la tabella Settings ha 1
 * riga sola
 * 
 * @author u0i8226
 * 
 */
public class SettingsManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Settings save(Settings tosave) {
		return em.merge(tosave);
	}

	public Settings find() {
		return em.find(Settings.class, 1L);
	}
}
