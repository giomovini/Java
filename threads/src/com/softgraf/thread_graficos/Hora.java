package com.softgraf.thread_graficos;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

/*
 * Uma thread pode ter os seguintes estados principais:
 *  - Não iniciada: fo instanciada, porem o metodo run() ainda nao foi executado.
 *  - Iniciada: O metodo run() já foi chamado via start();
 *  - Em execução: pode estar dormindo (sleep) ou acordada.
 *  - Morta: A thread acabou sua execução ou foi interrompida
 *  - Uma thread morta não revive jamais!
 */


public class Hora implements Runnable{
	
	private SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");
	private String agora;
	private JLabel label;
	
	
	
	public Hora(JLabel label){
		this.label = label;
	}
	
	@Override
	public void run() {
		try {
			
			while(true){
				agora = formatador.format(new Date());
				label.setText("Hora : "+agora);
				
				Thread.sleep(1000);
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	
	

}
