package implementacao;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import visao.conexaoServidor.ControleConexaoServidor;

public class FuncPrincipal extends Application {

	public static Stage palco;

	public static String CPF;
	public static String PORTA;
	public static String IP;

	public static boolean ADM;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage myStage) throws Exception {

		CPF = null;

		palco = myStage;

		conexaoServidor();

	}

	public void conexaoServidor() {
		abrirTela("/visao/conexaoServidor/ConexaoServidor.fxml", "Conexao Servidor", "/imagens/funcionario.jpg");
	}

	// Funcionario
	public void inserirFuncionario() {
		abrirTela("/visao/inserirFuncionario/InserirFuncionario.fxml", "Inserir Funcionario",
				"/imagens/funcionario.jpg");
	}

	public void deletarFuncionario() {
		abrirTela("/visao/deletarFuncionario/DeletarFunc.fxml", "Deletar Funcionario", "/imagens/funcionario.jpg");
	}

	public void editarFuncionario() {
		abrirTela("/visao/editarFuncionario/EditarFunc.fxml", "Editar Funcionario", "/imagens/funcionario.jpg");
	}

	// Cliente
	public void inserirCliente() {
		abrirTela("/visao/inserirCliente/InserirCliente.fxml", "Inserir Cliente", "/imagens/funcionario.jpg");
	}

	public void deletarCliente() {
		abrirTela("/visao/deletarCliente/DeletarCliente.fxml", "Deletar Cliente", "/imagens/funcionario.jpg");
	}

	public void editarCliente() {
		abrirTela("/visao/editarCliente/EditarCliente.fxml", "Editar Cliente", "/imagens/funcionario.jpg");
	}

	// Produto
	public void inserirProduto() {
		abrirTela("/visao/inserirProduto/InserirProduto.fxml", "Inserir Produto", "/imagens/funcionario.jpg");
	}

	public void editarProduto() {
		abrirTela("/visao/editarProduto/EditarProduto.fxml", "Editar Produto", "/imagens/funcionario.jpg");
	}

	public void deletarProduto() {
		abrirTela("/visao/deletarProduto/DeletarProduto.fxml", "Deletar Produto", "/imagens/funcionario.jpg");
	}

	// Conta
	public void abrirConta() {
		abrirTela("/visao/abrirConta/AbrirConta.fxml", "Abrir Conta", "/imagens/funcionario.jpg");
	}

	public void fecharConta() {
		abrirTela("/visao/fecharConta/FecharConta.fxml", "Fechar Conta", "/imagens/funcionario.jpg");
	}

	// Relatorio
	public void relatorio() {
		abrirTela("/visao/relatorio/Relatorio.fxml", "Relatorio", "/imagens/funcionario.jpg");
	}

	public void inicio() {
		abrirTela("/visao/inicio/Inicio.fxml", "Inicio", "/imagens/funcionario.jpg");
	}

	private void abrirTela(String urlTela, String titulo, String urlImagem) {

		Parent painel = null;

		// carrega o arquivo fxml
		URL fxml = this.getClass().getResource(urlTela);

		// coloca a tela em um painel
		try {
			painel = (Parent) FXMLLoader.load(fxml);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// coloca a cena em um palco
		palco.setScene(new Scene(painel));

		// adiciona o icone
		palco.getIcons().add(new Image(urlImagem));

		// adiciona um titulo a tela
		palco.setTitle("Sistema de Pedidos Bar - " + titulo);
		// por fim, exibe a tela na maquina
		palco.show();

		palco.setOnCloseRequest(e -> {
			System.out.println("Funcionario finalizado!");
			try {
				if (ControleConexaoServidor.conectado)
					Remota.getInstance().desconectar();
			} catch (Exception j) {
				palco.close();
				Platform.exit();
				System.exit(0);
			}
			palco.close();
			Platform.exit();
			System.exit(0);
		});

	}

}
