package com.softgraf.thread_implementacao;

public class PrincipalRunnable {

	public static void main(String[] args) {
		
		Runnable r = new ClasseRunnable();
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		
		t1.setName("Thread A");
		t2.setName("Thread B");
		t3.setName("Thread C");
		
		t1.start();
		t2.start();
		t3.start();
		
		
	}
}
