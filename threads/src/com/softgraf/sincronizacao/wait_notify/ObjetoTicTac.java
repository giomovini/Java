package com.softgraf.sincronizacao.wait_notify;
/*
 * Este objeto é compartilhado por 2 threads : ThreadTic e ThreadTac 
 * ThreadTic executa e fica aguardando a ThreadTac, então,
 * ThreadTac executa e fica aguardando a ThreadTic,etc..
 * 
 * Para que isso ser possivel é necessario usar métodos sincronizados
 *  colocar o objeto em estado de espera através de wait() e depois 
 *  liberar o objeto com notify()
 *  
 *  Obs. 1: wait() e notify() só podem ser usados em métodos sincronizados 
 *  (syncronized)
 *  
 *  Obs. 2: neste exemplo foi ignorado a possibilidade de "falsa ativacao",
 *  o que pode ocorrer em caso real, e no qual, devemos usar um loop com uma 
 *  variavel de controle para evitar este problema, que tem uma causa complexa.
 *  
 *   
 *   Métodos de sincronizacao:
 *   - Wait() coloca o objeto em estado de espera, isso faz com que uma thread
 *   que tente acessar o objeto fique bloqueada, até que um notify() ocorra.
 *   
 *  - notify(): avisa que uma única thread que esteja aguardando, que o objeto foi liberado
 *  
 *  - notifyAll() : nodifica todas as threads que estejam aguardando o objeto.
 * 
 */

public class ObjetoTicTac {

	final public static int quant = 5;

	// metodo chamado pela threadTic
	synchronized void tic(boolean rodando) {

		if (rodando) { // rodando = true
			System.out.println("Tic...");
			// permite que a ThreadTac seja executada (estava aguardando para
			notify();

			// apos inicicializar entra em estado de espera

			try {
				wait();
			} catch (InterruptedException e) {

			}

		} else { // rodando = false
			notify();
		}

	}
	
	synchronized void tac(boolean rodando) {

		if (rodando) { // rodando = true
			System.out.println("Tac...");
			// permite que a ThreadTac seja executada (estava aguardando para
			notify();

			// apos inicicializar entra em estado de espera

			try {
				wait();
			} catch (InterruptedException e) {

			}

		} else { // rodando = false
			notify();
		}

	}
	
	
	
	
	
	
	
	

}
