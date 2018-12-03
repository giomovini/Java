package mensagem;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Mensagens extends Agent {

	@Override
	protected void setup() {

		addBehaviour(new OneShotBehaviour() {


			@Override
			public void action() {
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setLanguage("Portugues");
				msg.setContent("conteudo");
				msg.addReceiver(new AID("Agente_2@10.10.10.65:1099/JADE", true));
				send(msg);
				System.out.println("Mensagem enviada");
				doDelete();
			}

		});
	}

	@Override
	protected void takeDown() {
		System.out.println("Finalizando...");
	}

}
