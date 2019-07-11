package gerenciador_locacoes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import gerenciador_exemplares.Exemplar;
import gerenciador_locatarios.Locatario;
import teste.locacao.TabelaLocacao;

public class Locacao
		implements IEfetuarDevolucao, IEfetuarLocacao, IAlterarStatusLocacao, IBuscarLocacao, IValidarRegistroLocacao {

	private int identificador;
	private Date dataLocacao, prazoDevolucao, dataDevolucao;
	private String Status;
	private Exemplar exemplar;
	private Locatario locatario;

	@Override
	public List<Locacao> buscarLocacoes() {
		return TabelaLocacao.getInstance().buscarLocacoes();
	}

	@Override
	public List<Locacao> buscarLocacoes(String Loc, String Ex, String Status) {
		return TabelaLocacao.getInstance().buscarLocacoes(Loc, Ex, Status);
	}

	@Override
	public boolean validarLocacao(Locacao Locac) {
		if (Locac.getPrazoDevolucao() == null || Locac.getExemplar() == null
				|| Locac.getExemplar().getQtdeDisponivel() == 0 || Locac.getLocatario() == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean realizarLocacao(Locacao Locac) {
		boolean sucess = Locac.validarLocacao(Locac);

		if (sucess) {
			sucess = Locac.getExemplar().alterarQtdeDisponivel(Locac.getExemplar(),
					Locac.getExemplar().getQtdeDisponivel() - 1);
			if (sucess)
				sucess = TabelaLocacao.getInstance().salvaLocacao(Locac);
		} else {
			sucess = false;
		}
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		JOptionPane.showMessageDialog(null, sucess
				? "Empréstimo salvo com sucesso!\nA devolução deve ser feita até " + formato.format(Locac.getPrazoDevolucao())
				: "Não foi possivel registrar o empréstimo, confira a disponibilidade do exemplar e a data do prazo de devolução e tente novamente!");
		return sucess;
	}

	@Override
	public boolean realizarDevolucao(Locacao Locac) {
		boolean sucess = Locac.getExemplar().alterarQtdeDisponivel(Locac.getExemplar(),
				Locac.getExemplar().getQtdeDisponivel() + 1);

		if (sucess)
			sucess = alterarStatusLocacao(Locac);

		JOptionPane.showMessageDialog(null, sucess ? "Empréstimo devolvido com sucesso!" : "Houve falha na devolução!");
		return sucess;
	}

	@Override
	public boolean alterarStatusLocacao(Locacao Locac) {
		if (!Locac.getStatus().equals("DEVOLVIDO")) {
			Locac.setStatus("DEVOLVIDO");
			return TabelaLocacao.getInstance().editarLocacao(Locac);
		}
		return false;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getPrazoDevolucao() {
		return prazoDevolucao;
	}

	public void setPrazoDevolucao(Date prazoDevolucao) {
		this.prazoDevolucao = prazoDevolucao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

}
