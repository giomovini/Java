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

public class ClientePrincipal extends Application {

	public static Stage palco;
	public static String CPF;
	public static String PORTA;
	public static String IP;
	public static int ID_PEDIDO;

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
		abrirTela("/visao/conexaoServidor/ConexaoServidor.fxml", "Conexao Servidor", "/imagens/pessoa.jpg");
	}

	public void inicio() {
		abrirTela("/visao/inicio/Inicio.fxml", "Inicio", "/imagens/pessoa.jpg");
	}

	public void cardapio() {
		abrirTela("/visao/cardapio/Cardapio.fxml", "Cardapio", "/imagens/pessoa.jpg");
	}

	public void pedido() {
		abrirTela("/visao/pedido/Pedido.fxml", "Pedido", "/imagens/pessoa.jpg");
	}

	public void visualizarPedido() {
		abrirTela("/visao/visualizarConta/VisualizarConta.fxml", "Visualizar Conta", "/imagens/pessoa.jpg");
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
			System.out.println("Cliente finalizado!");
			fecharPrograma();
		});

	}

	public void fecharPrograma() {
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

	}

}
