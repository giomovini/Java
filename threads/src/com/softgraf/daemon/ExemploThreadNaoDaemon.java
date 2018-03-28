package com.softgraf.daemon;

public class ExemploThreadNaoDaemon {
	
	public static void main(String[] args) {
		
		Thread threadNormal = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (;;) {

					try {
						System.out.println("Executando thread normal para sempre");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("*** ThreadNormal interrompida ");
					} finally {
						System.out.println("*** chamado bloco finally");
					
					}
				}

			}
				
			
		});
		
		System.out.println("=== Exemplo thread nao daemon");
		System.out.printf("\n ThreadNormal é uma thread daemon? %b - Prioridade ** %d ",threadNormal.isDaemon(),threadNormal.getPriority());
		System.out.printf("\n Thread main é uma thread daemon? %b - Prioridade ** %d ",Thread.currentThread().isDaemon(),Thread.currentThread().getPriority());
		
		threadNormal.start();
		
		for(int i = 0;i<5;i++){
			
	
			try {
				System.out.println("Executando thread...");
				Thread.sleep(1000);
				
				//apos testar o exemplo, remover os comentarios e ver as mensagens
				//if(i==3) threadNormal.interrupt();
				
				
			} catch (InterruptedException e) {
				System.out.println("Thread main interrompida");
			}
			System.out.println("** thread main finalizada");
			// apos main ter finalizado, threads do tipo nao daemon
			// continuarao executando
		}
		
		
		
	}
	
	
	

}
