package teste;

public enum Titulos {

	inicial("Inicial"), 
	inserirLocatario("Inserir Locatário"), 
	editarLocatario("Editar Locatário"),
	inserirExemplar("Inserir Exemplar"), 
	editarExemplar("Editar Exemplar"), 
	inserirLocacao("Inserir Locação"),
	renovacaoLocacao("Renovação Locação"),
	inserirReserva("Renovação Locação"),
	inserirCartaoAcesso("Inserir Cartão de Acesso");

	public String Titulo;

	Titulos(String Titulo) {
		this.Titulo = "UTBiblio - " + Titulo;
	}

}
