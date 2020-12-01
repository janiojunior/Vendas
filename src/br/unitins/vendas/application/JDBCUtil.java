package br.unitins.vendas.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	private JDBCUtil() {
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
			// estabelecendo uma conexao com o banco
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/vendasdb", "topicos2", "123456");
			// obrigando a trabalhar com commit e rollback
			conn.setAutoCommit(false);
	
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao registrar o driver do postgres.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar no banco de dados.");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(PreparedStatement stat) {
		if (stat != null) {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
