package redes.soquetes.pacotes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/* Datagramas: conexâo via protocolo UDP (User Datagram Protocol)
 * 
 *  Em java usamos o protocolo UDP através das classes:
 *  
 *  -- DatagramPacket: armazena dados na forma de bytes, com capacidade máxima de 64 Kbytes. 
 *  	Mas na prática ultilizamos geralmente usados 512 bytes (mais segura) ou maxima de 8K.
 *  
 *  DatagramSocket: envia e recebe DatagramPacket.
 * 
 *  
 * 
 */



public class Cliente {
	
	//soquete para conexao cliente
	
	public static void main(String[] args) throws IOException{
		
		DatagramSocket cliente = new DatagramSocket();
		
		// endereco de destino dos dados do pacote
		
		InetAddress destino = InetAddress.getByName("localhost");
		
		
		
		//agora vamos enviar um pacote de dados!
		System.out.println("\n\n------- Cliente--------");
		System.out.println("\nEnviando mensagens para o servidor...");
		
		String mensagemEnviada = "Olá, como vai?";
		
		//converte a string em byte
		
		byte[] bufferEnvio = new byte[512];
		
		for (int i = 0; i < mensagemEnviada.length(); i++) {
			bufferEnvio[i] = (byte) mensagemEnviada.charAt(i);
		}
		
		//cria o pacote de envio. Posso enviar vários pacotes e cada pacote posso mandar para um destino diferente!
		
		DatagramPacket pacoteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,destino,5678);
		
		//finalmente envia o pacote 
		
		cliente.send(pacoteEnvio);
		
		
		
		// recebendo dados
		byte[] buffer = new byte[512];
		
		DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
		
		cliente.receive(pacoteRecebido);
		
		String mensagemRecebida = new String(pacoteRecebido.getData()).trim();
		
		
		System.out.println("\n\n ---------------Cliente--------------");
		System.out.println("\nMensagem recebida: "+mensagemRecebida);
		
		
		
	}
	
	
	

}
