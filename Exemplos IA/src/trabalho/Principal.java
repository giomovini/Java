package trabalho;


import java.net.InetAddress;
import java.util.logging.Level;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.Logger;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Principal {
	
	public static String IP;
	
	
	public static void main(String[] args) {
		try {
			//atribui o IP da rede
           	IP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		
		
		Runtime runtime = Runtime.instance();
		Profile profile = new ProfileImpl();
		ContainerController contController = runtime.createMainContainer(profile);
		
		AgentController agentController;
		AgentController agentController2;
		AgentController agentController3;
		AgentController rma;
		
		try {
			rma = contController.createNewAgent("rma", "jade.tools.rma.rma", null);
			

			//adicionando o agente comprador
			agentController = contController.acceptNewAgent("Comprador", new Comprador());
			agentController.start();
			
			//adiciona o agente vendedor 1
			agentController2 = contController.acceptNewAgent("Vendedor1", new Vendedor());
			agentController2.start();
			
			//adiciona o agente vendedor 2
			agentController3 = contController.acceptNewAgent("Vendedor2", new Vendedor());
			agentController3.start();
			
			
			
			rma.start();
			
			
		} catch (StaleProxyException ex) {
			System.out.println("Erro ao criar o RMA");
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
		}
		
		
		
		
		
	}
	
	

}
