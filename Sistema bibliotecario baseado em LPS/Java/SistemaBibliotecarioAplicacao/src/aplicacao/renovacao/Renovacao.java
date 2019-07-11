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
			JOptionPane.showMessageDialog(null, "N�o � possivel renovar um empr�stimo devolvido!");
		} else {
			sucess = alterarPrazoDevolucao(Locac);
			JOptionPane.showMessageDialog(null, sucess ? "Renova��o realizada com sucesso!" : "N�o foi possivel realizar a renova��o!");
		}
		return sucess;
	}
	@Override
	public boolean alterarPrazoDevolucao(Locacao Locac) {
		return TabelaLocacao.getInstance().alterarPrazoDevolucacaoLocacao(Locac);
	}
}



