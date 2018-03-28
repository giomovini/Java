package rede;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TesteInetAddress {

	
	public static void main(String[] args) throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("Endereço Local"+ localHost);
		System.out.println(localHost.isSiteLocalAddress());
		
		
		InetAddress end1 = InetAddress.getByName("www.google.com.br");
		System.out.println("Servidor google"+end1);
		System.out.println(localHost.isSiteLocalAddress());

		InetAddress end2 = InetAddress.getByName("200.175.196.160");
		System.out.println("Endereço google por ip:"+end2);
		
		byte[] ip ={(byte)200,(byte) 175,(byte)196,(byte)88};
		
		InetAddress end3 = InetAddress.getByAddress(ip);
		System.out.println("Endereço google por ip vetor de bytes"+end3);
			
			
			
		}

		
		
	
}
