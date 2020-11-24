package br.unitins.vendas.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.unitins.vendas.application.Session;
import br.unitins.vendas.application.Util;
import br.unitins.vendas.model.Usuario;
import br.unitins.vendas.repository.UsuarioRepository;


@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;

	public void logar() {
		
		UsuarioRepository repo = new UsuarioRepository();
		try {
			Usuario usuarioLogado = 
					repo.findUsuario(getUsuario().getLogin(), getUsuario().getSenha());
			
			if (usuarioLogado == null)
				Util.addErrorMessage("Usuário ou senha inválido.");
			else {
				// Usuario existe com as credenciais
				Session.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				Util.redirect("/Vendas/faces/pages/usuario.xhtml");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao verificar o Login. Entre em contato pelo email: contato@email.com.br");
		}
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
