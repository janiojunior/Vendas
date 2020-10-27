package br.unitins.vendas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Endereco;
import br.unitins.vendas.model.Telefone;
import br.unitins.vendas.model.Usuario;
import br.unitins.vendas.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 2843660897121724859L;

	private Telefone telefone;

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
	
	public void adicionarTelefone() {
		if (getEntity().getListaTelefone() == null)
			getEntity().setListaTelefone(new ArrayList<Telefone>());
		
		getTelefone().setUsuario(getEntity());
		getEntity().getListaTelefone().add(getTelefone());
		
		telefone = null;
	}
	
	public void removerTelefone(Telefone telefone) {
		getEntity().getListaTelefone().remove(telefone);
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
			entity.setEndereco(new Endereco());
		}
		return entity;
	}

	public Telefone getTelefone() {
		if (telefone == null)
			telefone = new Telefone();
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
