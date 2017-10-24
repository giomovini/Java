package com.softgraf.sincronizacao;

//Neste exemplo main() usa o metodo isAlive() para aguardar todas as threads finalizarem

public class MinhaThread extends Thread {

	String nome;

	public MinhaThread(String nome) {
		this.nome = nome;
	}

	@Override
	public void run() {

		try {

			for (int i = 0; i < 5; i++) {

				System.out.println("\nRodando thread " + nome + "  - contador = " + (i + 1));
				Thread.sleep(1000);

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nThread " + nome + " finalizou");

	}

	// -------------- Thread main -----------------
	public static void main(String[] args) {
		Thread t1 = new MinhaThread("T1");
		Thread t2 = new MinhaThread("T2");
		Thread t3 = new MinhaThread("T3");

		System.out.println("\nIniciando threads...");

		t1.start();
		t2.start();
		t3.start();

		do {

			System.out.print(".");

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}

		} while (t1.isAlive() || t2.isAlive() || t3.isAlive());
		
	
		
		System.out.print("\n\nThread T1 está viva? "+t1.isAlive());
		System.out.print("\nNome da thread atual = "+Thread.currentThread().getName());
		System.out.print("\nEsta viva? "+Thread.currentThread().isAlive());
		System.out.print("\nThread main finalizou -> está morta :(");
	}

}
