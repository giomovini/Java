package banco.acesso;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TipoDado.Funcionario;
import banco.conexao.BancoOperacoes;

public class TabFuncionario extends BancoOperacoes {

	static TabFuncionario instancia = null;

	private TabFuncionario() {

	}

	public static synchronized TabFuncionario getInstance() {

		if (instancia == null) {
			instancia = new TabFuncionario();
		}

		return instancia;

	}

	public int inserirFuncionario(String nome, String rg, String cpf, String senha, String setor) {
		System.out.println(this.getClass().getName() + " inserirFuncionario");

		String sql = "INSERT INTO funcionario(NOME,RG,CPF,SENHA,SETOR)VALUES(?,?,?,?,?)";

		try {
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, rg);
			stmt.setString(3, cpf);
			stmt.setString(4, senha);
			stmt.setString(5, setor);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "funcionario");
			return 0;
		}

		return 1;

	}

	public boolean editarFuncionario(String cpfAntigo, String nome, String rg, String cpf, String senha, String setor) {
		System.out.println(this.getClass().getName() + " editarFuncionario");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("UPDATE funcionario SET " + "NOME = '" + nome + "', RG = '" + rg + "', CPF = '" + cpf
					+ "', SENHA = '" + senha + "', SETOR = '" + setor + "' WHERE CPF = '" + cpfAntigo + "'");

		} catch (SQLException e) {
			erroTabela(e, "funcionario");
			return false;
		}
		return true;

	}

	public Funcionario buscarInformacoesFuncionario(String cpf) {

		System.out.println(this.getClass().getName() + " buscarInformacoesFuncionario");

		String sql = "SELECT * FROM funcionario WHERE CPF = " + cpf;

		Funcionario p = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Funcionario();

				p.setCPF(rs.getString("CPF"));
				p.setRG(rs.getString("RG"));
				p.setNome(rs.getString("NOME"));
				p.setId(rs.getString("ID_FUNCIONARIO"));
				p.setSenha(rs.getString("SENHA"));
				p.setSetor(rs.getString("SETOR"));
			}
			
		

		} catch (Exception e) {
			erroTabela(e, "funcionario");
			return null;
		}

		return p;

	}
	
	public ArrayList<Funcionario> buscarInformacoesTodosFuncionario() {

		System.out.println(this.getClass().getName() + " buscarInformacoesTodosFuncionario");
		
		ArrayList<Funcionario> lista = new ArrayList<>();

		String sql = "SELECT * FROM funcionario";

		Funcionario p = null;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				p = new Funcionario();

				p.setCPF(rs.getString("CPF"));
				p.setRG(rs.getString("RG"));
				p.setNome(rs.getString("NOME"));
				p.setId(rs.getString("ID_FUNCIONARIO"));
				p.setSenha(rs.getString("SENHA"));
				p.setSetor(rs.getString("SETOR"));
				
				lista.add(p);
			}

		} catch (Exception e) {
			erroTabela(e, "funcionario");
			return null;
		}

		return lista;

	}
	

	public boolean deletarFuncionario(String id) {
		System.out.println(this.getClass().getName() + " deletarFuncionario");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("DELETE FROM funcionario WHERE ID_FUNCIONARIO = '" + id + "'");

		} catch (SQLException e) {
			erroTabela(e, "funcionario");
			return false;
		}
		return true;

	}

}
