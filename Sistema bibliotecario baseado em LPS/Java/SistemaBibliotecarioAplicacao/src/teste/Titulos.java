package teste;

public enum Titulos {

	inicial("Inicial"), 
	inserirLocatario("Inserir Locat�rio"), 
	editarLocatario("Editar Locat�rio"),
	inserirExemplar("Inserir Exemplar"), 
	editarExemplar("Editar Exemplar"), 
	inserirLocacao("Inserir Loca��o"),
	renovacaoLocacao("Renova��o Loca��o"),
	inserirReserva("Renova��o Loca��o"),
	inserirCartaoAcesso("Inserir Cart�o de Acesso");

	public String Titulo;

	Titulos(String Titulo) {
		this.Titulo = "UTBiblio - " + Titulo;
	}

}
