


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class ControleApp {
	
	private static String arquivo = "./meuTexto.txt";

	@FXML
	private TextArea txtTexto;

	@FXML
	private Button btnLer, btnGravar, btnSair;

	@FXML
	public void initialize() {
		txtTexto.setText("");
		btnGravar.setOnAction(e -> gravar(arquivo, txtTexto));
		btnLer.setOnAction(e -> ler(arquivo, txtTexto));
		btnSair.setOnAction(e -> Editor.stage.close());	
	}
	
	
	
	
	
	
public static boolean gravar(String arquivo, TextArea componente) {
		

		try {
			FileWriter gravador = new FileWriter(arquivo);
			
			for (String  s : componente.getText().split("\r")) {
				gravador.write(s +" \r\n");
			}
			gravador.close();
		} catch (IOException e) {
			System.out.println("Erro ao criar o arquivo " + arquivo);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void ler(String arquivo, TextArea componente){
		try {
			FileReader leitor = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(leitor);
			
			componente.setText("");
			
			while (br.ready()) {
				componente.appendText(br.readLine()+"\n");
			}
			br.close();
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado ");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Erro na leitura ");
			e.printStackTrace();
		}
		

	}
	
	
	
	

}
