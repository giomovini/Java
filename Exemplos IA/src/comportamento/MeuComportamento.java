package comportamento;

import jade.core.behaviours.Behaviour;

public class MeuComportamento extends Behaviour{

	private static final long serialVersionUID = -8850106216766434367L;
	private int acao = 0;
	
	
	@Override
	public void action() {
		switch (acao) {
		case 1:
			System.out.println("Acao "+acao);
			break;
		case 2:
			System.out.println("Acao "+acao);
			break;
		case 3:
			System.out.println("Acao "+acao);
			break;
			
		default:
			System.out.println("Sem acao definida");
			break;
		}
		this.acao++;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	


}
