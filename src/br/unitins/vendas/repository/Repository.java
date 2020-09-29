package br.unitins.vendas.repository;

import javax.persistence.EntityManager;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.DefaultEntity;

public class Repository <T extends DefaultEntity<T>> {

	private EntityManager entityManager;
	
	public Repository() {
		this(JPAUtil.getEntityManager());
	}
	
	public Repository(EntityManager em) {
		setEntityManager(em);
	}
	
	public void save(T entity) throws RepositoryException {
		try { 
			getEntityManager().merge(entity);
		} catch (Exception e) {
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Salvar.");
		} 
	
	}

	public void remove(T entity) throws RepositoryException {
		
		try { 
			T t = getEntityManager().merge(entity);
			getEntityManager().remove(t);
		} catch (Exception e) {
			System.out.println("Erro ao Remover.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao Remover. ");
		} 
	
	}
	
	public void beginTransaction() {
		try {
			getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao realizar um commit.");
		}
	}
	
	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}