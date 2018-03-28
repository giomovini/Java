package com.softgraf.sincronizacao.sinaleiro;

//Atençao: não confundir com semáforo(Semaphore)
public class Sinaleiro implements Runnable {

	private CorSinal cor = CorSinal.VERMELHO;
	private boolean ligado;
	// true quando a cor do sinal mudar
	private boolean mudou;

	public Sinaleiro() {
		ligado = true;
		mudou = false;
		new Thread(this).start();
		System.out.println("Sinaleiro ligado!");
	}

	@Override
	public void run() {

		try {

			while (ligado) {

				switch (cor) {
				case VERMELHO:
					Thread.sleep(6000);
					break;

				case VERDE:
					Thread.sleep(4000);
					break;

				case AMARELO:
					Thread.sleep(2000);
					break;
				}
				mudaCor();

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	synchronized private void mudaCor() {

		switch (cor) {

		case VERMELHO:
			cor = CorSinal.VERDE;
			break;

		case VERDE:
			cor = CorSinal.AMARELO;
			break;

		case AMARELO:
			cor = CorSinal.VERMELHO;
			break;

		}
		
		mudou = true;
		
		notify();

	}

	synchronized public void esperaMudar() {
		try {
			while (!mudou) {

				wait();

			}
			
			mudou = false;
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}
	
	
	public String getCor(){
		
		return "Cor = "+cor;
		
	}
	
	public void desligar(){
		
		ligado = false;
		System.out.println("Sinaleiro Desligado");
		
	}
	

}
