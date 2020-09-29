package br.unitins.vendas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Produto;
import br.unitins.vendas.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = 2843660897121724859L;
	
	private List<Produto> listaProduto;
	
	public void pesquisarProduto() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			setListaProduto(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaProduto(null);
		}
	}
	
	public Departamento[] getListaDepartamento() {
		return Departamento.values();
	}
	
	public List<Produto> getListaProduto() {
		if (listaProduto == null) 
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}
	
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}
	
	
}
