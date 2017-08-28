package it.finsoft.manager;

import it.finsoft.entity.Settings;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

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

	public final static Logger LOG = Logger.getLogger(SettingsManager.class);

	public Settings save(Settings tosave) {
		return em.merge(tosave);
	}

	public Settings find() {

		Settings instance = em.find(Settings.class, new Long(1L));
		if (instance == null)
			LOG.error("Record SETTINGS non trovato!? N.B. deve avere ID=1.");
		return instance;
	}
}
