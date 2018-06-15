package banco.acesso;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import TipoDado.Pedido;
import banco.conexao.BancoOperacoes;

public class TabPedido extends BancoOperacoes {

	static TabPedido instancia = null;

	private TabPedido() {

	}

	public static synchronized TabPedido getInstance() {

		if (instancia == null) {
			instancia = new TabPedido();
		}

		return instancia;

	}

	public boolean inserirConta(String cpfCliente, String mesa) {
		System.out.println(this.getClass().getName() + " inserirConta");

		String sql = "INSERT INTO pedido(ID_CLIENTE,DATA,MESA,FINALIZADO)VALUES(?,?,?,?)";

		int idCliente = Integer.parseInt(TabCliente.getInstance().buscarInformacoesCliente(cpfCliente).getId());

		try {
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, idCliente);
			stmt.setString(2, TabPedido.getInstance().dataAtual());
			stmt.setString(3, mesa);
			stmt.setInt(4, 0);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "pedido");
			return false;
		}

		return true;

	}

	public boolean finalizarConta(String idConta) {
		System.out.println(this.getClass().getName() + " finalizarConta");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("UPDATE pedido SET " + "FINALIZADO = '1' WHERE ID_PEDIDO = '" + idConta + "'");

		} catch (SQLException e) {
			erroTabela(e, "pedido");
			return false;
		}
		return true;

	}

	public boolean editarConta(String idContaAntigo, String idCliente, String mesa) {
		System.out.println(this.getClass().getName() + " finalizarConta");

		Statement st;

		try {
			st = bd.getConexao().createStatement();
			st.executeUpdate("UPDATE pedido SET " + "ID_CLIENTE = '" + idCliente + "', MESA = '" + mesa
					+ "' WHERE ID_PEDIDO = '" + idContaAntigo + "'");

		} catch (SQLException e) {
			erroTabela(e, "pedido");
			return false;
		}
		return true;

	}
	
	public Pedido buscaPedido(String idPedido) {

		Pedido p = null;

		System.out.println(this.getClass().getName() + " buscaPedido");

		String sql = "SELECT * FROM pedido WHERE ID_PEDIDO = " + idPedido;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Pedido();

				p.setId_pedido(rs.getInt("ID_PEDIDO"));
				p.setId_cliente(rs.getInt("ID_CLIENTE"));
				p.setData(rs.getString("DATA"));
				p.setMesa(rs.getInt("MESA"));
				p.setFinalizado(rs.getInt("FINALIZADO"));
			}

		} catch (Exception e) {
			erroTabela(e, "pedido");
			return p;
		}

		return p;

	}
	
	public int buscaIdPedido(String id_cliente) {

		int r = 0;

		System.out.println(this.getClass().getName() + " buscarTodosPedidos");

		String sql = "SELECT * FROM pedido WHERE FINALIZADO = 0 AND ID_CLIENTE = " + id_cliente;

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				r = rs.getInt("ID_PEDIDO");
			}

		} catch (Exception e) {
			erroTabela(e, "pedido");
			return r;
		}

		return r;

	}

	public ArrayList<Pedido> buscarTodosPedidos() {

		System.out.println(this.getClass().getName() + " buscarTodosPedidos");

		String sql = "SELECT * FROM pedido ";

		ArrayList<Pedido> lista = new ArrayList<>();

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido p = new Pedido();

				p.setId_pedido(rs.getInt("ID_PEDIDO"));
				p.setId_cliente(rs.getInt("ID_CLIENTE"));
				p.setData(rs.getString("DATA"));
				p.setMesa(rs.getInt("MESA"));
				p.setFinalizado(rs.getInt("FINALIZADO"));

				lista.add(p);
			}

		} catch (Exception e) {
			erroTabela(e, "pedido");
			return null;
		}

		return lista;

	}

	private String dataAtual() {
		Calendar atual = GregorianCalendar.getInstance(Locale.US);
		String dia, mes, ano;

		dia = String.valueOf(atual.get(Calendar.DAY_OF_MONTH));
		if (dia.length() == 1)
			dia = "0" + dia;
		mes = String.valueOf(atual.get(Calendar.MONTH) + 1);
		if (mes.length() == 1)
			mes = "0" + mes;
		ano = String.valueOf(atual.get(Calendar.YEAR));

		return ano + "-" + mes + "-" + dia;
	}

}
