package teste.locacao;

import gerenciador_locacoes.Locacao;

public class LinhaLocacao {
	
	private Locacao Loc;
	private String DataLocacao,PrazoDevolucao,DataDevolucao,Status,Exemplar,Locatario;
	
	
	public Locacao getLoc() {
		return Loc;
	}
	public void setLoc(Locacao loc) {
		Loc = loc;
	}
	public String getDataLocacao() {
		return DataLocacao;
	}
	public void setDataLocacao(String dataLocacao) {
		DataLocacao = dataLocacao;
	}
	public String getPrazoDevolucao() {
		return PrazoDevolucao;
	}
	public void setPrazoDevolucao(String prazoDevolucao) {
		PrazoDevolucao = prazoDevolucao;
	}
	public String getDataDevolucao() {
		return DataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		DataDevolucao = dataDevolucao;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getExemplar() {
		return Exemplar;
	}
	public void setExemplar(String exemplar) {
		Exemplar = exemplar;
	}
	public String getLocatario() {
		return Locatario;
	}
	public void setLocatario(String locatario) {
		Locatario = locatario;
	}

	
}
