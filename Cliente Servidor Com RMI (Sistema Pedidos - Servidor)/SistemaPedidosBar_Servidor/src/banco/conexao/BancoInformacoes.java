package banco.conexao;

public final class BancoInformacoes {
	
	private final String USUARIO = "root";
	private final String SENHA = "";
	private final String IP = "127.0.0.1";
	private final String URL = "jdbc:mysql://"+IP+":3306/sistemapedidosbar";
	
	public String getIP() {
		return IP;
	}
	
	public String getUsuario() {
		return USUARIO;
	}
	public String getSenha() {
		return SENHA;
	}
	public String getCaminhoNomeBanco() {
		return URL;
	}
	
}

