package com.softgraf.sincronizacao.wait_notify;

public class TesteTicTac {

	
	public static void main(String[] args) {
		
		ObjetoTicTac ticTac = new ObjetoTicTac();
		
		
		Thread tic = new ThreadTic(ticTac);
		Thread tac = new ThreadTac(ticTac);
		
		tic.start();
		tac.start();
		
		
	}
	
	
	
}
