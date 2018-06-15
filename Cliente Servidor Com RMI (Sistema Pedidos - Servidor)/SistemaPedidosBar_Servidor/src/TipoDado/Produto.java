package TipoDado;

import javafx.scene.control.Button;

public class Produto implements IProduto{

	private static final long serialVersionUID = -2628587019261729683L;
	
	String ID, NOME, DESCRICAO, VALIDADE,TIPO;

	Double preco;
	Integer quantidade;
	Button btnEditarDeletar;
	
	
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	
	public Button getBtnEditarDeletar() {
		return btnEditarDeletar;
	}
	public void setBtnEditarDeletar(Button btnEditarDeletar) {
		this.btnEditarDeletar = btnEditarDeletar;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}
	public String getVALIDADE() {
		return VALIDADE;
	}
	public void setVALIDADE(String vALIDADE) {
		VALIDADE = vALIDADE;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
