package visao.servidor;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import TipoDado.PessoaTabela;
import banco.conexao.BancoConexao;
import banco.conexao.BancoInformacoes;
import implementacao.ServidorImplementacao;
import implementacao.ServidorInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sun.rmi.registry.RegistryImpl;

public class ControleServidor {

	@FXML
	Pane Fundo;

	@FXML
	TextField txtPorta;

	@FXML
	Button btnConectar;

	@FXML
	Label lblIP, lblStatusSistema;

	@FXML
	TextArea txtLog;

	@FXML
	TableView<PessoaTabela> TFuncionario, TCliente;

	@FXML
	TableColumn<PessoaTabela, String> colCPF_func, colNome_func, colPorta_func, colIP_func;

	@FXML
	TableColumn<PessoaTabela, String> colCPF_cliente, colNome_cliente, colPorta_cliente, colIP_cliente;

	public static String ip,porta;
	boolean registradoServ, registradoBanco;
	public static String log = "";
	RegistryImpl r;

	@FXML
	public void initialize() {
		
		log += "[ATOR]\t-\t[AÇÃO]\t-\t[DATA]\t-\t[HORA]\n\n";

		if (BancoConexao.getInstance().isConected()) {
			log += "Sistema - Conexão com banco de dados IP " + new BancoInformacoes().getIP() +" - "+ ServidorImplementacao.dataHoraAtual() + "\n";
		}

		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			System.out.println("Erro" + ex.getMessage());
		}

		lblIP.setText("IP : " + ip);

		txtLog.setEditable(false);

		txtPorta.setText("1");

		colCPF_func.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colNome_func.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colPorta_func.setCellValueFactory(new PropertyValueFactory<>("porta"));
		colIP_func.setCellValueFactory(new PropertyValueFactory<>("ip"));

		colCPF_cliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colNome_cliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colPorta_cliente.setCellValueFactory(new PropertyValueFactory<>("porta"));
		colIP_cliente.setCellValueFactory(new PropertyValueFactory<>("ip"));

		// acao do botao
		btnConectar.setOnAction(e -> registrarServidor());

		Fundo.setOnMouseMoved(e -> atualizacaoComponentes());

	}

	private void atualizacaoComponentes() {

		lblStatusSistema.setText("Servidor registrado (" + registradoServ + ") Conexão com banco ("
				+ BancoConexao.getInstance().isConected() + ")");

		txtPorta.setDisable(registradoServ);

		txtLog.setText(log);

		TFuncionario.setItems(ServidorImplementacao.listaFuncionario);
		TCliente.setItems(ServidorImplementacao.listaClientes);

	}

	
	private void registrarServidor() {

		if (txtPorta.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite um valor inteiro para a porta");
			return;
		}

		try {

			r = new RegistryImpl(Integer.parseInt(txtPorta.getText()));

			ServidorInterface servidor = new ServidorImplementacao();

			Naming.rebind("rmi://" + ip + ":" + Integer.parseInt(txtPorta.getText()) + "/olaMundo", servidor);

			registradoServ = true;
			btnConectar.setDisable(true);

		} catch (NumberFormatException | MalformedURLException | RemoteException e) {
			registradoServ = false;
			System.out.println("erro: " + e.getMessage());
			JOptionPane.showMessageDialog(null,
					"Porta já registrada ou valor inserido incorretamente!\n(insira apenas numeros inteiros)");
		}

		System.out.println("Servidor registrado " + registradoServ);

		if (registradoServ){
			log += "Sistema - Servidor registrado no ip " + ip + " porta " + txtPorta.getText() + " - " + ServidorImplementacao.dataHoraAtual() + "\n";
			
		
		}else
			log += "Sistema - Servidor não registrado" + " - " + ServidorImplementacao.dataHoraAtual() + "\n";
		
		porta = txtPorta.getText();

	}


}
