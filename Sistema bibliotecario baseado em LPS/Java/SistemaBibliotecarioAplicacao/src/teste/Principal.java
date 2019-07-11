package teste;


import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage pl) throws Exception {
		Tela TelaInicial = Tela.getInstance();
		TelaInicial.setPalco(pl);
		TelaInicial.AbrirTela(Arquivos.inicial, Titulos.inicial);

	}

}
