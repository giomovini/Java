/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import tela.ControleCliente;

/**
 *
 * @author Uchiha
 */
public class ClienteImplementacao extends UnicastRemoteObject implements ClienteChatInterface {

    public ClienteImplementacao() throws RemoteException {

        super();
    }

    //deve receber os argumentos: apelido de origem e a mensagem. 
    @Override
    public void receberMensagemServidor(String apelidoOrigem, String mensagem) throws RemoteException {

        String msg = "";
        try {
            msg = SimpleCrypto.decrypt(mensagem);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ControleCliente.textoMenssagensRecebidas += "[" + apelidoOrigem + "] - " + msg + "\n";
    }

    //deve receber os argumentos: apelido e nome do cliente que se conectou ao servidor
    @Override
    public void receberNovaConexao(String apelido, String nome) throws RemoteException {
        // envia para todos os clientes o novo cliente que foi conectado

        boolean existe = false;
        for (int i = 0; i < ControleCliente.ListaClientes.size(); i++) {

            if (ControleCliente.ListaClientes.get(i).getApelido().equals(apelido) && ControleCliente.ListaClientes.get(i).getNome().equals(nome)) {
                existe = true;
            }
        }

        if (!existe) {
            Cliente t = new Cliente(apelido, nome);
            ControleCliente.ListaClientes.add(t);
        }

    }

    //deve receber os argumentos: apelido e nome do cliente que se desconectou do servidor
    @Override
    public void receberDesconexao(String apelido, String nome) throws RemoteException {

        for (int i = 0; i < ControleCliente.ListaClientes.size(); i++) {

            if (ControleCliente.ListaClientes.get(i).getApelido().equals(apelido) && ControleCliente.ListaClientes.get(i).getNome().equals(nome)) {
                ControleCliente.ListaClientes.remove(i);
            }
        }

    }

}
