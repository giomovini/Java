package com.softgraf.sincronizacao.wait_notify;

public class ThreadTic extends Thread{

	final private ObjetoTicTac objTicTac;
	
	
	public ThreadTic(ObjetoTicTac objTicTac ){
		
		this.objTicTac = objTicTac;
		
	}
	
	@Override
	public void run() {

		for (int i = 0; i < ObjetoTicTac.quant; i++) {
			objTicTac.tic(true);
		}
		
		
		//interrompe o relogio
		objTicTac.tic(false);
		
		
		
		
	}
	
	
	
	
	
	
}
