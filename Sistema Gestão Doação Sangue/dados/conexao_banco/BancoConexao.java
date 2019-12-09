package dados.conexao_banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*
 * classe que chama 'AcessoBanco' para 
 * pegar as informacoes do banco,
 * e tenta fazer a conexao
 * */
public class BancoConexao {

    public Alert erro;
    // Objeto autoreferenciado para criacao
    // do padao de projeto singleton
    private static BancoConexao INSTANCIA;

    // Objeto que contem informacoes do banco ( login,senha..)
    // identifica a classe dentro do jar (conector mysql)
    // Objeto responsavel pela conexao do java com o banco
    public static Connection conexao = null;

    // JDBC = Java Database Connection = protocolo de comunica��o
    // entre o java e o banco de dados
    private final String DRIVER = "com.mysql.jdbc.Driver";

    public static void fechar() {

        try {
            System.out.print("\nFechando conexao com o banco de dados...");
            BancoConexao.conexao.close();
            BancoConexao.conexao = null;
            System.out.println("OK");

        } catch (SQLException e) {
            System.out.println("\nErro ao fechar a conexao: " + e.toString());
        }
    }

    public void reconectar() {
        System.out.println(this.getClass() + " - method: reconectar ");
        fechar();
        conectar();
    }

    //Quando um erro no carregamento da tabela ocorrer esse sera o tratamento
    // colocado no catch
    public void erroTabela(SQLException e, String tabela) {
        System.out.println(this.getClass() + " - method: erroTabela ");

        BancoConexao.getInstance().reconectar();

        JOptionPane.showMessageDialog(null, "Erro com conexao, tentando novamente");
        if(erro == null)
            erro =new Alert(AlertType.ERROR);

        erro.setHeaderText("Erro fatal ao carregar tabela " + tabela);
        erro.setContentText(e.getMessage());
        erro.showAndWait();
    }

    /*
	 * tenta fazer a conexao do banco
     */
    private boolean conectar() {
        System.out.println(this.getClass() + " - method: conectar ");

        try {
            // procura pelo driver JDBC (.jar)
            Class.forName(DRIVER);

            AcessoBanco acesso = AcessoBanco.getInstance();
            System.out.println(acesso.getUrl_Conexao());

            conexao = DriverManager.getConnection(acesso.getUrl_Conexao(), acesso.getUsuario(),
                    acesso.getSenha());

            System.out.println("Conectando com o banco de dados... OK");
            return true;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar!");
            System.out.println("Driver nao encontrado " + e.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar!");
            System.out.println("Falha ao conectar " + e.toString());
        }

        return false;
    }

    // retorna o objeto que contem a 'conexao'
    public Connection getConexao() {
        return conexao;
    }

    // vetifica se a conexao é null
    // caso verdade, informa o erro
    // e finaliza o aplicativo
    private void verificaConecao() {
        System.out.println(this.getClass() + " - method: verificaConecao");
        if (!conectar()) {
            erro.setTitle("Erro");
            erro.setResizable(true);
            erro.setContentText("Erro ao conctar-se com  banco de dados\nVerifique sua conexao com a internet\n"
                    + "Se persistir contate o suporte");
            erro.showAndWait();
        }

    }

    // metodo construtor
    // sempre que instanciada a classe tenta fazer a conexao
    private BancoConexao() {
        if (conexao == null) {
            verificaConecao();
        }
    }

    // metodo que chama a instancia da classe
    // Padrao de projeto singleton
    // a classe só sera instanciada por esse metodo
    public static synchronized BancoConexao getInstance() {

        if (INSTANCIA == null) {
            
            INSTANCIA = new BancoConexao();
        }
        return INSTANCIA;
    }

}
