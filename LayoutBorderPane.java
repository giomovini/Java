

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class LayoutBorderPane extends Application{

	public static void main(String[] args) {
		launch(args); 

	}

	@Override
	public void start(Stage palco) throws Exception {
		BorderPane painelBorda = new BorderPane();
		Label lblTopo, lblEsquerda,lblDireita,lblAbaixo,lblCentro;
		
		painelBorda.setTop(lblTopo = new Label("Topo"));
		painelBorda.setLeft(lblEsquerda = new Label("Esquerda"));
		painelBorda.setBottom(lblAbaixo = new Label("Abaixo"));
		painelBorda.setRight(lblDireita = new Label("Direita"));
		painelBorda.setCenter(lblCentro = new Label("Centro"));
		
		BorderPane.setAlignment(lblTopo, Pos.CENTER);
		BorderPane.setAlignment(lblEsquerda, Pos.CENTER);
		BorderPane.setAlignment(lblAbaixo, Pos.CENTER);
		BorderPane.setAlignment(lblDireita, Pos.CENTER);
		BorderPane.setAlignment(lblCentro, Pos.CENTER);
		
		
		
		
		Scene cena = new Scene(painelBorda,400,300);
		palco.setScene(cena);
		palco.setTitle("BorderPane");
		palco.show();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
