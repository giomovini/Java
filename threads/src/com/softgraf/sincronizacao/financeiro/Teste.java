package com.softgraf.sincronizacao.financeiro;

public class Teste {

	public static void main(String[] args) {
		
		Financeiro r = new Financeiro(new Conta(5000.00),1000.00);
		
		Thread joao = new Thread(r,"Joao");
		
		Thread maria = new Thread(r,"Maria");
		
		Thread andre = new Thread(r,"Andre");
		
		Thread rafael = new Thread(r,"Rafael");
		
		
		joao.start();
		maria.start();
		andre.start();
		rafael.start();
		
		
		
	}
	
	
}
