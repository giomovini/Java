package com.softgraf.thread_graficos;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class Grafico implements Runnable {

	private Graphics g;
	private int x,y,min,max;
	private Color cor,corFundo;
	private boolean paraFrente;
	private int quant;
	private JLabel info;
	

	public Grafico(JApplet janela,int y,Color cor, JLabel info){
		
		this.g = janela.getGraphics();
		this.x = 5;
		this.y = y;
		min = 5;
		max = janela.getWidth() - 60;
		this.cor = cor;
		this.info = info;
		g.setColor(cor);
		corFundo = janela.getContentPane().getBackground();
		paraFrente = true;
		quant = 1;
		
	}
	
	@Override
	public void run() {
		
		try {
			
			while(true){
				
				g.fillOval(x, y, 7, 7);
				if(paraFrente)
					x++;
				else
					x--;
				
				if(x>=max){
					paraFrente = false;
					g.setColor(corFundo);
				}else if(x <=min){
					paraFrente = true;
					g.setColor(cor);
				}
					Thread.sleep(1);
					info.setText(String.valueOf(quant));
					quant++;
			}
			
	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
