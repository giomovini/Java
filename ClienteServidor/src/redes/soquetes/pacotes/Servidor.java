package redes.soquetes.pacotes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.xml.crypto.Data;

public class Servidor {
	
	//Selecione o console para ver as mensagens entre cliente/servidor ou execute o servidor no outro computador
	
	
	public static void main(String[] args) throws IOException {
		
		String mensagem;
		
		
		// porta qualquer
		DatagramSocket servidor = new DatagramSocket(5678);
		
		
		//buffer para recebimento 
		
		byte[] buffer = new byte[512];
		
		// cria pacote para recebimento
		DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
		
				
		// aguarda para receber um pacote na porta 5678
		servidor.receive(pacoteRecebido);
		
		
		//processa o pacote recebido(elimina os espaços (bytes) vazios)
		
		String mensagemRecebida = new String(pacoteRecebido.getData()).trim();
		
		
		System.out.println("\n\n ---------------Servidor--------------");
		System.out.println("\nMensagem recebida: "+mensagemRecebida);
		
		
		
		//prepara o pacote para envio(resposta)
		
		System.out.println("Respondendo cliente....");
		
		if(mensagemRecebida.equalsIgnoreCase( "Olá, como vai?")){
			mensagem = "Tudo bem e voce?";
			
		}else{
			
			mensagem = "OK";
		}
		
		
		byte[] bufferResposta = new byte[512];
		
		for (int i = 0; i < mensagem.length(); i++) {
			bufferResposta[i] = (byte) mensagem.charAt(i);
		}
		
		DatagramPacket pacoteEnvio = new DatagramPacket(bufferResposta, bufferResposta.length, pacoteRecebido.getAddress(),pacoteRecebido.getPort());
		
		//finalmente envia o pacote de resposta
		
		servidor.send(pacoteEnvio);
		System.out.println("Mensagem enviada");
		
	}

}
