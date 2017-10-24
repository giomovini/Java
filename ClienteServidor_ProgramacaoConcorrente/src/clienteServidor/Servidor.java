package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {

		ServerSocket servidor = new ServerSocket(1234);

		System.out.println("Servidor aguardando cliente");

		//while(true){
			
		Socket cliente = servidor.accept();
		
		DataInputStream entrada= new DataInputStream(cliente.getInputStream());

		
		DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
		
		String mensagemRecebida = entrada.readUTF();
		
		System.out.println("\n\n SERVIDOR \n\n");
		System.out.println("Mensagem recebida do servidor"+mensagemRecebida);
		
		System.out.println("respondendo cliente");
		
		if(mensagemRecebida.equals("Ola como vai?")){
			saida.writeUTF("Tudo bem! e voce?");
		}else{
			saida.writeUTF("Ok");
		}
		
		
		
		
		System.out.println("IP cliente"+cliente.getInetAddress());

		
		
		
		//}
		cliente.close();
		servidor.close();
		
		
		
		System.out.println("Servidor encerrado");

	}
}
