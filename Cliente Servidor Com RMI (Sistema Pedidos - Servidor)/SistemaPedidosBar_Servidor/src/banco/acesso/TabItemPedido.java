package banco.acesso;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import TipoDado.ItemPedido;
import TipoDado.Produto;
import banco.conexao.BancoOperacoes;


public class TabItemPedido extends BancoOperacoes {

	static TabItemPedido instancia = null;

	private TabItemPedido() {

	}

	public static synchronized TabItemPedido getInstance() {

		if (instancia == null) {
			instancia = new TabItemPedido();
		}

		return instancia;

	}
	
	
	public boolean inserirItemPedido(String id_produto,String id_pedido,int quantidade) {
		System.out.println(this.getClass().getName() + " inserirItemPedido");

		String sql = "INSERT INTO item_pedido(ID_PRODUTO,ID_PEDIDO,QUANTIDADE)VALUES(?,?,?)";

		try {
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(id_produto));
			stmt.setInt(2, Integer.parseInt(id_pedido));
			stmt.setInt(3, quantidade);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			erroTabela(e, "item_pedido");
			return false;
		}

		return true;

	}
	
	public ArrayList<ItemPedido> buscarInformacoesTodosItensPedidos() {

		System.out.println(this.getClass().getName() + " buscarInformacoesTodosProdutos");

		ArrayList<ItemPedido> lista = new ArrayList<>();

		String sql = "SELECT * FROM item_pedido";


		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				
				ItemPedido i = new ItemPedido();
				
				i.setId_Pedido(String.valueOf(rs.getInt("ID_PEDIDO")));
				i.setQuantidade(rs.getInt("QUANTIDADE"));
				i.setIdProduto(String.valueOf(rs.getInt("ID_PRODUTO")));
				i.setIdItemPedido(String.valueOf(rs.getInt("ID_ITEM_PEDIDO")));

				lista.add(i);
			}

		} catch (Exception e) {
			erroTabela(e, "item_pedido");
			return null;
		}
		
		
		for (int i = 0; i < lista.size(); i++) {
			Produto prod = TabProduto.getInstance().buscarProduto(lista.get(i).getIdProduto());
			lista.get(i).setNomeProduto(prod.getNOME());
			lista.get(i).setPrecoUnid(prod.getPreco());
			lista.get(i).setPrecototal(prod.getPreco()*lista.get(i).getQuantidade());
		}
		
		return lista;

	}
	
	
	

}
