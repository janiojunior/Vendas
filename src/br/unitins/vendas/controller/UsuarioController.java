package br.unitins.vendas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.vendas.application.JPAUtil;
import br.unitins.vendas.application.RepositoryException;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Cidade;
import br.unitins.vendas.model.Endereco;
import br.unitins.vendas.model.Estado;
import br.unitins.vendas.model.Telefone;
import br.unitins.vendas.model.Usuario;
import br.unitins.vendas.repository.CidadeRepository;
import br.unitins.vendas.repository.EstadoRepository;
import br.unitins.vendas.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> {

	private static final long serialVersionUID = 2843660897121724859L;

	private Telefone telefone;

	private List<Usuario> listaUsuario;
	
	private InputStream fotoInputStream = null;
	private String nomeFoto = null;
	
	@Override
	public void salvar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			repo.beginTransaction();
			setEntity(repo.save(getEntity()));
			if (! Util.saveImageUsuario(fotoInputStream, "png", getEntity().getId())) 
				throw new RepositoryException("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
			repo.commitTransaction();
			Util.addInfoMessage("Operação realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			System.out.println("Erro ao salvar.");
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
		
	}

	public void upload(FileUploadEvent event) {

		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				nomeFoto = uploadFile.getFileName();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}
	
	public String getNomeFoto() {
		if (nomeFoto == null) 
			return "Selecione uma foto ...";
		return "Arquivo: "+ nomeFoto + " (Clique para alterar a foto...)";
	}

	public List<Estado> completeEstado(String query) {
		EstadoRepository repo = new EstadoRepository();
		try {
			return repo.findByNome(query, 6);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Estado>();
		}
	}

	public List<Cidade> completeCidade(String query) {
		CidadeRepository repo = new CidadeRepository();
		try {
			return repo.findByNome(query, getEntity().getCidade().getEstado().getId(), 6);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Cidade>();
		}
	}

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
			entity.setCidade(new Cidade());
			entity.getCidade().setEstado(new Estado());
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
