package com.softgraf.sincronizacao;

public class ThreadLoop {
	
	public static void main(String[] args) {
		
		int tempo = 100;
		System.out.println("Thread que aguarda a ela mesma!");
		
		try {
			
			for(;;){
				System.out.println("\n");
				Thread.currentThread().join(tempo);
				tempo+=100;
				
				if(tempo > 1000) 
					Thread.currentThread().interrupt();
		}
			

		} catch (InterruptedException e) {
			
		}
		
		
	}

}
