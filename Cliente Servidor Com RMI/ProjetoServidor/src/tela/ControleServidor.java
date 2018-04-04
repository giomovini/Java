package tela;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import mensagem.Cliente;
import mensagem.ServidorChatInterface;
import mensagem.ServidorImplementacao;
import sun.rmi.registry.RegistryImpl;

public class ControleServidor {

    // Objetos da tela
    @FXML
    Label lblStatus;

    @FXML
    AnchorPane ancor;

    @FXML
    TextField txtPorta;

    @FXML
    TextArea txtLog;

    @FXML
    Button btnRegistrar;

    @FXML
    TableView<Cliente> tblClientes;

    @FXML
    TableColumn<Cliente, String> colApelido, colNome, colIP, colPorta;

    //String para atualizar o txtLog
    public static String log = "";
    // flag para indicar se o servidor esta conectado ou nao
    private boolean registrado = false;

    // ip do servidor
    private String ip = "";

    //lista de clientes conectados ao servidor
    public static ObservableList<Cliente> ListaClientes;

    @FXML
    public void initialize() {
        
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            //ip = "localhost";
        } catch (UnknownHostException ex) {
            System.out.println("Erro"+ex.getMessage());
        }
        
        txtLog.setEditable(false);
        
        colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        colIP.setCellValueFactory(new PropertyValueFactory<>("ipCliente"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPorta.setCellValueFactory(new PropertyValueFactory<>("portaCliente"));

        // acao do botao
        btnRegistrar.setOnAction(e -> registrarServidor());

        // toda vez que o mouse mover pela tela atualiza alguns componentes da tela 
        ancor.setOnMouseEntered(e -> atualizacaoComponentes());
        ancor.setOnMouseMoved(e -> atualizacaoComponentes());

    }

    public void atualizacaoComponentes() {
        
        // atualiza a lista de clientes conectados
        tblClientes.setItems(ServidorImplementacao.listaClientes);
        // atualiza o txt de acoes no servidor
        txtLog.setText(log);
    }

    private void registrarServidor() {
        
        if(txtPorta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite um valor inteiro para a porta");
            return;
        }
        

        try {

            //passo 1 -- registrar servidor na porta
            RegistryImpl registryImpl = new RegistryImpl(Integer.parseInt(txtPorta.getText()));

            //passo 2 -- Instanciando a classe ServidorImplementacao que sao do tipo ServidorChatInterface.
            ServidorChatInterface servidor = new ServidorImplementacao();
            
            

            //passo 3 -- Possibilitando que a instancia contendo metodos remotos fique visivel aos clientes. 
            //Alem do IP e PORTA, deve-se associar um nome a instÃ¢ncia.
            //Parametros: String (IP, Porta e nome do servidor) e Objeto com metodos remotos.
            Naming.rebind("rmi://" + ip + ":" + Integer.parseInt(txtPorta.getText()) + "/olaMundo", servidor);

            registrado = true;
            
            
        } catch (NumberFormatException | MalformedURLException | RemoteException e) {
            registrado = false;
            System.out.println("erro: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Porta já registrada ou valor inserido incorretamente!\n(insira apenas numeros inteiros)");
        }

        System.out.println("Servidor Registrado " + registrado);

        // atualiza o status do servidor por labels e textArea
        if (registrado) {
            lblStatus.setText("Registrado!");
            log = "Servidor registrado com sucesso no ip "+ip+"\n";
            btnRegistrar.setDisable(registrado);
        } else {
            lblStatus.setText("Nao Registrado!");
            log = "Falha ao registrar o servidor!\n";
        }
        
        atualizacaoComponentes();

    }

}
