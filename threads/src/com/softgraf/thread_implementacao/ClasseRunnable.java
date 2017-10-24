package com.softgraf.thread_implementacao;


/*
 * Thread atraves de Implementacao
 * 
 * Implementamos a interface Runnable e sobrescrevemos o metodo run()
 * Depois instanciamos uma Thread e passamos este objeto
 * 
 */


//a partir dessa classe criamos um objeto Runnable
public class ClasseRunnable implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Thread: "+ Thread.currentThread().getName()+"  :  "+i );
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
