package banco.acesso;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TipoDado.Cliente;
import banco.conexao.BancoOperacoes;

public class TabCliente extends BancoOperacoes {

	private static TabCliente instancia = null;

	private TabCliente() {
	}

	public static synchronized TabCliente getInstance() {

		if (instancia == null) {
			instancia = new TabCliente();
		}

		return instancia;

	}
	

	// insere um novo usuario na tabela pessoa
	public boolean inserirCliente(String nome, String rg, String cpf, String telefone, String senha) {
		System.out.println(this.getClass().getName() + " inserirCliente");

		String sql = "INSERT INTO cliente(NOME,RG,CPF,TELEFONE,SENHA)VALUES(?,?,?,?,?)";

		try {
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, rg);
			stmt.setString(3, cpf);
			stmt.setString(4, telefone);
			stmt.setString(5, senha);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "cliente");
			return false;
		}
		return true;

	}

	public boolean editarCliente(String cpfAntigo, String nome, String rg, String cpf, String telefone, String senha) {
		System.out.println(this.getClass().getName() + " editarCliente");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("UPDATE cliente SET " + "NOME = '" + nome + "', RG = '" + rg + "', CPF = '" + cpf
					+ "', TELEFONE = '" + telefone + "', SENHA = '" + senha + "' WHERE CPF = '" + cpfAntigo + "'");

		} catch (SQLException e) {
			erroTabela(e, "cliente");
			return false;
		}
		return true;

	}

	public Cliente buscarInformacoesCliente(String cpf) {

		System.out.println(this.getClass().getName() + " buscarInformacoesCliente");

		String sql = "SELECT * FROM cliente WHERE CPF = " + cpf;

		Cliente c = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Cliente();
				c.setCPF(rs.getString("CPF"));
				c.setRG(rs.getString("RG"));
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getString("ID_CLIENTE"));
				c.setSenha(rs.getString("SENHA"));
				c.setTelefone(rs.getString("TELEFONE"));
			}

		} catch (Exception e) {
			erroTabela(e, "cliente");
			return null;
		}

		return c;

	}
	
	public Cliente buscarInformacoesClienteID(String id) {

		System.out.println(this.getClass().getName() + " buscarInformacoesCliente");

		String sql = "SELECT * FROM cliente WHERE ID_CLIENTE = " + id;

		Cliente c = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Cliente();
				c.setCPF(rs.getString("CPF"));
				c.setRG(rs.getString("RG"));
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getString("ID_CLIENTE"));
				c.setSenha(rs.getString("SENHA"));
				c.setTelefone(rs.getString("TELEFONE"));
			}

		} catch (Exception e) {
			erroTabela(e, "cliente");
			return null;
		}

		return c;

	}

	public ArrayList<Cliente> buscarInformacoesTodosClientes() {

		System.out.println(this.getClass().getName() + " buscarInformacoesTodosClientes");

		ArrayList<Cliente> lista = new ArrayList<>();

		String sql = "SELECT * FROM cliente";

		Cliente c = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				c = new Cliente();
				c.setCPF(rs.getString("CPF"));
				c.setRG(rs.getString("RG"));
				c.setNome(rs.getString("NOME"));
				c.setId(rs.getString("ID_CLIENTE"));
				c.setSenha(rs.getString("SENHA"));
				c.setTelefone(rs.getString("TELEFONE"));

				lista.add(c);
			}

		} catch (Exception e) {
			erroTabela(e, "cliente");
			return null;
		}

		return lista;

	}

	public boolean deletarCliente(String id) {
		System.out.println(this.getClass().getName() + " deletarCliente");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("DELETE FROM cliente WHERE ID_CLIENTE = '" + id + "'");

		} catch (SQLException e) {
			erroTabela(e, "cliente");
			return false;
		}
		return true;

	}

}
