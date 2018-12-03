package mensagem;


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
		AgentController agentController2;
		AgentController rma;
		
		try {
			rma = contController.createNewAgent("rma", "jade.tools.rma.rma", null);
			

			//adicionando outro agente
			agentController = contController.acceptNewAgent("Agente_1", new Mensagens());
			agentController.start();
			
			agentController2 = contController.acceptNewAgent("Agente_2", new RecebimentoMensagem());
			agentController2.start();
			
			
			
			rma.start();
			
			
		} catch (StaleProxyException ex) {
			System.out.println("Erro ao criar o RMA");
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE,null, ex);
		}
		
		
		
		
		
	}
	
	

}
