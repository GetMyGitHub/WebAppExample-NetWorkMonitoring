package org.getmygithub.webapp.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");	
	private static List<EntityManager> listEntityManager = new ArrayList<EntityManager>();
	
	
	public static EntityManager createEntityManager() {		
		EntityManager em = emf.createEntityManager();
		listEntityManager.add(em);
		return em;		
	}
	
	public static void closeFactory() {		
		emf.close();
	}
	
}
