package javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExibeAudio extends Application{
	
	public static void main(String[] args) {
		
		System.out.println("\nCarregando audio...");
		
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		//carrega o clip de àudio
		
		AudioClip audio = new AudioClip("http://www.softgraf.com/cursojava/multimidia/musica.wav");
		
		StackPane painel = new StackPane();
		
		painel.getChildren().add(new Text("Tocando musica"));
		
		Scene cena = new Scene(painel, 300,100);

		stage.setTitle("Tocando Audio com JavaFx");
		
		stage.setScene(cena);
		stage.show();
		
		
		audio.play();
		
		
		
		
	}
	
	
	

}
