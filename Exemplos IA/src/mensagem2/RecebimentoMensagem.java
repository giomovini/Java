package mensagem2;


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class RecebimentoMensagem extends Agent {

	@Override
	protected void setup() {
		System.out.println("Iniciando agente " + getAID().getName());
		addBehaviour(new SpecificMessageReceiverBehaviour());
		addBehaviour(new AllMessageReceiverBehaviour());
		
		
	}

	private class SpecificMessageReceiverBehaviour extends CyclicBehaviour {

		@Override
		public void action() {
			MessageTemplate m1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			MessageTemplate m2 = MessageTemplate.MatchLanguage("myLanguage");
			MessageTemplate m1andm2 = MessageTemplate.and(m1, m2);

			ACLMessage msg = receive(m1andm2);

			if (msg != null) {
				System.out.println("\nAgente: " + getLocalName() + " mensagem especifica de " + msg.getSender() + ": "
						+ msg.getContent());
			} else {
				block();
			}
		}

	}

	private class AllMessageReceiverBehaviour extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg = receive();
			if (msg != null) {
				System.out.println("\nAgente " + getLocalName() + " mensagem qualquer recebida de "
						+ msg.getSender().getName() + ": " + msg.getContent());

			} else {
				block();
			}

		}

	}

}
