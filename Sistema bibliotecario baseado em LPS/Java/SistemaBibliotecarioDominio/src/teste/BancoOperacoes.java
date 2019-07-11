package teste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

/*
 * classe intermediaria entre 
 *  Conexao do banco e Aplicacao java
 *  metodo basico(fechar)
 *  Instancias essenciais para outras classes
 *  essa classe e mae de outras classe ..
 * */

public class BancoOperacoes {

	public static BancoConexao bd = BancoConexao.getInstance();
	public PreparedStatement stmt;

	public ResultSet rs;
	public Alert erro;
	

	// fecha a conexao com o banco de dados
	public static void fechar() {
		try {
			System.out.print("\nFechando conexao com o banco de dados...");
			BancoConexao.conexao.close();
			BancoConexao.conexao = null;
			System.out.println("OK");

		} catch (SQLException e) {
			System.out.println("\nErro ao fechar a conexao: " + e.toString());
		}
	}

	
	 //Quando um erro no carregamento da tabela ocorrer esse sera o tratamento
	 // colocado no catch
	public void erroTabela(SQLException e, String tabela) {
		
		BancoOperacoes.fechar();
		BancoConexao.getInstance().conectar();
		
		JOptionPane.showMessageDialog(null, "Erro com conexão, tentando novamente");
		
		erro.setHeaderText("Erro fatal ao carregar tabela " + tabela);
		erro.setContentText(e.getMessage());
		erro.showAndWait();
		fechar();
	}


}
