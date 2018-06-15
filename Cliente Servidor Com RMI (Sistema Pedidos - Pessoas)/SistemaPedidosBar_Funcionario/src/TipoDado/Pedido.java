package TipoDado;

import javafx.scene.control.Button;

public class Pedido implements IPedido{

	private static final long serialVersionUID = -5035503422577628209L;
	
	private Integer id_pedido,id_cliente,mesa,finalizado;
	private String data;
	private Button EditarDeletar;
	
	public Integer getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getMesa() {
		return mesa;
	}
	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}
	public Integer getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(Integer finalizado) {
		this.finalizado = finalizado;
	}
	public Button getEditarDeletar() {
		return EditarDeletar;
	}
	public void setEditarDeletar(Button editarDeletar) {
		EditarDeletar = editarDeletar;
	}

}
