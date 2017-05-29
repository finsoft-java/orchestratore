package it.finsoft;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import it.finsoft.server.DependencyBinder;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("ws")
public class JAXRSConfiguration extends ResourceConfig {

	// ResourceConfig is Jetty-specific extension of Application

	public JAXRSConfiguration() {
		System.err.println("REGISTERING JAX-RS");
		
		register(new DependencyBinder());
	}

}