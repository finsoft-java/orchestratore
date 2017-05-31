package it.finsoft.server;

import java.io.Closeable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

/**
 * This class should provide injection of EntityManager.
 *
 */
class EntityManagerFactoryHK2 implements Factory<EntityManager> {
	private final CloseableService closeableService;
	EntityManagerFactory emf;

	@Inject
	public EntityManagerFactoryHK2(CloseableService closeableService) {
		this.closeableService = closeableService;
		emf = Persistence.createEntityManagerFactory("persistenceUnit");
	}

	@Override
	public void dispose(EntityManager em) {
		em.close();
	}

	@Override
	public EntityManager provide() {
		final EntityManager em = emf.createEntityManager();
		closeableService.add(new Closeable() {
			@Override
			public final void close() {
				em.close();
			}
		});
		return em;
	}
}
