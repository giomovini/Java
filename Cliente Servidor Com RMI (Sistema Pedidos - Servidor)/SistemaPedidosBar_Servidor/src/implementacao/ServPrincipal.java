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

public class ServPrincipal extends Application {
	
	public static Stage palco;
	

	public static void main(String[] args) {
		launch(args);	
	}

	
	@Override
	public void start(Stage myStage) throws Exception {
		palco = myStage;
		telaServidor();	
	}

	public void telaServidor() {
		abrirTela("/visao/servidor/Servidor.fxml", "Servidor", "/imagens/servidor.jpg");
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
            System.out.println("Servidor finalizado!");
            palco.close();
            Platform.exit();
            System.exit(0);
        });

	}

}
