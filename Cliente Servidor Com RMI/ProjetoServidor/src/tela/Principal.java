package tela;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage palco) throws Exception {
        Parent painel = null;

        // carrega o arquivo fxml
        URL fxml = this.getClass().getResource("/tela/TelaServidor.fxml");

        // coloca a tela em um painel
        try {
            painel = (Parent) FXMLLoader.load(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // coloca a cena em um palco
        palco.setScene(new Scene(painel, 690, 440));

        palco.setResizable(false);

        palco.getIcons().add(new Image("/tela/icone-servidor.png"));
        // adiciona um titulo a tela
        palco.setTitle("Servidor");
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
