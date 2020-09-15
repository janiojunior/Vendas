package br.unitins.vendas.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	// Responsavel por criar os entitys managers
	private static EntityManagerFactory emf = null;
	
	private JPAUtil() {
		// nao permitir a instancia
	}
	
	public static EntityManager getEntityManager() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("Vendas");
		return emf.createEntityManager();
	}
	
}
