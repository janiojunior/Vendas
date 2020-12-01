package br.unitins.vendas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Marca;
import br.unitins.vendas.model.Produto;
import br.unitins.vendas.repository.MarcaRepository;
import br.unitins.vendas.repository.ProdutoRepository;

@Named
@ViewScoped
public class RelatorioProdutoController implements Serializable {

	private static final long serialVersionUID = -6801748435188590032L;
	private Marca marca;
	private List<Marca> listaMarca;
	private List<Produto> listaProduto;
	
	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			if (getMarca().getId() == null) 
				setListaProduto(repo.findAll());
			else
				setListaProduto(repo.findByMarca(marca.getId()));
		} catch (RepositoryException e) {
			setListaProduto(null);
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		marca = null;
		listaMarca = null;
		listaProduto = null;
	}
	
	public void gerarRelatorio() {
		Util.redirect("/Vendas/faces/produtoreportservlet?ID_MARCA="+getMarca().getId());
	}

	public Marca getMarca() {
		if (marca == null)
			marca = new Marca();

		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Marca> getListaMarca() {
		if (listaMarca == null) {
			MarcaRepository repo = new MarcaRepository();
			try {
				listaMarca = repo.findAll();
			} catch (RepositoryException e) {
				listaMarca = new ArrayList<Marca>();
				e.printStackTrace();
			}
		}
		return listaMarca;
	}

	public void setListaMarca(List<Marca> listaMarca) {
		this.listaMarca = listaMarca;
	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null)
			listaProduto = new ArrayList<Produto>();
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

}
