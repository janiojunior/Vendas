package br.unitins.vendas.report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.vendas.application.JDBCUtil;

@WebServlet("/produtoreportservlet")
public class ProdutoReportServlet extends ReportServlet {

	private static final long serialVersionUID = 3336581594588250594L;

	@Override
	public String getArquivoJasper() {
		return "produtos.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		 HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		 param.put("ID_MARCA", Integer.class);
		 return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}

}
