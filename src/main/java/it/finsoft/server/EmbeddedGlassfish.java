package it.finsoft.server;

import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishRuntime;

public class EmbeddedGlassfish {

	/**
	 * Avvia l'applicazione con l'embedded server. Vuol dire che nonn c'è
	 * bisogno di installare Glassfish, basta fare "run as application" da
	 * Eclipse.
	 */
	public static void main(String[] args) throws GlassFishException {

	    GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish();
	    glassfish.start();
	    
	}

}
