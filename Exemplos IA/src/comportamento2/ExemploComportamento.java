package comportamento2;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class ExemploComportamento extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474565001928907832L;
	
	
	@SuppressWarnings("serial")
	@Override
	protected void setup() {
		
		addBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				System.out.println("["+getAID().getName()+"] - Executei uma unica vez");
				//adicionando comportamento
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
