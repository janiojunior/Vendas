package br.unitins.vendas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Produto;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 2843660897121724859L;
	
	private Produto produto;
	private List<Produto> listaProduto;
	
	public void salvar() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		// iniciando uma transacao com o banco de dados
		em.getTransaction().begin();
		em.merge(getProduto());
		em.getTransaction().commit();
		
		Util.addInfoMessage("Operação realizada com sucesso.");
		limpar();
	}
	
	public void excluir() {
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		Produto produto = em.merge(getProduto());
		em.remove(produto);
		em.getTransaction().commit();
		
		Util.addInfoMessage("Remoção realizada com sucesso.");
		limpar();
	}
	
	public void limpar() {
		produto = null;
	}
	
	public void editar(Produto produto) {
		setProduto(produto);
	}
	
	public void pesquisarProduto() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Produto p ");
		setListaProduto(query.getResultList());
	}
	
	public List<Produto> getListaProduto() {
		if (listaProduto == null) 
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}
	
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public Produto getProduto() {
		if (produto == null)
			produto = new Produto();
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
