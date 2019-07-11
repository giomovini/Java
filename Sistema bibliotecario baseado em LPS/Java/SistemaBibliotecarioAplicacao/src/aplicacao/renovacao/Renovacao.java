package aplicacao.renovacao;

import javax.swing.JOptionPane;

import gerenciador_locacoes.Locacao;
import teste.locacao.TabelaLocacao;


public class Renovacao implements IEfetuarRenovacao,IAlterarPrazoDevolucao{
	
	Locacao Loc;
	@Override
	public boolean realizarRenovacao(Locacao Locac) {
		boolean sucess = false;
		if (Locac.getDataDevolucao() != null) {
			JOptionPane.showMessageDialog(null, "Não é possivel renovar um empréstimo devolvido!");
		} else {
			sucess = alterarPrazoDevolucao(Locac);
			JOptionPane.showMessageDialog(null, sucess ? "Renovação realizada com sucesso!" : "Não foi possivel realizar a renovação!");
		}
		return sucess;
	}
	@Override
	public boolean alterarPrazoDevolucao(Locacao Locac) {
		return TabelaLocacao.getInstance().alterarPrazoDevolucacaoLocacao(Locac);
	}
}



