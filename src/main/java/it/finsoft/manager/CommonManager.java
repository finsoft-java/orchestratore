package it.finsoft.manager;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Not very different from EntityManager.
 * 
 * @author LV
 *
 */
@Stateless
public class CommonManager {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	/**
	 * Return a Class, if any, or null if class does not exist.
	 * 
	 * @param entity
	 *            Class name, either in format it-mypackage-ClassName or
	 *            it.mypackage.ClassName
	 */
	public Class<?> getClazz(String entity) {
		try {
			return Class.forName(entity.replaceAll("-", "."));
		} catch (ClassNotFoundException exc) {
			try {
				return Class.forName(entity);
			} catch (ClassNotFoundException exc1) {
				return null;
			}
		}
	}

	public <T> T bean2object(Class<T> entity, Map<String, ? extends Object> attributes) {
		T obj;
		try {
			obj = entity.newInstance();
			BeanUtils.populate(obj, attributes);

		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return obj;
	}

	public Map<String, String> object2bean(Object obj) {
		try {
			return BeanUtils.describe(obj);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Find (i.e. retrieve) an object from database, by primary key.
	 */
	public <T> T findById(Class<T> entity, Serializable id) {
		return em.find(entity, id);
	}

	/**
	 * Save an object to database (using 'merge' instead of 'persist').
	 */
	public <T> T save(T tosave) {
		return em.merge(tosave);
	}

	/**
	 * Delete an object from database.
	 */
	public void remove(Object toremove) {
		em.remove(toremove);
	}

	/**
	 * Delete an object from database, by primary key.
	 */
	public void remove(Class<?> entity, Serializable id) {
		Object obj = findById(entity, id);
		em.remove(obj);
	}

	/**
	 * Load all objects of given entity.
	 */
	public <T> List<T> findAll(Class<T> entity) {
		return em.createQuery("from " + entity.getName(), entity).getResultList();
	}

	/**
	 * Count number of rows in given table.
	 */
	public Long countEntities(Class<?> entity) {
		return em.createQuery("count(*) from " + entity.getName(), Long.class).getSingleResult();
	}

	/**
	 * Load at most maxResults objects of given entity.
	 */
	public <T> List<T> findAll(Class<T> entity, int maxResults) {
		return em.createQuery("from " + entity.getName(), entity).setMaxResults(maxResults).getResultList();
	}

	/**
	 * Load at most maxResults objects of given entity, starting from
	 * firstResult. Useful for pagination.
	 */
	public <T> List<T> findAll(Class<T> entity, int maxResults, int firstResult) {
		return em.createQuery("from " + entity.getName(), entity).setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	/**
	 * Load all objects of given entity, such that property=value.
	 */
	public <T> List<T> findByProperty(Class<T> entity, String property, Object value) {
		return em.createQuery("from " + entity.getName() + " where " + property + " = :param", entity)
				.setParameter("param", value).getResultList();
	}

}
