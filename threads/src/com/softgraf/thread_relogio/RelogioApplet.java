package com.softgraf.thread_relogio;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelogioApplet extends Applet implements Runnable {

	
	Thread threadRelogio;
	Font fonte;
	SimpleDateFormat formator;
	String hora;
	
	@Override
	public void start() {
		fonte = new Font(Font.MONOSPACED, Font.BOLD, 36);
		formator = new SimpleDateFormat("hh:mm:ss");
		threadRelogio = new Thread(this,"Relogio");
		threadRelogio.start();
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		hora = formator.format(new Date());
		g.setFont(fonte);
		g.drawString(hora, 10, 50);
	}
	
	
	@Override
	public void stop() {

		threadRelogio.interrupt();
		threadRelogio = null;
	}
	
	
	
	@Override
	public void run() {
		
		try {
			
			while(threadRelogio != null){
				repaint();
				Thread.sleep(1000);
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("Relogio interrompido");
		}
		
		
		
	}

}
