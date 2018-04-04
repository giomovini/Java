/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagem;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tela.ControleServidor;

/**
 *
 * @author Uchiha
 */
public class ServidorImplementacao extends UnicastRemoteObject implements ServidorChatInterface {

    public ServidorImplementacao() throws RemoteException {
        super();
    }
    
    

    // lista de clientes conectados ao servidor
    public static ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    /*Método de encaminhamento de mensagens: deve receber os argumentos:
    apelido de origem, apelido de destino e mensagem. Caso o apelido de destino
    for “TODOS”, a mensagem deverá ser enviada a todos os clientes conectados
    ao servidor, caso contrário, a mensagem será enviada apenas ao cliente
    informado. Este método retornará 0 caso a mensagem seja enviada com
    sucesso, caso contrário, retornará 1.*/
    @Override
    public int receberMensagemCliente(String apelidoOrigem, String apelidoDestino, String mensagem) throws RemoteException {
        String ipDestino = "";
        String portaDestino = "";

        

        // passa pelo for sem nenhum criterio e envia para todos
        if (apelidoDestino.equals("TODOS")) {

            for (int i = 0; i < listaClientes.size(); i++) {

                ipDestino = listaClientes.get(i).getIpCliente();
                portaDestino = listaClientes.get(i).getPortaCliente();

                ClienteChatInterface cliente;
                try {
                    cliente = (ClienteChatInterface) Naming.lookup("rmi://" + ipDestino + ":" + portaDestino + "/olaMundo");
                    cliente.receberMensagemServidor(apelidoOrigem, mensagem);
                    
                    ControleServidor.log += "Mensagem de: [" + apelidoOrigem + "] para: [" + apelidoDestino + "] \n";
                    
                } catch (NotBoundException | MalformedURLException ex) {
                    System.out.println("Erro:"+ex.getMessage());
                    return 1;
                }
            }

        } else {
            // passa pelo for com comparacao de apelido e envia
            for (int i = 0; i < listaClientes.size(); i++) {

                if (apelidoDestino.equals(listaClientes.get(i).getApelido())) {
                    ipDestino = listaClientes.get(i).getIpCliente();
                    portaDestino = listaClientes.get(i).getPortaCliente();
                }
            }

            ClienteChatInterface cliente;
            try {
                cliente = (ClienteChatInterface) Naming.lookup("rmi://" + ipDestino + ":" + portaDestino + "/olaMundo");
                cliente.receberMensagemServidor(apelidoOrigem, mensagem);
                
                ControleServidor.log += "Mensagem de: [" + apelidoOrigem + "] para: [" + apelidoDestino + "] \n";
                
            } catch (NotBoundException | MalformedURLException ex) {
                System.out.println("Erro:"+ex.getMessage());
                return 1;
            }
        }

        return 0;
    }

    /* Método de conexão: Deve receber os argumentos: apelido do cliente, nome
     * do cliente, IP do cliente e porta do cliente. Este método retornará 0 caso o
     * conseguiu realizar a conexão, caso contrário, retornará 1.
     */
    @Override
    public int conectar(String apelido, String nome, String ipCliente, String portaCliente) throws RemoteException {

        
       for (int i = 0; i < listaClientes.size(); i++) {
            
           if(apelido.equals(listaClientes.get(i).getApelido())){
               
               System.out.println("Cliente "+apelido+" ja inserido");
               return 1;
           }
       }
        
        
        Cliente cli = new Cliente(apelido,nome,ipCliente,portaCliente);
        listaClientes.add(cli);

        ControleServidor.log += "Cliente conectado: [" + nome + "] \n";

        try {

            for (int i = 0; i < listaClientes.size(); i++) {

                ClienteChatInterface cliente = (ClienteChatInterface) Naming.lookup("rmi://" + listaClientes.get(i).getIpCliente() + ":" + listaClientes.get(i).getPortaCliente() + "/olaMundo");

                for (int j = 0; j < listaClientes.size(); j++) {
                    cliente.receberNovaConexao(listaClientes.get(j).getApelido(), listaClientes.get(j).getNome());
                }
            }

        } catch (NotBoundException | MalformedURLException ex) {
            ex.printStackTrace();
            return 1;
        }

        return 0;
    }

    /*deve receber como argumentos: apelido do cliente, IP do cliente e porta do cliente.*/
    @Override
    public void desconectar(String apelido, String ipCliente, String portaCliente) throws RemoteException {
        
        // indice na lista do elemento a ser removido
        int elementoRemover = -1;

        // encontra o indice do elemento a ser removido
        for (int i = 0; i < listaClientes.size(); i++) {

            if (listaClientes.get(i).getApelido().equals(apelido) && listaClientes.get(i).getIpCliente().equals(ipCliente)
                    && listaClientes.get(i).getPortaCliente().equals(portaCliente)) {

                elementoRemover = i;

            }
        }

        // for que passa por todos os clientes conectados 
        // e chama o metodo receberDesconexao para retirar o cliente que desconectou
        for (int i = 0; i < listaClientes.size(); i++) {
            ClienteChatInterface cliente;
            try {
                cliente = (ClienteChatInterface) Naming.lookup("rmi://" + listaClientes.get(i).getIpCliente() + ":" + listaClientes.get(i).getPortaCliente() + "/olaMundo");
                cliente.receberDesconexao(listaClientes.get(elementoRemover).getApelido(), listaClientes.get(elementoRemover).getNome());
            } catch (NotBoundException | MalformedURLException ex) {
                Logger.getLogger(ServidorImplementacao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        // atualiza o log 
        ControleServidor.log += "Cliente desconectado: [" + listaClientes.get(elementoRemover).getNome() + "] \n";
        // remove da lista do servidor
        listaClientes.remove(elementoRemover);
        
    }

}
