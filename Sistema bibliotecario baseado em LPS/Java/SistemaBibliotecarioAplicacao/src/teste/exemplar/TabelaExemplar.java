package teste.exemplar;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gerenciador_exemplares.Autor;
import gerenciador_exemplares.Exemplar;
import teste.BancoOperacoes;

public class TabelaExemplar extends BancoOperacoes {

	private static TabelaExemplar Instancia;

	public static synchronized TabelaExemplar getInstance() {

		if (Instancia == null)
			Instancia = new TabelaExemplar();
		return Instancia;
	}

	public List<Exemplar> buscarLocatarios() {

		List<Exemplar> lista = new ArrayList<>();

		String sql = "SELECT * FROM exemplar LEFT JOIN autor on exemplar.ID_AUTOR = autor.ID_AUTOR";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Exemplar Ex = new Exemplar();

				Ex.setIdentificador(rs.getInt("ID_EXEMPLAR"));
				Ex.setTitulo(rs.getString("TITULO"));
				Ex.setIdioma(rs.getString("IDIOMA"));
				Ex.setEdicao(Integer.parseInt(rs.getString("EDICAO")));
				Ex.setAnoLancamento(Integer.parseInt(rs.getString("ANO_LANC")));
				Ex.setQtdeDisponivel(rs.getInt("QTDE_DISP"));
				Ex.setCategoria(rs.getString("CATEGORIA"));
				Ex.setClassificacaoIdade(Integer.parseInt(rs.getString("CLASSIFICACAO")));
				Ex.setLocalizacao(rs.getString("LOCALIZACAO"));
				Ex.setCodBarras(rs.getString("COD_BARRAS"));

				Autor autor = new Autor();
				autor.setId(rs.getInt("ID_AUTOR"));
				autor.setNome(rs.getString("AUTOR"));
				autor.setWebSite(rs.getString("WEBSITE"));

				Ex.setAutor(autor);

				lista.add(Ex);

			}

		} catch (SQLException e) {
			erroTabela(e, "exemplar");
		}

		return lista;
	}

	public List<Exemplar> buscarExemplar(String Exemplar, String Autor) {

		List<Exemplar> lista = new ArrayList<>();

		String sql = "SELECT * FROM exemplar LEFT JOIN autor on exemplar.ID_AUTOR = autor.ID_AUTOR";
		if (!Exemplar.equals("") && !Autor.equals("")) {
			sql += " where TITULO like '" + Exemplar + "%' and autor like '" + Autor + "%'";
		} else if (!Exemplar.equals("")) {
			sql += " where TITULO like '" + Exemplar + "%'";
		} else if (!Autor.equals("")) {
			sql += " where autor like '" + Autor + "%'";
		}

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Exemplar Ex = new Exemplar();

				Ex.setIdentificador(rs.getInt("ID_EXEMPLAR"));
				Ex.setTitulo(rs.getString("TITULO"));
				Ex.setIdioma(rs.getString("IDIOMA"));
				Ex.setEdicao(Integer.parseInt(rs.getString("EDICAO")));
				Ex.setAnoLancamento(Integer.parseInt(rs.getString("ANO_LANC")));
				Ex.setQtdeDisponivel(rs.getInt("QTDE_DISP"));
				Ex.setCategoria(rs.getString("CATEGORIA"));
				Ex.setClassificacaoIdade(Integer.parseInt(rs.getString("CLASSIFICACAO")));
				Ex.setLocalizacao(rs.getString("LOCALIZACAO"));
				Ex.setCodBarras(rs.getString("COD_BARRAS"));

				Autor autor = new Autor();
				autor.setId(rs.getInt("ID_AUTOR"));
				autor.setNome(rs.getString("AUTOR"));
				autor.setWebSite(rs.getString("WEBSITE"));

				Ex.setAutor(autor);

				lista.add(Ex);

			}

		} catch (SQLException e) {
			erroTabela(e, "exemplar");
		}

		return lista;
	}

	public boolean salvarExemplar(Exemplar Ex) {
		
		String sql = "INSERT INTO Autor (AUTOR, WEBSITE) VALUES (?,?);";
		try {
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setString(1, Ex.getAutor().getNome());
			stmt.setString(2, Ex.getAutor().getWebSite());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "Autor");
			return false;
		}
		
		
		sql = "SELECT ID_AUTOR FROM Autor order by ID_AUTOR desc";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			// CONDICAO PARA INSERIR NA LISTA quantidade material >0
			if (rs.next()) {
				Ex.getAutor().setId(rs.getInt("ID_AUTOR"));
			}

		} catch (SQLException e) {
			erroTabela(e, "Autor");
		}

		sql = "INSERT INTO exemplar (ID_AUTOR, TITULO, IDIOMA, EDICAO, ANO_LANC, QTDE_DISP, CATEGORIA, CLASSIFICACAO, LOCALIZACAO, COD_BARRAS) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, Ex.getAutor().getId());
			stmt.setString(2, Ex.getTitulo());
			stmt.setString(3, Ex.getIdioma());
			stmt.setString(4, String.valueOf(Ex.getEdicao()));
			stmt.setString(5, String.valueOf(Ex.getAnoLancamento()));
			stmt.setInt(6, Ex.getQtdeDisponivel());
			stmt.setString(7, Ex.getCategoria());
			stmt.setString(8, String.valueOf(Ex.getClassificacaoIdade()));
			stmt.setString(9, Ex.getLocalizacao());
			stmt.setString(10, Ex.getCodBarras());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "exemplar");
			return false;
		}
		
		
		

		return true;

	}

	public boolean deletarExemplar(Exemplar Ex) {

		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM exemplar WHERE ID_EXEMPLAR = ?");
			stmt.setInt(1, Ex.getIdentificador());
			stmt.executeUpdate();
		} catch (SQLException e) {
			erroTabela(e, "Exemplar");
			return false;
		}
		
		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM Autor WHERE ID_AUTOR = ?");
			stmt.setInt(1, Ex.getAutor().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			erroTabela(e, "Autor");
			return false;
		}
		
		return true;
	}

	public boolean editarExemplar(Exemplar Ex) {
		Statement stmt = null;
		try {
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE exemplar SET " + "TITULO = '" + Ex.getTitulo() + "', " + "IDIOMA = '" + Ex.getIdioma()
					+ "'," + "EDICAO = '" + Ex.getEdicao() + "'," + "ANO_LANC = '" + Ex.getAnoLancamento() + "',"
					+ "QTDE_DISP = '" + Ex.getQtdeDisponivel() + "'," + "CATEGORIA = '" + Ex.getCategoria() + "',"
					+ "CLASSIFICACAO = '" + Ex.getClassificacaoIdade() + "'," + "LOCALIZACAO = '" + Ex.getLocalizacao()
					+ "'," + "COD_BARRAS = '" + Ex.getCodBarras() + "'" + " WHERE ID_EXEMPLAR = '"
					+ Ex.getIdentificador() + "'";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			erroTabela(e, "exemplar");
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE Autor SET " + 
			"AUTOR = '" + Ex.getAutor().getNome() + "', " + 
					"WEBSITE = '"+ Ex.getAutor().getWebSite() + "'" +
					" WHERE ID_AUTOR = '" + Ex.getAutor().getId() + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			erroTabela(e, "Autor");
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

	public boolean editarQtdeExemplar(int qtde, int id) {
		Statement stmt = null;
		try {
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE exemplar SET " + "QTDE_DISP = " + qtde + "" + " WHERE ID_EXEMPLAR = " + id + "";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			erroTabela(e, "exemplar");
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
