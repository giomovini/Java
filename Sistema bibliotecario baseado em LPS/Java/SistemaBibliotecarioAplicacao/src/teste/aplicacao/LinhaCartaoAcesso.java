package teste.aplicacao;

import aplicacao.cartao_acesso.CartaoAcesso;

public class LinhaCartaoAcesso {
	
	CartaoAcesso Cartao;
	String Cod_Barras,locatario,Validade;
	public CartaoAcesso getCartao() {
		return Cartao;
	}
	public void setCartao(CartaoAcesso cartao) {
		Cartao = cartao;
	}
	public String getCod_Barras() {
		return Cod_Barras;
	}
	public void setCod_Barras(String cod_Barras) {
		Cod_Barras = cod_Barras;
	}
	public String getLocatario() {
		return locatario;
	}
	public void setLocatario(String locatario) {
		this.locatario = locatario;
	}
	public String getValidade() {
		return Validade;
	}
	public void setValidade(String validade) {
		Validade = validade;
	}

	
	


	
}
