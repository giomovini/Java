package agentes;

import jade.core.Agent;

public class AgenteSimples extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7248872158113331120L;

	@Override
	protected void setup() {
		System.out.println("O agente " + getAID().getName() + " está pronto");

		Object args[] = getArguments();
		if (args != null) {
			System.out.println("Argumentos:");
			for (int i = 0; i < args.length; i++) {
				System.out.println("Argumento: " + args[i] + " " + args[i].getClass().getName());
			}
		}

	}
	
	@Override
	protected void takeDown() {
		System.out.println("Agente "+getAID().getName()+" finalizado");
	}
	
	

}
