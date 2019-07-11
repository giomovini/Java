package teste.aplicacao;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicacao.reserva.Reserva;
import gerenciador_exemplares.Exemplar;
import gerenciador_locatarios.Locatario;
import teste.BancoOperacoes;


public class TabelaReserva extends BancoOperacoes {

	private static TabelaReserva Instancia;

	public static synchronized TabelaReserva getInstance() {

		if (Instancia == null)
			Instancia = new TabelaReserva();
		return Instancia;
	}
	
	public List<Reserva> buscarReservas() {

		List<Reserva> lista = new ArrayList<>();

		String sql = "SELECT reserva.IDENTIFICADOR,reserva.DATA_RESERVA,locatario.LOCATARIO,exemplar.TITULO FROM reserva "
				+ "LEFT JOIN locatario on locatario.ID_LOCATARIO = reserva.ID_LOCATARIO "
				+ "LEFT JOIN exemplar ON exemplar.ID_EXEMPLAR = reserva.ID_EXEMPLAR";
		

		try {
			
			
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			
			while (rs.next()) {
				
				Reserva reserva = new Reserva();
				
				Locatario Locac = new Locatario();
				Exemplar Ex = new Exemplar();
				Locac.setNome(rs.getString("LOCATARIO"));
				Ex.setTitulo(rs.getString("TITULO"));
				reserva.setDataReserva(new Date(rs.getDate("DATA_RESERVA").getTime()));
				reserva.setIdentificador(rs.getInt("IDENTIFICADOR"));

				reserva.setLoc(Locac);
				reserva.setEx(Ex);

				lista.add(reserva);

			}	

		} catch (SQLException e) {
			e.printStackTrace();
			erroTabela(e, "reserva");
		}

		return lista;
	}

	
	public boolean salvarReserva(Reserva Res) {

		String sql = "INSERT INTO reserva (ID_EXEMPLAR, ID_LOCATARIO, DATA_RESERVA) VALUES (?,?,?)";

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, Res.getEx().getIdentificador());
			stmt.setInt(2, Res.getLoc().getId());
			stmt.setString(3, formato.format(Res.getDataReserva()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "reserva");
			return false;
		}

		return true;

	}

	public boolean deletarReserva(Reserva Res) {
		
		System.out.println(Res.getIdentificador());

		try {
			stmt = bd.getConexao().prepareStatement("DELETE FROM reserva WHERE IDENTIFICADOR = ?");
			stmt.setInt(1, Res.getIdentificador());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			erroTabela(e, "reserva");
			return false;
		}
	}
}
