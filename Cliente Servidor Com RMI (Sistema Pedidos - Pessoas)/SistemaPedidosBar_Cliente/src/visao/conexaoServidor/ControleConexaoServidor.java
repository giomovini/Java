package visao.conexaoServidor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JOptionPane;

import implementacao.ClientePrincipal;
import implementacao.Remota;
import implementacao.Tverificacao;
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

	int porta;

	@FXML
	public void initialize() {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			System.out.println("Erro" + ex.getMessage());
		}

		txtMeuIP.setText(ip);
		txtMeuIP.setDisable(true);

		// teste
		txtIP.setText(ip);
		txtPorta.setText("1");
		txtCPF.setText("22558");
		pssSenha.setText("1231237");
		// teste

		btnConectar.setOnAction(e -> {

			if (txtCPF.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo CPF não pode ser vazio!");
				return;
			}
			if (txtPorta.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo porta não pode ser vazio!");
				return;
			}
			if (pssSenha.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Campo senha não pode ser vazio!");
				return;
			}

			porta = new Random().nextInt(4000) + 1;

			ClientePrincipal.PORTA = txtPorta.getText();
			ClientePrincipal.IP = txtIP.getText();
			ClientePrincipal.CPF = txtCPF.getText();

			boolean b = Remota.getInstance().conectarCliente(String.valueOf(porta), txtMeuIP.getText(),
					txtCPF.getText(), pssSenha.getText());

			if (b) {
				new ClientePrincipal().inicio();
				
				try {
					Tverificacao t = new Tverificacao();
					Thread newThrd = new Thread(t);

					newThrd.start();
				} catch (Exception j) {

				}
			}
		});

		lblStatusSistema.setText("Não conectado");

	}

}
