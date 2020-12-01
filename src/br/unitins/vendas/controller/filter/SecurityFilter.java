package br.unitins.vendas.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.vendas.model.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/pages/*"})
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

 //	 	Para desabilitar o filter, descomente as duas proximas linhas e comente o restante		
		chain.doFilter(request, response);
		return;
		
//		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		// imprime o endereco da pagina
//		String endereco = servletRequest.getRequestURI();
//		System.out.println(endereco);
////	    ex.	/Vendas/faces/pages/usuario.xhtml
//		
//		
//		
//		// filtrando o nome da pagina
//		if (endereco != null) {
//			int inicio = endereco.lastIndexOf("/faces/") + 7;
//			int fim = endereco.length();
//			endereco = endereco.substring(inicio, fim);
//		}
//		System.out.println(endereco);
////		ex.   pages/usuario.xhtml
//		
//		// retorna a sessao corrente (false - para nao criar uma nova sessao)
//		HttpSession session = servletRequest.getSession(false);
//		
//		Usuario usuario = null;
//		if (session != null)
//			usuario = (Usuario) session.getAttribute("usuarioLogado");
//		
//		if (usuario == null) {
//			((HttpServletResponse) response).sendRedirect("/Vendas/faces/login.xhtml");
//		}  else {
//			if (usuario.getPerfil().getPaginasComPermissao().contains(endereco)) {
//				// segue o fluxo 
//				chain.doFilter(request, response);
//				return;
//			} else {
//				((HttpServletResponse) response).sendRedirect("/Vendas/faces/semacesso.xhtml");
//			}
//		}
//		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

}
