package com.softgraf.thread_heranca;

/*
 * Thread atrav�s de Heran�a
 * Para criarmos uma thread atrav�s de heran�a basta estender 
 * a classe Thread e sobrescrever o metodo run(), depois instanciar  
 * um objeto desta classe e iniciar a execu��o atraves do meodo start
 * 
 * Problema de herana de thread
 * Se precisarmos, n�o podemos estrender outra classe
 * 
 * Alguns metodos herdados da classe thread 
 * -- run() serve apenas para sobrescrita, nunca � chamado diretamente 
 * --start() � atraves dele que � chamado o metodo run()
 * --sleep(milissegundos) suspende a thread, dado o objeto
 * -- currentThread() retorna o objeto da thread que est� em execu��o
 * no exato momento
 * 
 * Mesmo iniciando threads em sequencia, nunca sabemos qual vai ser a 
 * ordem exata de execu��o, apenas podemos definir prioridades
 * 
 * Em qualque programa java, temos pelo menos 2 threads em execu��o:
 * -- O programa principal (main), com alta prioridade.
 * -- E o Garbage Collector (DAEMON)
 * 
 */





public class MinhaThread extends Thread{
	
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
