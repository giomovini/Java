package trabalho;

import java.io.IOException;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

@SuppressWarnings("serial")
public class Vendedor extends Agent {
	

	@Override
	protected void setup() {
		System.out.println("Iniciando agente " + getAID().getName());
		//evento que se repete
		addBehaviour(new MessageReceiverBehaviour());
	}

	private class MessageReceiverBehaviour extends CyclicBehaviour {

		
		private Carro buscarMelhor(Carro criterio) {
			ArrayList<Carro> lista;

			// cada vendedor recebe uma lista diferente
			lista = CarrosVendedores.getInstance().carrosVendedor(getAID().getLocalName());
		
			Carro escolhido = null;
			
			// busca o carro conforme os criterios e pega o de menor valor
			for (Carro carro2 : lista) {
				if (carro2.getAno() >= criterio.getAno() && carro2.getPotenciaCV() >= criterio.getPotenciaCV()) {					
					if (escolhido != null && carro2.getPreco() < escolhido.getPreco()) {
						escolhido = carro2;
					} else if(escolhido == null) {
						escolhido = carro2;
					}
				}

			}
			return escolhido;

		}

		@Override
		public void action() {

			// desempilha uma mensagem se houver
			ACLMessage msg = receive();
			if (msg != null) {
				try {
					// atribui o objeto carro que esta contido na mensagem (carro que eh criterio)
					Carro carroCriterio = (Carro) msg.getContentObject();
					Carro escolhido = buscarMelhor(carroCriterio);
					
					
					System.out.println("**Proposta do agente["+getAID().getLocalName()+"]\t Nome["+escolhido.getNome()+"],"
							+ " Ano["+escolhido.getAno()+"], Cambio["+escolhido.getCambio()+"], Potencia["+escolhido.getPotenciaCV()
							+"], Cor["+escolhido.getCor()+"] e Preco["+escolhido.getPreco()+"]");
					
					
					/*
					 * monta a mensagem, adiciona o objeto carro escolhido e envia
					 */
					ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);

					msg1.setContentObject(escolhido);
					
					msg1.addReceiver(new AID("Comprador@" + Principal.IP + ":1099/JADE", true));
					send(msg1);
					System.out.println(getAID().getName()+" Enviou mensagem");
					
				} catch (UnreadableException e) {
					System.out.println("Erro ao receber a mensagem no agente vendedor.");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Erro ao enviar o objeto no agente vendedor.");
					e.printStackTrace();
				}

			} else {
				block();
			}
		}

	}

}
