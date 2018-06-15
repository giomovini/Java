package TipoDado;

public class ItemPedido implements I_ItemPedido{

	private static final long serialVersionUID = -4283065882424570108L;


	String IdItemPedido,IdProduto,Id_Pedido,NomeProduto;
	Integer Quantidade;
	Double Precototal,PrecoUnid;
	
	public String getIdItemPedido() {
		return IdItemPedido;
	}
	public void setIdItemPedido(String idItemPedido) {
		IdItemPedido = idItemPedido;
	}
	public String getIdProduto() {
		return IdProduto;
	}
	public void setIdProduto(String idProduto) {
		IdProduto = idProduto;
	}
	public String getId_Pedido() {
		return Id_Pedido;
	}
	public void setId_Pedido(String id_Pedido) {
		Id_Pedido = id_Pedido;
	}
	public String getNomeProduto() {
		return NomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}
	public Integer getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}
	public Double getPrecototal() {
		return Precototal;
	}
	public void setPrecototal(Double precototal) {
		Precototal = precototal;
	}
	public Double getPrecoUnid() {
		return PrecoUnid;
	}
	public void setPrecoUnid(Double precoUnid) {
		PrecoUnid = precoUnid;
	}

	

}
