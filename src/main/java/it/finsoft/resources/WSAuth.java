package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import it.finsoft.entity.Engine;
import it.finsoft.manager.EngineManager;
import it.finsoft.manager.TokenManager;
import it.finsoft.util.StringJsonResponse;

/**
 * JWT authentication endpoint. Creo un token che non ha neanche bisogno di
 * essere salvato su database. Per tracking lo salviamo comunque.
 * 
 * @see https://stackoverflow.com/questions/26777083
 * 
 * @author Luca Vercelli
 *
 */
@Stateless
@Path("Authenticate")
public class WSAuth {

	public static final Logger LOG = Logger.getLogger(EngineManager.class);

	@Inject
	EngineManager engineManager;
	@Inject
	TokenManager tokenManager;

	public static class Credentials {
		public String username;
		public String password;
	}

	/**
	 * Expects {"username":"goofy","password":"mickey"}
	 * 
	 * @param credentials
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response auth(Credentials credentials, @Context HttpServletRequest request) {

		StringJsonResponse resp = new StringJsonResponse();

		try {

			String token = internalAuth(credentials.username, credentials.password, request);

			if (token != null) {
				resp.data = token;
				return Response.ok(resp, MediaType.APPLICATION_JSON).build();

			} else {
				resp.errorCode = "404";
				resp.errorMessage = "Invalid credentials";
				return Response.ok(resp, MediaType.APPLICATION_JSON).status(Response.Status.NOT_FOUND).build();
			}

		} catch (Exception exc) {
			LOG.error("Exception while authenticating", exc);
			resp.errorCode = "500";
			resp.errorMessage = "Internal server error";
			return Response.ok(resp, MediaType.APPLICATION_JSON).status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	/**
	 * Expects username=goofy&password=mickey
	 * 
	 * @param credentials
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response auth2(@FormParam("username") String username, @FormParam("password") String password,
			@Context HttpServletRequest request) {

		try {
			String token = internalAuth(username, password, request);

			if (token != null) {
				return Response.ok(token, MediaType.TEXT_PLAIN).build();

			} else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}

		} catch (Exception exc) {
			LOG.error("Exception while authenticating", exc);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private String internalAuth(String username, String password, HttpServletRequest request) {

		if (username == null)
			return null;

		Engine e = engineManager.findByUsernamePassword(username, password);

		if (e == null) {
			return null;

		} else {

			String token = tokenManager.issueToken(username, request.getRemoteAddr());
			return token;

		}
	}
}
