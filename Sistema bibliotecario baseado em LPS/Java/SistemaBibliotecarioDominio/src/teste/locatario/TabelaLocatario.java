package teste.locatario;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gerenciador_locatarios.Locatario;
import teste.BancoOperacoes;

public class TabelaLocatario extends BancoOperacoes {

	private static TabelaLocatario Instancia;

	public static synchronized TabelaLocatario getInstance() {

		if (Instancia == null)
			Instancia = new TabelaLocatario();
		return Instancia;
	}

	public List<Locatario> buscarLocatarios() {

		List<Locatario> lista = new ArrayList<>();

		String sql = "SELECT * FROM locatario ";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locatario Locac = new Locatario();

				Locac.setId(rs.getInt("ID_LOCATARIO"));
				Locac.setNome(rs.getString("LOCATARIO"));
				Locac.setTelefone(rs.getString("TELEFONE"));
				Locac.setCPF(rs.getString("CPF"));
				Locac.setNascimento(rs.getString("NASCIMENTO"));
				Locac.setSexo(rs.getString("SEXO"));
				Locac.setEmail(rs.getString("EMAIL"));
				Locac.setCidade(rs.getString("CIDADE"));
				Locac.setEstado(rs.getString("ESTADO"));
				Locac.setEndereco(rs.getString("ENDERECO"));

				lista.add(Locac);

			}

		} catch (SQLException e) {
			erroTabela(e, "locatario");
		}

		return lista;
	}

	public List<Locatario> buscarLocatarios(String nome) {

		List<Locatario> lista = new ArrayList<>();

		String sql = "SELECT * FROM locatario where LOCATARIO like '" + nome + "%'";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locatario Locac = new Locatario();

				Locac.setId(rs.getInt("ID_LOCATARIO"));
				Locac.setNome(rs.getString("LOCATARIO"));
				Locac.setTelefone(rs.getString("TELEFONE"));
				Locac.setCPF(rs.getString("CPF"));
				Locac.setNascimento(rs.getString("NASCIMENTO"));
				Locac.setSexo(rs.getString("SEXO"));
				Locac.setEmail(rs.getString("EMAIL"));

				Locac.setCidade(rs.getString("CIDADE"));
				Locac.setEstado(rs.getString("ESTADO"));
				Locac.setEndereco(rs.getString("ENDERECO"));

				lista.add(Locac);

			}

		} catch (SQLException e) {
			erroTabela(e, "locatario");
		}

		return lista;
	}

	public boolean salvarLocatario(Locatario Locac) {

		String sql = "INSERT INTO locatario (LOCATARIO, TELEFONE, CPF, NASCIMENTO, SEXO, EMAIL,CIDADE,ESTADO,ENDERECO) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setString(1, Locac.getNome());
			stmt.setString(2, Locac.getTelefone());
			stmt.setString(3, Locac.getCPF());
			stmt.setString(4, Locac.getNascimento());
			stmt.setString(5, Locac.getSexo());
			stmt.setString(6, Locac.getEmail());
			stmt.setString(7, Locac.getCidade());
			stmt.setString(8, Locac.getEstado());
			stmt.setString(9, Locac.getEndereco());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "Locatario");
			return false;
		}

		return true;

	}

	public boolean deletarLocatario(Locatario locac) {

		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM Locatario WHERE ID_LOCATARIO = ?");
			stmt.setInt(1, locac.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			erroTabela(e, "Locatario");
			return false;
		}
	}

	public boolean editarLocatario(Locatario locac) {
		Statement stmt = null;
		try {
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE locatario SET " + "LOCATARIO = '" + locac.getNome() + "', " + "TELEFONE = '"
					+ locac.getTelefone() + "'," + "CPF = '" + locac.getCPF() + "'," + "NASCIMENTO = '"
					+ locac.getNascimento() + "'," + "SEXO = '" + locac.getSexo() + "'," + "CIDADE = '"
					+ locac.getCidade() + "'," + "ESTADO = '" + locac.getEstado() + "'," + "ENDERECO = '"
					+ locac.getEndereco() + "'," + "EMAIL = '" + locac.getEmail() + "'" + " WHERE ID_LOCATARIO = '" + locac.getId() + "'";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			erroTabela(e, "locatario");
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
