package it.finsoft.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
	 * The server private key used for signing and encoding messages.
	 */
	public static Key serverKey = null;

	@Inject
	SettingsManager settingsManager;

	/**
	 * Validita' del token in ms
	 */
	public static final long DELAY = 24 * 3600 * 1000; // 1 giorno

	public static final Logger LOG = Logger.getLogger(TokenManager.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@PostConstruct
	public void postConstructEJB() {
		//either generate or load:
		//serverKey = generateSecretKey();
		serverKey = loadSecretKey();
	}

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
				.setExpiration(expDate).signWith(SignatureAlgorithm.HS512, serverKey).compact();

		Token t = new Token(compactJwts, username, callerIP, now, expDate);
		save(t);

		return compactJwts;
	}

	/**
	 * Check if it was issued by the server and if it's not expired. Check
	 * caller IP's too.
	 * 
	 * @param token
	 * @throws Exception
	 * @return tru if token is valid
	 */
	public boolean validateToken(String token, String callerIP) {

		Jws<Claims> jws;

		try {
			jws = Jwts.parser().setSigningKey(serverKey).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}

		// OK, we can trust this JWT (well, JWS). What about its content?

		Claims claims = jws.getBody();

		// DEBUG
		LOG.info("Caller IP: " + callerIP + "IP in token: " + claims.get("ipc"));

		if (claims.get("ipc") != null && !claims.get("ipc").equals(callerIP)) {
			// throw new SecurityException("Token was issued for a different
			// IP!");
			return false;
		}

		if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
			return false;
		}

		return true;
	}

	/**
	 * The new key is a random sequence generated through
	 * MacProvider.generateKey(), to be used for SHA-256.
	 * 
	 * Questo metodo lo uso in locale per generare una nuova chiave, che poi
	 * salvo sul server.
	 * 
	 * @throws IOException
	 * @see https://stackoverflow.com/questions/11410770
	 */
	public Key generateSecretKey() {
		String filename = "server.key";
		Key key = MacProvider.generateKey();
		FileOutputStream fos;
		try {
			File f = new File(filename).getAbsoluteFile();
			System.out.println("Writing server key to " + f.getPath());
			fos = new FileOutputStream(f, false);

			try {
				fos.write(key.getEncoded());
			} finally {
				fos.close();
			}
			return key;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @throws IOException
	 * @see https://stackoverflow.com/questions/11410770
	 */
	public SecretKey loadSecretKey() {
		String filename = "server.key";
		byte[] keyBytes;
		try {
			keyBytes = Files.readAllBytes(Paths.get(filename));
			return new SecretKeySpec(keyBytes, "SHA-512");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
