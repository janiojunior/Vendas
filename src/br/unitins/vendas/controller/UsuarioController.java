package br.unitins.vendas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Usuario;
import br.unitins.vendas.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 2843660897121724859L;
	
	private List<Usuario> listaUsuario;
	
	public void pesquisarUsuario() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioRepository repo = new UsuarioRepository();
		try {
			setListaUsuario(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao excluir.");
			setListaUsuario(null);
		}
	}
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) 
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}
	
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();
		}
		return entity;
	}
	
	
}
