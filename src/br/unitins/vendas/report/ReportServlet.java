package br.unitins.vendas.report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unitins.vendas.application.JDBCUtil;

public abstract class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return retorna o nome do arquivo .jasper
	 */
	public abstract String getArquivoJasper();

	/**
	 * 
	 * @return retorna os tipos dos parametros, 
	 * ex.
	 *  HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
	 *	param.put("ID_MARCA", Integer.class);
	 *	return param;
	 */
	public abstract HashMap<String, Class<?>> getParametros();

	public abstract Connection getConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nome = request.getServletContext().getRealPath("/reports/" + getArquivoJasper());

			Connection connection = getConnection();

			// Adicionando os parametros
			Map<String, Object> parametros = new HashMap<String, Object>();
			HashMap<String, Class<?>> listaParametros = getParametros();
			if (listaParametros != null)
				for (String key : listaParametros.keySet()) {
					// excecao para valores nullos
					if (request.getParameter(key) == null) {
						parametros.put(key, null);
						continue;
					} 
					//excecao para valores vazios
					Object param = request.getParameter(key);
					if (param instanceof String ) {
						if ( ((String)param).trim().equals("") || ((String)param).trim().equals("null")) {
							parametros.put(key, null);
							continue;
						}
					}
					
					if (listaParametros.get(key).getName().equals("java.lang.Integer"))
						parametros.put(key, Integer.valueOf(request.getParameter(key)));
					else if (listaParametros.get(key).toString().contains("java.lang.String")) {
						parametros.put(key, new String(request.getParameter(key)));
					} else if (listaParametros.get(key).toString().contains("java.lang.Boolean"))
						parametros.put(key, Boolean.valueOf(request.getParameter(key)));
					else if (listaParametros.get(key).toString().contains("java.lang.Float"))
						parametros.put(key, Float.valueOf(request.getParameter(key)));
					else if (listaParametros.get(key).toString().contains("java.lang.Double"))
						parametros.put(key, Double.valueOf(request.getParameter(key)));
				}

			GeradorRelatorio gerador = new GeradorRelatorio(nome, parametros, connection);
			gerador.gerarPDFParaOutputStream(response.getOutputStream());

			JDBCUtil.closeConnection(connection);
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}