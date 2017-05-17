package it.finsoft.testing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.finsoft.entity.Entita;

public class prova {

	private static EntityManager manager;

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

	public static void main(String[] args) {
		/* Creiamo il gestore di peristenza */

		manager = emf.createEntityManager();
		
		Entita e0 = new Entita("test", "prova", "ciao");
		manager.getTransaction().begin(); 
		manager.persist(e0);
		manager.getTransaction().commit();
		
		/*
		 * Programma p0 = new Programma("Backup", 100, "provaDB"); 
		 * Programma p1 = new Programma("Storace", 400, "Cohesity"); 
		 * Programma p2 = new Programma ("Antivirus", 500, "provaDB"); 
		 * Programma p3 = new Programma("File Server", 200, "Numero di cartelle disponibili");
		 * Programma p4 = new Programma("Dc", 300, "Utenti a disposizione");
		 * 
		 * 
		 * manager.getTransaction().begin(); 
		 * manager.persist(p0);
		 * manager.persist(p1); 
		 * manager.persist(p2); 
		 * manager.persist(p3);
		 * manager.persist(p4); 
		 * manager.getTransaction().commit();
		 * 
		 */
		
		
		emf.close();

	}
	

}
