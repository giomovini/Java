package com.softgraf.commandline.fx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Importar sempre do pacote java.sql
 */
public class BD {

	private Connection conexao = null;
	// identifica a classe dentro do jar (conector mysql)
	// baixar o arquivo Connector/J do site mysql.org
	// e copiar "mysql-connector-java-5.1.39-bin" para dentro
	// do projeto, depois usar Build Path -> Add
	private final String DRIVER = "com.mysql.jdbc.Driver";
	// JDBC = Java Database Connection = protocolo de comunicação
	// entre o java e o banco de dados
	// Temos 2 formas de nos comunicar com o banco:
	// usando jdbc ou jpa (java persistence api)
	private final String URL = "jdbc:mysql://localhost:3306/bancoMysql";
	private final String LOGIN = "root";  // usuário padrão
	private final String SENHA = "softgraf";  // senha do banco
	
	
	public boolean conectar(){
		
		try {
			
			// procura pelo driver JDBC (.jar)
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conectando com o banco de dados... OK");
			return true;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado " + e.toString());
			e.printStackTrace();
			
		} catch (SQLException e) {
			System.out.println("Falha ao conectar " + e.toString());
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Connection getConexao(){
		return conexao;
	}
	
	public void fechar(){
		try {
			
			System.out.print("\nFechando conexão com o banco de dados...");
			conexao.close();
			System.out.println("OK");
			
		} catch (SQLException e) {
			System.out.println("\nErro ao fechar a conexão: " + e.toString());
		}
	}
	
}
