package it.finsoft.resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.finsoft.manager.CommonManager;

/**
 * This REST WS handles not just one entity, but all possible entities. Entites
 * can lie in different packages, however their names must be different (I
 * argue this is assumed by JPA too). This service assumes all entities have a
 * primary key of type Long, called Id.
 * 
 * REST syntax is inspired to OData. For handling parameters, we follow this
 * pattern: @see https://api.stackexchange.com/docs/users
 * 
 * @author Luca Vercelli 2017
 *
 */
@Stateless
@Path("rsr")
@Produces(MediaType.APPLICATION_JSON)
public class CommonResources {

	private static final Integer ZERO = 0;
	private static final Integer DEFAULT_PAGESIZE = 20;

	@Inject
	CommonManager manager;

	/**
	 * Return a list of objects (via JSON). We don't know the type of returned
	 * objects, so we must return a generic "Response".
	 * 
	 * @param entity
	 * @return
	 */
	@GET
	@Path("{entity}")
	public Response find(@PathParam("entity") String entity, @QueryParam("page") Integer page,
			@QueryParam("pagesize") Integer pagesize, @QueryParam("sort") String sort,
			@QueryParam("order") String order) {

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		if (pagesize == null || pagesize.equals(ZERO))
			pagesize = DEFAULT_PAGESIZE;
		if (page == null || page.equals(ZERO))
			page = 1;

		List<?> list = manager.find(clazz, pagesize, (page - 1) * pagesize + 1, sort, order);

		// ListType and GenericEntity are needed in order to handle generics
		Type genericType = new ListType(clazz);
		GenericEntity<Object> genericList = new GenericEntity<Object>(list, genericType);

		return Response.ok(genericList).build();

	}

	/**
	 * Retreive and return (via JSON) a single object by id. We don't know the
	 * type of returned objects, so we must return a generic "Response".
	 * 
	 * @param entity
	 * @return
	 */
	@GET
	@Path("{entity}({id})")
	public Response findById(@PathParam("entity") String entity, @PathParam("id") Long id) {

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		Object obj = manager.findById(clazz, id);

		return Response.ok(obj).build();
	}

	/**
	 * Create and return a single object (via JSON). We don't know the type of
	 * returned objects, so we must return a generic "Response".
	 * 
	 * @param entity
	 * @return
	 */
	@POST
	@Path("{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@PathParam("entity") String entity, Map<String, String> attributes) {

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		Object obj = manager.bean2object(clazz, attributes);
		obj = manager.save(obj);

		return Response.ok(obj).build();
	}

	/**
	 * Delete a single object by id.
	 * 
	 * @param entity
	 * @return
	 */
	@DELETE
	@Path("{entity}({id})")
	public Response delete(@PathParam("entity") String entity, @PathParam("id") Long id) {

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		manager.remove(clazz, id);
		return Response.ok().build();
	}

	/**
	 * Update and return a single object by id.
	 * 
	 * @param entity
	 * @return
	 */
	@PUT
	@Path("{entity}({id})")
	public Response update(@PathParam("entity") String entity, @PathParam("id") Long id,
			Map<String, String> attributes) {

		// FIXME: right way?

		if (id == null)
			return Response.status(Status.NOT_FOUND).build();

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		attributes.put("id", id.toString());
		Object obj = manager.bean2object(clazz, attributes);
		obj = manager.save(obj);
		return Response.ok(obj).build();
	}

	/**
	 * Duplicate and return (via JSON) a single object by id.
	 * 
	 * @param entity
	 * @return
	 */
	@GET
	@Path("{entity}({id})/clone")
	public Response duplicate(@PathParam("entity") String entity, @PathParam("id") Long id) {

		// FIXME: right way?

		if (id == null)
			return Response.status(Status.NOT_FOUND).build();

		Class<?> clazz = manager.getEntityClass(entity);
		if (clazz == null)
			return Response.status(Status.NOT_FOUND).build();

		Object obj = manager.findById(clazz, id);

		Method setter;
		try {
			setter = obj.getClass().getMethod("setId", Long.class);
			setter.invoke(obj, (Long) null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		return Response.ok(obj).build();

	}

	/**
	 * Class ListType(T) is a runtime placeholder for List&lt;T&gt;.
	 * 
	 * @see https://stackoverflow.com/questions/2989475
	 */
	public static class ListType implements ParameterizedType {

		private Type rawType;
		private Type[] actualTypeArguments;

		public ListType(Class<?> type) {
			if (type == null)
				throw new IllegalArgumentException("null type given");
			this.rawType = List.class;
			this.actualTypeArguments = new Type[] { type };
		}

		@Override
		public Type[] getActualTypeArguments() {
			return actualTypeArguments;
		}

		@Override
		public Type getRawType() {
			return rawType;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}

		@Override
		public String toString() {
			return "" + getRawType() + "<" + Arrays.asList(getActualTypeArguments()) + ">";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof ParameterizedType))
				return false;
			ParameterizedType t2 = (ParameterizedType) obj;
			return toString().equals(t2.toString());
		}

	}

}
