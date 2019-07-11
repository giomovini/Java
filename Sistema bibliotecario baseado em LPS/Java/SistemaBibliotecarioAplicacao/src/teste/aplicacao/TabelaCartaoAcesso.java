package teste.aplicacao;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicacao.cartao_acesso.CartaoAcesso;
import gerenciador_locatarios.Locatario;
import teste.BancoOperacoes;


public class TabelaCartaoAcesso extends BancoOperacoes {

	private static TabelaCartaoAcesso Instancia;

	public static synchronized TabelaCartaoAcesso getInstance() {

		if (Instancia == null)
			Instancia = new TabelaCartaoAcesso();
		return Instancia;
	}
	
	public List<CartaoAcesso> buscarCartaoAcesso() {

		List<CartaoAcesso> lista = new ArrayList<>();

		String sql = "SELECT cartao_acesso.IDENTIFICADOR,cartao_acesso.VALIDADE,cartao_acesso.COD_BARRAS,locatario.LOCATARIO FROM cartao_acesso "
				+ "LEFT JOIN locatario on locatario.ID_LOCATARIO = cartao_acesso.ID_LOCATARIO ";
		
		try {
			
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			
			while (rs.next()) {
				
				CartaoAcesso cartaoAcesso = new CartaoAcesso();
				
				Locatario Locac = new Locatario();
				Locac.setNome(rs.getString("LOCATARIO"));
				cartaoAcesso.setCodBarras(rs.getString("COD_BARRAS"));
				cartaoAcesso.setIdentificador(rs.getInt("IDENTIFICADOR"));
				cartaoAcesso.setLocatario_(Locac);
				cartaoAcesso.setValidade(new Date(rs.getDate("VALIDADE").getTime()));
				
				lista.add(cartaoAcesso);

			}	

		} catch (SQLException e) {
			e.printStackTrace();
			erroTabela(e, "cartao_acesso");
		}

		return lista;
	}

	
	public boolean salvarCartaoAcesso(CartaoAcesso Cartao) {

		String sql = "INSERT INTO cartao_acesso (ID_LOCATARIO, COD_BARRAS, VALIDADE) VALUES (?,?,?)";

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, Cartao.getLocatario_().getId());
			stmt.setString(2, Cartao.getCodBarras());
			stmt.setString(3, formato.format(Cartao.getValidade()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "reserva");
			return false;
		}

		return true;

	}

	public boolean deletarCartaoAcesso(CartaoAcesso Cartao) {

		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM cartao_acesso WHERE IDENTIFICADOR = ?");
			stmt.setInt(1, Cartao.getIdentificador());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			erroTabela(e, "reserva");
			return false;
		}
	}
}
