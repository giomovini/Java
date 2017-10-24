package com.softgraf.thread_heranca;

public class PrincipalHeranca {

	public static void main(String[] args) {
		
		
		Thread t1 = new MinhaThread();
		Thread t2 = new MinhaThread();
		Thread t3 = new MinhaThread();
		
		t1.setName("Thread A");
		t2.setName("Thread B");
		t3.setName("Thread C");
		
		t1.start();
		t2.start();
		t3.start();
		

	}

}
