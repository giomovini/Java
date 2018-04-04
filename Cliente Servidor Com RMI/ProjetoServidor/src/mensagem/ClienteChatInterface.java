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
public interface ClienteChatInterface extends Remote {
    
    // Receber mensagem do Servidor
    public void receberMensagemServidor(String apelidoOrigem, String mensagem) throws RemoteException;
    
    // Receber apelido e o nome de um cliente que acabou de se conectar no Servidor
    public void receberNovaConexao(String apelido, String nome) throws RemoteException;
    
    // Receber apelido e o nome de um cliente que acabou de se desconectar do Servidor
    public void receberDesconexao(String apelido, String nome) throws RemoteException;
}