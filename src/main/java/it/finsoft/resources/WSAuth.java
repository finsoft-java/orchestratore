package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

import it.finsoft.util.StringJsonResponse;

/**
 * JWT authentication endpoint
 * 
 * @see https://stackoverflow.com/questions/26777083
 * 
 * @author Luca Vercelli
 *
 */
@Stateless
@Path("Authenticate")
public class WSAuth {

	// We need a signing key, so we'll create one just for this example. Usually
	// the key would be read from your application configuration instead.
	Key key = MacProvider.generateKey();

	public static class Credentials {
		public String username;
		public String password;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) // expects
											// {"username":"goofy","password":"mickey"}
	public StringJsonResponse auth(Credentials credentials) {

		StringJsonResponse ret = new StringJsonResponse();

		try {

			System.out.println("DEBUG - using key:" + key);
			// Authenticate the user using the credentials provided
			authenticate(credentials.username, credentials.password);

			// Issue a token for the user
			String token = issueToken(credentials.username);

			// Return the token on the response
			ret.data = token;

		} catch (Exception exc) {
			ret.errorCode = "401";
			ret.errorMessage = "Invalid credentials";
		}

		return ret;

	}

	private void authenticate(String username, String password) throws Exception {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		if (!(username.equals("goofy") && (password.equals("mickey")))) {
			throw new SecurityException("Invalid credentials");
		}
	}

	private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a
		// JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		String compactJwts = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, key).compact();

		return compactJwts;
	}
}
