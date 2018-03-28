package com.softgraf.sincronizacao.wait_notify;

public class ThreadTac extends Thread{
	
	final private ObjetoTicTac objTicTac;
	
public ThreadTac(ObjetoTicTac objTicTac ){
		
		this.objTicTac = objTicTac;
		
	}


	@Override
	public void run() {
		for (int i = 0; i < ObjetoTicTac.quant; i++) {
			
			objTicTac.tac(true);
			
		}
		
		
		//interrompe  relogio
		objTicTac.tac(false);
	}
	
	




	

}
