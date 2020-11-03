package br.unitins.vendas.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.model.Produto;
import br.unitins.vendas.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoListing extends Listing<Produto> {
	
	private static final long serialVersionUID = -9011762519094386462L;
	private String filtro;

	public ProdutoListing() {
		super("produtolisting", new ProdutoRepository());
	}
	
	public void pesquisar() {
		ProdutoRepository repo = (ProdutoRepository)getRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			e.printStackTrace();
			setList(new ArrayList<Produto>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	
}
