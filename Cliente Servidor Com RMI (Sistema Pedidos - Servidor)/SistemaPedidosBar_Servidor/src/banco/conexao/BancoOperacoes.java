package banco.conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BancoOperacoes {

	public static BancoConexao bd = BancoConexao.getInstance();

	public PreparedStatement stmt;

	public ResultSet rs;

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


	public void erroTabela(Exception e, String tabela) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erro "+tabela, JOptionPane.ERROR_MESSAGE);
	}

}
