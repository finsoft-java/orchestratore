package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StringJsonResponse auth(Credentials credentials, @Context HttpServletRequest request) {

		StringJsonResponse ret = new StringJsonResponse();
		try {
			Engine e = engineManager.findByUsernamePassword(credentials.username, credentials.password);

			if (e == null) {
				ret.errorCode = "401";
				ret.errorMessage = "Invalid credentials";
			} else {

				String token = tokenManager.issueToken(credentials.username, request.getRemoteAddr());

				ret.data = token;

			}
		} catch (Exception exc) {
			LOG.error("Eccezione durante l'autenticazione ", exc);
			ret.errorCode = "500";
			ret.errorMessage = "Internal server error";
		}

		return ret;

	}

}
