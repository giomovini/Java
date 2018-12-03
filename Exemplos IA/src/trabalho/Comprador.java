	package trabalho;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

@SuppressWarnings("serial")
public class Comprador extends Agent {

	@Override
	protected void setup() {

		System.out.println("Iniciando agente " + getAID().getName());
		
		//evento unico
		addBehaviour(new OnShotB());
		//evento que se repete
		addBehaviour(new MessageReceiverBe());
	}

	private class OnShotB extends OneShotBehaviour {

		@Override
		public void action() {

			// criterios de escolha, ano e potencia
			Carro carro = new Carro(2010, 110, "", "", "", 0);

			System.out.println("**Criterios para a compra do carro: Ano[" + carro.getAno() + "] e Potencia(CV)["
					+ carro.getPotenciaCV() + "]");

			//criacao da mensagem
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

			try {
				//adiciona o objeto a mensagem
				msg.setContentObject(carro);
			} catch (IOException e) {
				System.out.println("Erro ao enviar o objeto no agente comprador.");
				e.printStackTrace();
			}

			//adiciona os vendedores a lista de receptores da mensagem
			msg.addReceiver(new AID("Vendedor1@" + Principal.IP + ":1099/JADE", true));
			msg.addReceiver(new AID("Vendedor2@" + Principal.IP + ":1099/JADE", true));
			//envia a mensagem
			send(msg);
			System.out.println(getAID().getName() + " Enviou mensagem");

		}

	}

	private class MessageReceiverBe extends CyclicBehaviour {

		Carro carro1, carro2;
		
		@Override
		public void action() {

			// desempilha uma mensagem se houver
			ACLMessage msg = receive();
			if (msg != null) {
				
				try {
					// recebe as mensagens dos vendedores e atribui nos objetos dos carros.
					if (carro1 == null) {
						carro1 = (Carro) msg.getContentObject();
					} else {
						carro2 = (Carro) msg.getContentObject();
					}
					
					/*Compara qual carro eh mais barato e escolhe para compra0
					 * */
					
					
					
					if (carro1 != null && carro2 != null) {
						System.out.print("**Escolha de compra do agente " + getAID().getLocalName()+" ");
						if (carro1.getPreco() <= carro2.getPreco()) {

							System.out.println("Nome[" + carro1.getNome() + "]," + " Ano[" + carro1.getAno()
									+ "], Cambio[" + carro1.getCambio() + "], Potencia[" + carro1.getPotenciaCV()
									+ "], Cor[" + carro1.getCor() + "] e Preco[" + carro1.getPreco() + "]");
						} else {
							System.out.println("Nome[" + carro2.getNome() + "]," + " Ano[" + carro2.getAno()
									+ "], Cambio[" + carro2.getCambio() + "], Potencia[" + carro2.getPotenciaCV()
									+ "], Cor[" + carro2.getCor() + "] e Preco[" + carro2.getPreco() + "]");
						}
					}

				} catch (UnreadableException e) {
					System.out.println("Erro ao receber a mensagem no agente comprador");
					e.printStackTrace();
				}

			} else {
				block();
			}

		}

	}

	//quando o agente é finalizado
	@Override
	protected void takeDown() {
		System.out.println(this.getClass() + " Finalizando...");
	}

}
