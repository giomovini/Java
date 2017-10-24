package com.softgraf.sincronizacao;

public class ThreadJoin extends Thread{
	
	
	public  ThreadJoin(String nome) {
		setName(nome);
		
	}
	
	
	
	@Override
	public void run() {

		try {

			for (int i = 0; i < 5; i++) {

				System.out.println("\nRodando thread " + getName() + "  - contador = " + (i + 1));
				Thread.sleep(1000);

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nThread " + getName() + " finalizou");

	}
	
	public static void main(String[] args) {
		Thread t1 = new MinhaThread("T1");
		Thread t2 = new MinhaThread("T2");
		Thread t3 = new MinhaThread("T3");

		System.out.println("\nIniciando threads...");
		
		t1.start();
		t2.start();
		t3.start();
		
	
		try {
			System.out.println("\nt1.join");
			t1.join();
			System.out.println("\nOK t1 joined");
			
			System.out.println("\nt2.join");
			t2.join();
			System.out.println("\nOK t2 joined");
			
			System.out.println("\nt3.join");
			t3.join();
			System.out.println("\nOK t3 joined");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("\n T1,T2,T3 finalizaram");
		
		System.out.print("\n Thread main finalizou");
	
		
		
	}
	
	

}
