package br.unitins.vendas.controller;

import java.io.Serializable;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.DefaultEntity;
import br.unitins.vendas.repository.Repository;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {

	private static final long serialVersionUID = 3094966366555764771L;
	
	protected T entity;

	public Controller() {
		super();
	}

	public void salvar() {
		
		Repository<T> repo = new Repository<T>();
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizada com sucesso.");
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage("Erro ao Salvar.");
		}
		
		limpar();
	}

	public void excluir() {
		Repository<T> repo = new Repository<T>();
		try {
			repo.beginTransaction();
			repo.remove(getEntity());
			Util.addInfoMessage("Remoção realizada com sucesso.");
			repo.commitTransaction();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao excluir.");
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
		}
		
		limpar();
	}
	
	public void editar(T entity) {
		setEntity(entity);
	}
	
	public void limpar() {
		setEntity(null);
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	

}