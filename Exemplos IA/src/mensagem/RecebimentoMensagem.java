package mensagem;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RecebimentoMensagem extends Agent{
	

	@Override
	protected void setup() {
		System.out.println("Iniciando agente "+getAID().getName());
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage msg = receive();
				System.out.println(msg);
				
				if(msg != null) {
					System.out.println("\nAgente"+getLocalName()+" mensagem recebida de "
							+msg.getSender().getName()+": conteudo: "+msg.getContent());
				}else {
					block();
				}	
			}
		});
		
		
		
		
	
	}
	


}
