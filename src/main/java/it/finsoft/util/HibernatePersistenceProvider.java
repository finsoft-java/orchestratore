package it.finsoft.util;

/**
 * Questa classe viene caricata durante il deploy. Quindi è un punto "furbo" per
 * inserire codice di debug
 * 
 * @author Luca Vercelli
 * 
 */
public class HibernatePersistenceProvider extends
		org.hibernate.jpa.HibernatePersistenceProvider {

	static {
		System.out.println("DEBUG - loading HibernatePersistenceProvider");
		
		System.out.println("What JPA is loaded?");
		try {
			System.out.println(javax.persistence.JoinColumn.class
					.getProtectionDomain().getCodeSource().getLocation()
					.getPath());
		} catch (Exception exc) {
			System.out.println("Exception  " + exc.getMessage());
		}

		System.out.println("What Antlr is loaded?");
		try {
			Class<?> antlr = Class.forName("antlr.CharScanner");
			System.out.println(antlr.getProtectionDomain().getCodeSource()
					.getLocation().getPath());
		} catch (Exception exc) {
			System.out.println("Exception  " + exc.getMessage());
		}

		System.out.println("What HibernatePersistenceProvider is loaded?");
		try {
			System.out
					.println(org.hibernate.jpa.HibernatePersistenceProvider.class
							.getProtectionDomain().getCodeSource()
							.getLocation().getPath());
		} catch (Exception exc) {
			System.out.println("Exception  " + exc.getMessage());
		}

		System.out.println("What JAX-RS is loaded?");
		try {
			System.out
					.println(javax.ws.rs.core.Application.class
							.getProtectionDomain().getCodeSource()
							.getLocation().getPath());
		} catch (Exception exc) {
			System.out.println("Exception  " + exc.getMessage());
		}
/*
		System.out.println("What @Inject is loaded?");
		try {
			System.out
					.println(javax.inject.Inject.class
							.getProtectionDomain().getCodeSource()
							.getLocation().getPath());
		} catch (Exception exc) {
			System.out.println("Exception  " + exc.getMessage());
		}*/
		
		
	}

}
