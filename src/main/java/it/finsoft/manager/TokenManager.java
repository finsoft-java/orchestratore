package it.finsoft.manager;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;
import it.finsoft.entity.Token;

/**
 * Questo manager gestisce sia la tabella TOKEN, sia i token JWT (che di per sè
 * non avrebbero bisogno di tabella).
 * 
 * @author Luca Vercelli
 * 
 */
@Stateless
public class TokenManager {

	/**
	 * We need a signing key, so we'll create one just for this example. Usually
	 * the key would be read from your application configuration instead.
	 */
	public static Key SERVER_SECRET_KEY = MacProvider.generateKey();

	/**
	 * Validita' del token in ms
	 */
	public static final long DELAY = 24 * 3600 * 1000; // 1 giorno

	public static final Logger LOG = Logger.getLogger(TokenManager.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	public Token save(Token tosave) {
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

	/**
	 * Issue a JWT token associated to given engine and IP, then record it to
	 * database.
	 */
	public String issueToken(String username, String callerIP) {

		Date now = new Date();
		Date expDate = new Date(now.getTime() + DELAY);

		Claims moreClaims = new DefaultClaims();
		moreClaims.put("ipc", callerIP);

		String compactJwts = Jwts.builder().setClaims(moreClaims).setSubject(username).setIssuedAt(now)
				.setExpiration(expDate).signWith(SignatureAlgorithm.HS512, SERVER_SECRET_KEY).compact();

		Token t = new Token(compactJwts, username, callerIP, now, expDate);
		save(t);

		return compactJwts;
	}

	/**
	 * Check if it was issued by the server and if it's not expired. Check
	 * caller IP's too. Throw exception if token is not valid.
	 * 
	 * @param token
	 * @throws Exception
	 */
	public void validateToken(String token, String callerIP) throws SignatureException {

		Jws<Claims> jws = Jwts.parser().setSigningKey(SERVER_SECRET_KEY).parseClaimsJws(token);

		// OK, we can trust this JWT (well, JWS). What about its content?

		Claims claims = jws.getBody();

		// DEBUG
		LOG.info("Caller IP: " + callerIP + "IP in token: " + claims.get("ipc"));

		if (claims.get("ipc") != null && !claims.get("ipc").equals(callerIP)) {
			throw new SecurityException("Token was issued for a different IP!");
		}

		if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
			throw new SecurityException("Token expired!");
		}

	}
}
