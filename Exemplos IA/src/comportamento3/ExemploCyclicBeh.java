package comportamento3;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;


public class ExemploCyclicBeh extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4474565001928907832L;
	
	
	@SuppressWarnings("serial")
	@Override
	protected void setup() {
		System.out.println("inicializando agente "+getAID().getName());
		
		
		addBehaviour(new TickerBehaviour(this,1000) {
			
			int contador = 0;
			
			@Override
			protected void onTick() {
				System.out.println("["+getAID().getName()+"] - contador "+contador++);
				if(contador == 20) {
					removeBehaviour(this);
					takeDown();
				}
			}
		});
		
		
		/*addBehaviour(new CyclicBehaviour() {
			
			
			private int contador = 0;
			
			@Override
			public void action() {
				System.out.println("["+getAID().getName()+"] - contador "+contador++);
				if(contador == 20) {
					removeBehaviour(this);
					takeDown();
				}
			}
		});*/
	}
	
	@Override
	protected void takeDown() {
		System.out.println("Agente "+getAID().getName()+" finalizado");
	
	}

}
