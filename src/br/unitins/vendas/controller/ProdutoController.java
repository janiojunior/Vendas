package br.unitins.vendas.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.vendas.model.Produto;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 2843660897121724859L;
	
	private Produto produto;
	
	public void salvar() {
		System.out.println(getProduto());
		limpar();
	}
	
	public void limpar() {
		produto = null;
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
