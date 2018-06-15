package banco.acesso;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TipoDado.Produto;
import banco.conexao.BancoOperacoes;

public class TabProduto extends BancoOperacoes {

	static TabProduto instancia = null;

	private TabProduto() {

	}

	public static synchronized TabProduto getInstance() {

		if (instancia == null) {
			instancia = new TabProduto();
		}

		return instancia;

	}

	// se validade for null, nao insere a validade do produto
	public boolean inserirProduto(String nome, String descricao, double preco, int quantidade, String validade,String tipo) {
		System.out.println(this.getClass().getName() + " inserirProduto");

		String sql;
		if (validade != null)
			sql = "INSERT INTO produto(NOME,DESCRICAO,PRECO,QUANTIDADE,TIPO,VALIDADE)VALUES(?,?,?,?,?,?)";
		else
			sql = "INSERT INTO produto(NOME,DESCRICAO,PRECO,QUANTIDADE,TIPO)VALUES(?,?,?,?,?)";

		try {
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, descricao);
			stmt.setDouble(3, preco);
			stmt.setInt(4, quantidade);
			stmt.setString(5, tipo);
			if (validade != null)
				stmt.setString(6, validade);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "produto");
			return false;
		}
		return true;

	}

	public boolean editarProduto(String id, String nome, String descricao, double preco, int quantidade,
			String validade,String tipo) {
		System.out.println(this.getClass().getName() + " editarProduto");

		Statement st;

		// sql = "INSERT INTO
		// produto(NOME,DESCRICAO,PRECO,QUANTIDADE,VALIDADE)VALUES(?,?,?,?,?)";
		try {
			st = bd.getConexao().createStatement();
			if (validade != null)
				st.executeUpdate("UPDATE produto SET " + "NOME = '" + nome + "', DESCRICAO = '" + descricao
						+ "', PRECO = '" + preco + "', QUANTIDADE = '" + quantidade + "', VALIDADE = '" + validade
						+ "', TIPO = '"+tipo+ "' WHERE ID_PRODUTO = '" + id + "'");
			else
				st.executeUpdate("UPDATE produto SET " + "NOME = '" + nome + "', DESCRICAO = '" + descricao
						+ "', PRECO = '" + preco + "', QUANTIDADE = '" + quantidade + "', VALIDADE = NULL"
						+ "', TIPO = '"+tipo+ " WHERE ID_PRODUTO = '" + id + "'");

		} catch (SQLException e) {
			erroTabela(e, "produto");
			return false;
		}
		return true;

	}

	public ArrayList<Produto> buscarInformacoesTodosProdutos() {

		System.out.println(this.getClass().getName() + " buscarInformacoesTodosProdutos");

		ArrayList<Produto> lista = new ArrayList<>();

		String sql = "SELECT * FROM produto";

		Produto p = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				p = new Produto();
				p.setID(rs.getString("ID_PRODUTO"));
				p.setNOME(rs.getString("NOME"));
				p.setDESCRICAO(rs.getString("DESCRICAO"));
				p.setPreco(rs.getDouble("PRECO"));
				p.setQuantidade(rs.getInt("QUANTIDADE"));
				p.setVALIDADE(rs.getString("VALIDADE"));
				p.setTIPO(rs.getString("TIPO"));
				lista.add(p);
			}

		} catch (Exception e) {
			erroTabela(e, "produto");
			return null;
		}

		return lista;

	}

	public boolean deletarProduto(String id) {
		System.out.println(this.getClass().getName() + " deletarProduto");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("DELETE FROM produto WHERE ID_PRODUTO = '" + id + "'");

		} catch (SQLException e) {
			erroTabela(e, "produto");
			return false;
		}
		return true;

	}
	
	public Produto buscarProduto(String id_produto) {

		System.out.println(this.getClass().getName() + " buscarProduto");

		String sql = "SELECT * FROM produto WHERE ID_PRODUTO = "+id_produto;

		Produto p = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Produto();

				p.setID(rs.getString("ID_PRODUTO"));
				p.setNOME(rs.getString("NOME"));
				p.setDESCRICAO(rs.getString("DESCRICAO"));
				p.setPreco(rs.getDouble("PRECO"));
				p.setQuantidade(rs.getInt("QUANTIDADE"));
				p.setVALIDADE(rs.getString("VALIDADE"));
				p.setTIPO(rs.getString("TIPO"));

			}

		} catch (Exception e) {
			erroTabela(e, "produto");
			return null;
		}

		return p;

	}

}
