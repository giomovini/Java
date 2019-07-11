package aplicacao.cartao_acesso;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import gerenciador_locatarios.Locatario;
import teste.aplicacao.TabelaCartaoAcesso;

public class CartaoAcesso implements ICadastrarCartaoAcesso,IExcluirCartaoAcesso{

	Locatario Locatario_;
	int identificador;
	String CodBarras;
	Date Validade;
	
	@Override
	public boolean excluirCartao(CartaoAcesso Cart) {
		boolean sucess = true;
		sucess = TabelaCartaoAcesso.getInstance().deletarCartaoAcesso(Cart);
		JOptionPane.showMessageDialog(null, sucess ? "Registro deletado com sucesso!"
				: "Não foi possivel realizar a deleção do cartão de acesso");
		return sucess;
	}

	@Override
	public boolean cadastrarCartao(CartaoAcesso Cart) {
		boolean sucess = false;
		if(Cart.getValidade() == null || Cart.getCodBarras().equals("") || Cart.getLocatario_() == null) {
			sucess = false;
		}else {
			sucess = TabelaCartaoAcesso.getInstance().salvarCartaoAcesso(Cart);
		}
		JOptionPane.showMessageDialog(null, sucess ? "Registro inserido com sucesso!": "Não foi possivel salvar o cartão de acesso!");
		
		return sucess;
	}
	
	@Override
	public List<CartaoAcesso> listarCartaoAcesso() {
		return TabelaCartaoAcesso.getInstance().buscarCartaoAcesso();
	}

	public Locatario getLocatario_() {
		return Locatario_;
	}

	public void setLocatario_(Locatario locatario_) {
		Locatario_ = locatario_;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getCodBarras() {
		return CodBarras;
	}

	public void setCodBarras(String codBarras) {
		CodBarras = codBarras;
	}

	public Date getValidade() {
		return Validade;
	}

	public void setValidade(Date validade) {
		Validade = validade;
	}



}
