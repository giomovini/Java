package tela;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

import mensagem.ClienteChatInterface;
import mensagem.ClienteImplementacao;
import mensagem.Cliente;
import mensagem.ServidorChatInterface;
import mensagem.SimpleCrypto;
import sun.rmi.registry.RegistryImpl;

public class ControleCliente {

    // Objetos da tela
    @FXML
    AnchorPane ancor;

    @FXML
    TextField txtPortaCliente, txtIPServidor, txtPortaServidor, txtNome, txtApelido, txtEnderecoIP;

    @FXML
    Button btnConectar, btnEnviar, btnDesconectar;

    @FXML
    Label lblStatus;

    @FXML
    TextArea txtMensagem;

    @FXML
    TextArea txtMensagemRecebida;

    @FXML
    TableView<Cliente> tblClientes;

    @FXML
    TableColumn<Cliente, String> colApelido, colNome;

    // texto a ser exibido na tela, log do cliente
    public static String textoMenssagensRecebidas = "";

    // lista de clientes conectados no mesmo servidor
    public static ObservableList<Cliente> ListaClientes = FXCollections.observableArrayList();

    //indica se o cliente esta conectado ou nao
    private boolean conectado = false;

    @FXML
    public void initialize() {

        try {
            txtEnderecoIP.setText(InetAddress.getLocalHost().getHostAddress());
            txtEnderecoIP.setDisable(true);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        // atualiza alguns componentes da tela 
        atualizacaoComponentes();

        txtMensagemRecebida.setEditable(false);

        colApelido.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // acoes dos botoes
        btnConectar.setOnAction(e -> conectar());
        btnEnviar.setOnAction(e -> enviarMensagem());
        btnDesconectar.setOnAction(e -> desconectar());

        // toda vez que o mouse mover pela tela atualiza alguns componentes da tela 
        ancor.setOnMouseMoved(e -> atualizacaoComponentes());

    }

    // os campos devem ser preenchidos
    private boolean camposVazios() {

        if (txtApelido.getText().equals("")) {
            return false;
        }
        if (txtEnderecoIP.getText().equals("")) {
            return false;
        }
        if (txtIPServidor.getText().equals("")) {
            return false;
        }
        if (txtNome.getText().equals("")) {
            return false;
        }
        if (txtPortaCliente.getText().equals("")) {
            return false;
        }
        if (txtPortaServidor.getText().equals("")) {
            return false;
        }

        return true;
    }

    private void atualizacaoComponentes() {

        // atualiza as mensagens recebidas
        txtMensagemRecebida.setText(textoMenssagensRecebidas);
        // atualiza a tabela com a lista de clientes
        tblClientes.setItems(ListaClientes);
        // se estiver conectado habilita o botao de desconectar
        btnDesconectar.setVisible(conectado);

        // quando conectado desabilita as opcoes para conexao 
        txtApelido.setDisable(conectado);
        txtIPServidor.setDisable(conectado);
        txtNome.setDisable(conectado);
        txtPortaCliente.setDisable(conectado);
        txtPortaServidor.setDisable(conectado);
        btnConectar.setVisible(!conectado);

    }

    // cria uma opcao "Todos" como cliente e insere na lista de clientes
    private void opcaoTodos() {
        Cliente c = new Cliente("TODOS", "TODOS");
        ListaClientes.add(c);
    }

    //desconecta o cliente do servidor
    private void desconectar() {

        ServidorChatInterface servidor;
        try {
            servidor = (ServidorChatInterface) Naming.lookup("rmi://" + txtIPServidor.getText() + ":" + Integer.parseInt(txtPortaServidor.getText()) + "/olaMundo");
            servidor.desconectar(txtApelido.getText(), txtEnderecoIP.getText(), txtPortaCliente.getText());

        } catch (NumberFormatException | MalformedURLException | NotBoundException | RemoteException ex) {
            System.out.println(ex.getMessage());
        }
        conectado = false;
        lblStatus.setText("Desconectado");

        // limpa a lista
        ListaClientes = FXCollections.observableArrayList();

        // quando desconectado, fecha a tela e encerra o processo
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        Platform.exit();
        System.exit(0);

    }

    private void enviarMensagem() {
        
        if(!conectado){
            JOptionPane.showMessageDialog(null, "Conecte-se ao servidor!");
            return;
        }
        if(tblClientes.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Selecione o destinatário da mensagem na tabela!");
            return;
        }

        textoMenssagensRecebidas += "[voce] -> [" + tblClientes.getSelectionModel().getSelectedItem().getApelido() + "] - " + txtMensagem.getText() + "\n";

        ServidorChatInterface servidor;
        try {
            // Passo 7
            // Recuperando o objeto remoto do servidor.
            // Para obter e necessario indicar o IP, Porta e Nome do servico 
            //(nome associado a instancia do objeto remoto)
            servidor = (ServidorChatInterface) Naming.lookup("rmi://" + txtIPServidor.getText() + ":" + Integer.parseInt(txtPortaServidor.getText()) + "/olaMundo");

            // Passo 8: Utilizacao dos metodos remotos, bastante semelhante 
            //a utilizacao de metodos locais.
            // ha o envio, alem da mensagem, o IP e Porta do cliente 
            //para que o servidor possa retornar a mensagem para o Cliente correto.
            String apelidoDestino = tblClientes.getSelectionModel().getSelectedItem().getApelido();

            //faz a encriptacao da mensagem
            String msg = SimpleCrypto.encrypt(txtMensagem.getText());

            int i = servidor.receberMensagemCliente(txtApelido.getText(), apelidoDestino, msg);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar a mensagem!");
            System.out.println(ex.getMessage());
        }

    }

    private void conectar() {

        // se tiver campo vazio, não faz o metodo de conexao
        if (!camposVazios()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }

        try {

            // Passo 4 -- Registrando o servico em uma determinada porta
            RegistryImpl registryImpl = new RegistryImpl(Integer.parseInt(txtPortaCliente.getText()));

            // Passo 5 -- Instanciando a classe ClienteImpl que sao do tipo ClienteChatInterface
            ClienteChatInterface cliente = new ClienteImplementacao();

            // Passo 6
            // Possibilitando que a instancia contendo metodos remotos fique visivel ao Servidor. 
            // Alem do IP e PORTA, deve-se associar um nome a instancia.
            // Parametros: String (IP, Porta e nome do servico) e Objeto com metodos remotos.
            Naming.rebind("rmi://" + txtEnderecoIP.getText() + ":" + Integer.parseInt(txtPortaCliente.getText()) + "/olaMundo", cliente);
            conectado = true;

        } catch (NumberFormatException | MalformedURLException | RemoteException ex) {
            conectado = false;
            System.out.println(ex.getMessage());
        }

        ServidorChatInterface servidor;

        try {
            servidor = (ServidorChatInterface) Naming.lookup("rmi://" + txtIPServidor.getText() + ":" + Integer.parseInt(txtPortaServidor.getText()) + "/olaMundo");
            int i = servidor.conectar(txtApelido.getText(), txtNome.getText(), txtEnderecoIP.getText(), txtPortaCliente.getText());

            conectado = (i == 0);

        } catch (NumberFormatException | MalformedURLException | NotBoundException | RemoteException ex) {
            conectado = false;
            System.out.println(ex.getMessage());
        }
        
        if(!conectado){
           JOptionPane.showMessageDialog(null, "Erros possiveis:\n"+"Porta ja registrada\n"+"Apelido já registrado no servidor\nIP ou Porta do servidor incorretos\nServidor não conectado"); 
        }

        System.out.println("Cliente Conectado " + conectado);

        lblStatus.setText(conectado ? "Conectado!" : "Desconectado!");

        if (conectado) {
            opcaoTodos();
            Principal.palco.setTitle("Cliente - " + txtNome.getText() + "(" + txtApelido.getText() + ")");
        }

    }

}
