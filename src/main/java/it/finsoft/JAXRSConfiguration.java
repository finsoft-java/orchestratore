package it.finsoft;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.logging.Logger;

import it.finsoft.server.DependencyBinder;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("ws")
public class JAXRSConfiguration extends ResourceConfig // ResourceConfig is Jersey-specific
{

	public static Logger LOG = Logger.getLogger(DependencyBinder.class);

	// ResourceConfig is Jetty-specific extension of Application

	public JAXRSConfiguration() {

		LOG.info("Registering JAX-RS...");

		register(new DependencyBinder()); // HK2-specific

	}

}