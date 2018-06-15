package visao.conexaoServidor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import implementacao.FuncPrincipal;
import implementacao.Remota;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleConexaoServidor {

	@FXML
	TextField txtIP, txtPorta, txtMeuIP, txtCPF;
	
	@FXML
	PasswordField pssSenha;

	@FXML
	Button btnConectar;
	
	@FXML
	Label lblStatusSistema;

	private String ip;

	public static boolean conectado, registrado;

	@FXML
	public void initialize() {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			System.out.println("Erro" + ex.getMessage());
		}
		
		txtMeuIP.setText(ip);
		txtMeuIP.setDisable(true);
		
		int porta = new Random().nextInt(4000)+1;

		// teste
		txtIP.setText(ip);
		txtPorta.setText("1");
		txtCPF.setText("01515396266");
		pssSenha.setText("henry");
		// teste

		btnConectar.setOnAction(e -> {
			FuncPrincipal.PORTA = txtPorta.getText();
			FuncPrincipal.IP = txtIP.getText();
			FuncPrincipal.CPF = txtCPF.getText();
			
			Remota.getInstance().conectarFuncionario(String.valueOf(porta), txtMeuIP.getText(), txtCPF.getText(), pssSenha.getText());
		});
		
		lblStatusSistema.setText("Não conectado");

	}

}
