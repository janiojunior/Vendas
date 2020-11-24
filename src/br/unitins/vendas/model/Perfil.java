package br.unitins.vendas.model;

import java.util.ArrayList;
import java.util.List;

public enum Perfil {
	
	ADMINISTRADOR(1, "Administrador"), 
	CLIENTE(2, "Cliente"), 
	FUNCIONARIO(3, "Funcionario");
	
	private int id;
	private String label;
	private List<String> paginasComPermissao = null;
	
	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
		if (id == 1) {
			paginasComPermissao.add("pages/usuario.xhtml");
			paginasComPermissao.add("pages/marca.xhtml");
			paginasComPermissao.add("pages/marcalisting.xhtml");
			paginasComPermissao.add("pages/produto.xhtml");
			paginasComPermissao.add("pages/produtolisting.xhtml");
			paginasComPermissao.add("pages/produtolistingsql.xhtml");
			paginasComPermissao.add("pages/img-usuario");
		} else if (id == 2) {
			paginasComPermissao.add("pages/usuario.xhtml");
			paginasComPermissao.add("pages/img-usuario");
		} else if (id == 3) {
			paginasComPermissao.add("pages/usuario.xhtml");
			paginasComPermissao.add("pages/img-usuario");
			paginasComPermissao.add("pages/produto.xhtml");
			paginasComPermissao.add("pages/produtolisting.xhtml");			
		}
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}
	
	public static Perfil valueOf(Integer id) {
		if (id == null)
			return null;
		
		for (Perfil dep : Perfil.values()) {
			if (dep.getId() == id)
				return dep;
		}
		return null;
	}	

}
