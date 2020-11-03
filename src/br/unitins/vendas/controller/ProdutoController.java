package br.unitins.vendas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.controller.listing.MarcaListing;
import br.unitins.vendas.controller.listing.ProdutoListing;
import br.unitins.vendas.model.Marca;
import br.unitins.vendas.model.Produto;
import br.unitins.vendas.repository.MarcaRepository;
import br.unitins.vendas.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = 2843660897121724859L;
	
	private List<Produto> listaProduto;

//  utilizado no select one menu 
//	private List<Marca> listaMarca;
	
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
			entity.setMarca(new Marca());
		}
		return entity;
	}
	
	public void abrirMarcaListing() {
		MarcaListing listing = new MarcaListing();
		listing.open();
	}
	
	public void obterMarcaListing(SelectEvent<Marca> event) {
		getEntity().setMarca(event.getObject());
	}
	
	public void abrirProdutoListing() {
		ProdutoListing listing = new ProdutoListing();
		listing.open();
	}
	
	public void obterProdutoListing(SelectEvent<Produto> event) {
		setEntity(event.getObject());
	}
	
	public List<Marca> completeMarca(String query) {
		MarcaRepository repo = new MarcaRepository();
		try {
			return repo.findByNome(query, 6);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Marca>();
		}
	}

// Utilizado no select one menu
//	public List<Marca> getListaMarca() {
//		if (listaMarca == null) {
//			MarcaRepository repo = new MarcaRepository();
//			try {
//				setListaMarca(repo.findAll());
//			} catch (RepositoryException e) {
//				e.printStackTrace();
//				setListaMarca(new ArrayList<Marca>());
//			}
//		}
//		return listaMarca;
//	}

// Utilizado no select one menu
//	public void setListaMarca(List<Marca> listaMarca) {
//		this.listaMarca = listaMarca;
//	}
	
}
