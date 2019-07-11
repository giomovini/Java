package teste.locacao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gerenciador_exemplares.Exemplar;
import gerenciador_locacoes.Locacao;
import gerenciador_locatarios.Locatario;
import teste.BancoOperacoes;

public class TabelaLocacao extends BancoOperacoes {

	private static TabelaLocacao Instancia;

	public static synchronized TabelaLocacao getInstance() {

		if (Instancia == null)
			Instancia = new TabelaLocacao();
		return Instancia;
	}
	
	public boolean editarLocacao(Locacao Loc) {
		Statement stmt = null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE locacao SET " + 
			"STATUS = '"+Loc.getStatus()+"',"+
			"DATA_DEVOLUCAO = '"+formato.format(new Date())+"'"+
					" WHERE ID_LOCACAO = '" + Loc.getIdentificador() + "'";
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
	
	public boolean alterarPrazoDevolucacaoLocacao(Locacao Loc) {
		Statement stmt = null;
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			stmt = bd.getConexao().createStatement();
			String sql = "UPDATE locacao SET " + 
			"PRAZO_DEVOLUCAO = '"+formato.format(Loc.getPrazoDevolucao())+"'"+
					" WHERE ID_LOCACAO = '" + Loc.getIdentificador() + "'";
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
	
	public boolean deletarLocacao(Locacao Locac) {
		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM locacao WHERE ID_LOCACAO = ?");
			stmt.setInt(1, Locac.getIdentificador());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			erroTabela(e, "Autor");
			return false;
		}
	}

	public boolean salvaLocacao(Locacao Loc) {

		String sql = "INSERT INTO locacao (ID_EXEMPLAR, ID_LOCATARIO, DATALOCACAO, PRAZO_DEVOLUCAO, STATUS) VALUES (?,?,?,?,?)";

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, Loc.getExemplar().getIdentificador());
			stmt.setInt(2, Loc.getLocatario().getId());
			stmt.setString(3, formato.format(Loc.getDataLocacao()));	
			stmt.setString(4, formato.format(Loc.getPrazoDevolucao()));
			stmt.setString(5, Loc.getStatus());
			
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "locacao");
			return false;
		}

		return true;

	}

	public List<Locacao> buscarLocacoes() {

		List<Locacao> lista = new ArrayList<>();

		String sql = "SELECT * FROM locacao left JOIN locatario on locatario.ID_LOCATARIO = locacao.ID_LOCATARIO LEFT JOIN exemplar on locacao.ID_EXEMPLAR = exemplar.ID_EXEMPLAR";

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();


			while (rs.next()) {
				
				
				Locacao Loc = new Locacao();
				Locatario Locac = new Locatario();
				Exemplar Ex = new Exemplar();

				Locac.setId(rs.getInt("ID_LOCATARIO"));
				Locac.setNome(rs.getString("LOCATARIO"));
				Locac.setTelefone(rs.getString("TELEFONE"));
				Locac.setCPF(rs.getString("CPF"));
				Locac.setNascimento(rs.getString("NASCIMENTO"));
				Locac.setSexo(rs.getString("SEXO"));
				Locac.setEmail(rs.getString("EMAIL"));

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
			
				Loc.setLocatario(Locac);
				Loc.setExemplar(Ex);


				if(rs.getDate("DATA_DEVOLUCAO") != null)
					Loc.setDataDevolucao(new Date(rs.getDate("DATA_DEVOLUCAO").getTime()));
				Loc.setDataLocacao(new Date(rs.getDate("DATALOCACAO").getTime()));
				Loc.setIdentificador(rs.getInt("ID_LOCACAO"));
				Loc.setPrazoDevolucao(new Date(rs.getDate("PRAZO_DEVOLUCAO").getTime()));
				Loc.setStatus(rs.getString("STATUS"));

				lista.add(Loc);

			}

		} catch (SQLException e) {
			erroTabela(e, "locacao");
		}

		return lista;
	}
	
	public List<Locacao> buscarLocacoes(String Locatario, String Exemplar, String Status) {

		List<Locacao> lista = new ArrayList<>();

		String sql = "SELECT * FROM locacao left JOIN locatario on locatario.ID_LOCATARIO = locacao.ID_LOCATARIO LEFT JOIN exemplar on locacao.ID_EXEMPLAR = exemplar.ID_EXEMPLAR";
		
		if(!Locatario.equals("") || !Exemplar.equals("") || !Status.equals("T") ) {
			sql += " WHERE ";
			boolean and = false;
			
			if(!Locatario.equals("") && Exemplar.equals("")) {
				sql += " locatario.LOCATARIO like '"+Locatario+"%'";
				and =true;
			}
			if(!Exemplar.equals("") && Locatario.equals("")) {
				sql += " exemplar.TITULO like '"+Exemplar+"%'";
				and =true;
			}
			if(!Locatario.equals("") && !Exemplar.equals("")) {
				sql += " locatario.LOCATARIO like '"+Locatario+"%'";
				sql += " AND exemplar.TITULO like '"+Exemplar+"%'";
				and =true;
			}
			
			
			if(!Status.equals("T")) {
				if(and) {
					sql += " AND";
				}
				sql += " STATUS like '"+Status+"'";
			}
		}

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();


			while (rs.next()) {
				
				
				Locacao Loc = new Locacao();
				Locatario Locac = new Locatario();
				Exemplar Ex = new Exemplar();

				Locac.setId(rs.getInt("ID_LOCATARIO"));
				Locac.setNome(rs.getString("LOCATARIO"));
				Locac.setTelefone(rs.getString("TELEFONE"));
				Locac.setCPF(rs.getString("CPF"));
				Locac.setNascimento(rs.getString("NASCIMENTO"));
				Locac.setSexo(rs.getString("SEXO"));
				Locac.setEmail(rs.getString("EMAIL"));

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
			
				Loc.setLocatario(Locac);
				Loc.setExemplar(Ex);


				if(rs.getDate("DATA_DEVOLUCAO") != null)
					Loc.setDataDevolucao(new Date(rs.getDate("DATA_DEVOLUCAO").getTime()));
				Loc.setDataLocacao(new Date(rs.getDate("DATALOCACAO").getTime()));
				Loc.setIdentificador(rs.getInt("ID_LOCACAO"));
				Loc.setPrazoDevolucao(new Date(rs.getDate("PRAZO_DEVOLUCAO").getTime()));
				Loc.setStatus(rs.getString("STATUS"));

				lista.add(Loc);

			}

		} catch (SQLException e) {
			erroTabela(e, "locacao");
		}

		return lista;
	}
	
}
