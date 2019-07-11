package teste;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Tela {
	private static Tela TelaInstancia;
	private Stage Palco,PalcoSobreposto;



	public Stage getPalcoSobreposto() {
		return PalcoSobreposto;
	}

	public void setPalcoSobreposto(Stage palcoSobreposto) {
		PalcoSobreposto = palcoSobreposto;
	}

	private Tela() {
	}

	public static synchronized Tela getInstance() {
		if (TelaInstancia == null)
			TelaInstancia = new Tela();

		return TelaInstancia;
	}

	public void AbrirTela(Arquivos Arq, Titulos Tit) {
		try {
			// carrega o arquivo fxml
			URL fxml = this.getClass().getResource(Arq.Caminho);

			Parent painel = (Parent) FXMLLoader.load(fxml);

			Palco.setScene(new Scene(painel));
			
			// adiciona o icone
			Palco.getIcons().add(new Image(Imagens.icone.Imagem));

			// adiciona um titulo a tela
			Palco.setTitle(Tit.Titulo);

			// habilita/desabilita a opcao redimensionar
			Palco.setResizable(false);

			// por fim, exibe a tela na maquina
			Palco.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AbrirTelaSobreposta(Arquivos Arq, Titulos Tit) {
		try {
			if(PalcoSobreposto == null) {
				PalcoSobreposto = new Stage();
			}
			// carrega o arquivo fxml
			URL fxml = this.getClass().getResource(Arq.Caminho);

			Parent painel = (Parent) FXMLLoader.load(fxml);

			PalcoSobreposto.setScene(new Scene(painel));
			
			// adiciona o icone
			PalcoSobreposto.getIcons().add(new Image(Imagens.icone.Imagem));

			// adiciona um titulo a tela
			PalcoSobreposto.setTitle(Tit.Titulo);

			// habilita/desabilita a opcao redimensionar
			PalcoSobreposto.setResizable(false);

			// por fim, exibe a tela na maquina
			PalcoSobreposto.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPalco() {
		return Palco;
	}

	public void setPalco(Stage palco) {
		Palco = palco;
	}

}
