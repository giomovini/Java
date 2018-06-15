package banco.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*
 * classe que chama 'AcessoBanco' para 
 * pegar as informacoes do banco,
 * e tenta fazer a conexao
 * */

public class BancoConexao {

	// Objeto autoreferenciado para criacao
	// do padao de projeto singleton
	private static BancoConexao INSTANCIA;

	// Objeto que contem informacoes do banco ( login,senha..)
	private BancoInformacoes dadosBanco = new BancoInformacoes();

	// identifica a classe dentro do jar (conector mysql)
	// Objeto responsavel pela conexao do java com o banco
	public static Connection conexao = null;

	// JDBC = Java Database Connection = protocolo de comunicação
	// entre o java e o banco de dados
	private final String DRIVER = "com.mysql.jdbc.Driver";

	/*
	 * tenta fazer a conexao do banco
	 */
	public boolean conectar() {

		try {
			// procura pelo driver JDBC (.jar)
			Class.forName(DRIVER);

			conexao = DriverManager.getConnection(dadosBanco.getCaminhoNomeBanco(), dadosBanco.getUsuario(),
					dadosBanco.getSenha());

			System.out.println("Conectando com o banco de dados... OK");
			return true;

		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado " + e.toString());
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Falha ao conectar " + e.toString());
			e.printStackTrace();
		}

		return false;
	}

	// retorna o objeto que contem a 'conexao'
	public Connection getConexao() {
		return conexao;
	}

	// vetifica se a conexao Ã© null
	// caso verdade, informa o erro
	// e finaliza o aplicativo
	private void verificaConecao() {

		if (!conectar()) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Login");
			erro.setResizable(true);
			erro.setContentText("Erro ao conctar-se com  banco de dados\nVerifique sua conexão com a internet\n"
					+ "Se persistir contate o suporte");
			erro.showAndWait();
			System.exit(0);
		}

	}
	
	public boolean isConected(){
		return (conexao != null);
	}

	// metodo construtor
	// sempre que instanciada a classe tenta fazer a conexao
	private BancoConexao() {
		if (conexao == null)
			verificaConecao();
	}

	// metodo que chama a instancia da classe
	// Padrao de projeto singleton
	// a classe sÃ³ sera instanciada por esse metodo
	public static synchronized BancoConexao getInstance() {

		if (INSTANCIA == null)
			INSTANCIA = new BancoConexao();
		return INSTANCIA;
	}
	

}

