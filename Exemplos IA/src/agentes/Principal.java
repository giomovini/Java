package agentes;


import java.util.logging.Level;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.Logger;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Principal {
	
	public static void main(String[] args) {
		
		Runtime runtime = Runtime.instance();
		Profile profile = new ProfileImpl();
		ContainerController contController = runtime.createMainContainer(profile);
		
		AgentController agentController;
		AgentController rma;
		
		try {
			rma = contController.createNewAgent("rma", "jade.tools.rma.rma", null);
			
			String[] argumentos = {"a1","a2"};
			
			//criando um agente
			agentController = contController.createNewAgent("Agente_0", "agentes.AgenteSimples", argumentos);
			agentController.start();
			
			//adicionando outro agente
			agentController = contController.acceptNewAgent("Agente_1", new AgenteSimples());
			agentController.start();
			
			rma.start();
			
			
		} catch (StaleProxyException ex) {
			System.out.println("Erro ao criar o RMA");
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
		}
		
		
		
		
		
	}
	
	

}
