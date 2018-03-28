

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Editor extends Application{
	
	
	protected static  Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage palco) throws Exception {
		stage = palco;
		URL fxml = this.getClass().getResource("Editor.fxml");
		Parent painel  = (Parent)FXMLLoader.load(fxml);
		palco.setScene(new Scene(painel));
		palco.setTitle("Editor");
		palco.show();
	}

}
