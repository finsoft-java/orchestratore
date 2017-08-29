package it.finsoft.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * JWT authentication filter
 * 
 * @see https://stackoverflow.com/questions/26777083
 * 
 * @author Luca Vercelli
 * 
 */
// TODO @WebFilter("/*")
public class TokenFilter implements Filter /* ContainerRequestFilter  since 2.0 */ {

	private static final String AUTHENTICATION_SCHEME = "Bearer";
	
	String key = "somesecretkeyplease";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {

			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) req;

			// Get the Authorization header from the request
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

			// Validate the Authorization header
			if (!isTokenBasedAuthentication(authorizationHeader)) {
				abortWithUnauthorized(request, response);
				return;
			}

			// Extract the token from the Authorization header
			String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

			try {

				// Validate the token
				validateToken(token);

			} catch (Exception e) {
				abortWithUnauthorized(request, response);
			}

		} else {
			chain.doFilter(req, resp); // Just continue chain
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a
		// whitespace
		// Authentication scheme comparison must be case-insensitive
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(HttpServletRequest request, HttpServletResponse response) {

		// Abort the filter chain with a 401 status code
		// The "WWW-Authenticate" is sent along with the response
		response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
		response.setHeader(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME);
	}

	private void validateToken(String token) throws Exception {
		// Check if it was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid
		
		try {

		    Jwts.parser().setSigningKey(key).parseClaimsJws(token);

		    //OK, we can trust this JWT

		} catch (SignatureException e) {

		    //don't trust the JWT!
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
