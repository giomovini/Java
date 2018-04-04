package mensagem;


import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius
 */
public interface ServidorChatInterface extends Remote {
    
    // Receber mensagem do cliente
    // Se apelidoDestino == "TODOS", a mensagem deverá ser enviada a todos os clientes conectados, caso contrário, somente ao cliente informado
    // Caso o retorno (int) seja igual a 0, significa que a mensagem foi enviada corretamente, caso contrário, o retorno será 1 reportando o erro
    public int receberMensagemCliente(String apelidoOrigem, String apelidoDestino, String mensagem) throws RemoteException;
    
    // Conexão de um novo cliente
    // Se o retorno (int) for igual a 0, significa que o cliente foi aceito, caso contrário, o retorno será 1 reportando o erro.
    public int conectar(String apelido, String nome, String ipCliente, String portaCliente) throws RemoteException;
    
    // Desconexão de um cliente
    public void desconectar(String apelido, String ipCliente, String portaCliente) throws RemoteException;
}