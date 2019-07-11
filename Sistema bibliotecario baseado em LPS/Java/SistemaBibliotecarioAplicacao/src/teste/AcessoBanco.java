package teste;

/* Classe responsavel por guardar
 * as informacoes sobre o banco de dados
 * */

public final class AcessoBanco {
	
	
	
	private final String USUARIO = "root";
	private final String SENHA = "";
	private final String IP = "127.0.0.1";
	private final String PORTA = "3306";
	private final String DB = "sistema_bibliotecario_aplicacao";
	
	private final String URL = "jdbc:mysql://"+IP+":"+PORTA+"/"+DB;
	
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
