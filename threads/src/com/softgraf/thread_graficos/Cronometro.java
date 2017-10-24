package com.softgraf.thread_graficos;

import javax.swing.JLabel;

/* Faz uma contagem regressiva a cada 100 milissegundos 
 * Uma thread pode ser interrompida quando acordada ou  dormindo
 * 
 */




public class Cronometro implements Runnable{

	private JLabel label;
	private int numero;
	private int tempo = 10;
	
	public  Cronometro(JLabel label, int numero) {
		this.label = label;
		this.numero = numero;
	}
	
	public void setTempo(int tempo){
		this.tempo = tempo;
	}
	
	
	
	@Override
	public void run() {
		
		while(numero > 0){
			numero--;
			
			label.setText("Cronometro: "+numero);
			
			try {
				// dorme, se for interrompida lança InterruptException
				Thread.sleep(tempo);
				
				//thread acordou
				for (int i = 0; i < 12345; i++) Math.sin(45); // faz alguma coisa
				//verifica se a thread foi interrompida agora (acordada)
				
				if(Thread.interrupted()){
					System.out.println("Thread interrompida quando ACORDADA");
					break;
				}
					
				
				
				
			} catch (InterruptedException e) {

					System.out.println("Thread interrompida quando dormindo");
				   break;
			}
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
}
