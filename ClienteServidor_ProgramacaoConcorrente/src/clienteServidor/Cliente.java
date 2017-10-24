package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket cliente = new Socket("localhost", 1234);
		System.out.println("Cliente conecto-se com o servidor");
		
		
		DataInputStream entrada = new DataInputStream(cliente.getInputStream());

		DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
		
		
		System.out.println("\n\nCliente\n\n");
		System.out.println("\n enviando dados para o servidor\n");
		
		
		saida.writeUTF("\nOla como vai?");
		
		System.out.println("Resposta do servidor :"+entrada.readUTF());
		
		cliente.close();

	}

}
