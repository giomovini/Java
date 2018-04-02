package com.softgraf.consultasql.fx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {

	private Connection conexao;

	private final String DRIVER = "com.mysql.jdbc.Driver";

	private final String LOGIN = "*****";

	private final String SENHA = "****";

	private final String URL = "jdbc:mysql://localhost:3306/bancoMySql";

	public boolean conectar() {
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conexao com o banco de dados ok");
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao conectar" + e.toString());
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado " + e.toString());
			e.printStackTrace();
			return false;
		}

	}

	public Connection getConexao() {
		return conexao;
	}

	public void fechar() {

		try {
			System.out.print("fechando conexao com o banco  ...");
			conexao.close();
			System.out.println("ok");
		} catch (SQLException e) {
			System.out.println("Erro ao fechar o banco" + e.toString());
			e.printStackTrace();
		}

	}

}
