package teste;

public enum Arquivos {
	
	 inicial("/teste/tela_inicial.fxml"),
	 inserirLocatario("/teste/locatario/tela_inserir_locatario.fxml"),
	 inserirExemplar("/teste/exemplar/tela_inserir_exemplar.fxml"),
	 inserirLocacao("/teste/locacao/tela_inserir_locacao.fxml"),
	 renovacaoLocacao("/teste/aplicacao/tela_renovacao.fxml"),
	 inserirReserva("/teste/aplicacao/tela_inserir_reserva.fxml"),
	 inserirCartaoAcesso("/teste/aplicacao/tela_inserir_cartao_acesso.fxml");
	 
	 
    public String Caminho;
    Arquivos(String Caminho) {
        this.Caminho = Caminho;
    }

}
