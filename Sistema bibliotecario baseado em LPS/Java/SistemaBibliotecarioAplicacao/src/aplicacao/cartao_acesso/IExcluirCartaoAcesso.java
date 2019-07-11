package aplicacao.cartao_acesso;

import java.util.List;

public interface IExcluirCartaoAcesso {

	public boolean excluirCartao(CartaoAcesso Cart);
	
	public List<CartaoAcesso> listarCartaoAcesso();
	
}
