package com.softgraf.daemon;

/*
	Exemplo thread daemon
	threads daemon n�o s�o recomendadas para trabalhos cr�ticos
	pois estas podem ser encerradas a qualquer momento pela JVM
	sem qualquer aviso
	
 */
public class ExemploThreadDaemon {

	public static void main(String[] args) {

		Thread threadDaemon = new Thread(new Runnable() {

			@Override
			public void run() {
				for (;;) {

					try {
						System.out.println("Executando threadDaemon");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("*** ThreadDaemon interrompida ");
						e.printStackTrace();
					} finally {
						System.out.println("*** threads daemon n�o executam o bloco finally quando encontrado");
						// THREADS DAEMON n�o sao recomendadas para servi�os de
						// I/O (s flush)
					}
				}

			}
		});
		
		
		threadDaemon.setDaemon(true);
		// � recomendavel que threads daemon tenham baixa prioridade para
		// executarem em "segundo plano"
		threadDaemon.setPriority(Thread.MIN_PRIORITY);
		
		System.out.println("=== Exemplo daemon ===");
		System.out.printf("\n threadDaemon � uma thread daemon ? %b - prioridade %d",threadDaemon.isDaemon(),threadDaemon.getPriority() );
		
		threadDaemon.start();
		
		for(int i =0;i<5;i++){
			
		
			try {
				System.out.println("Executando thread main");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("thread main interrompida");
			}
			
			
			System.out.println("***** Thread main finalizou");
			
			
		}

	}

}
