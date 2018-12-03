package comportamento;

import jade.core.Agent;

public class ExemploComportamento extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474565001928907832L;
	
	
	@Override
	protected void setup() {
		
		System.out.println("Criando agente .. "+getAID().getName());
		//adicionando comportamento
		addBehaviour(new MeuComportamento());
	}

}
