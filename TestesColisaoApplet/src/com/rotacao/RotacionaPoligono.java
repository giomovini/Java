package com.rotacao;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class RotacionaPoligono extends Applet{
	
		private int []xPontos = {0, -10, -7, 7, 10};
		private int []ypontos = {-10, -2, 10, 10, -2};
		
		//shape usado para desenha
		private Polygon poligono;
		
		//posição central do poligono
		
		private int posX,posY;

		//rotação do poligonos		
		private int rotacao = 0;
		
		
		//controla a escala x e y do poligono
		private int escala = 20;
		
		
		//Graphics2d = é a "biblioteca" gráfica para desenhar
		private Graphics2D g2d;
		
		
		@Override
		public void start() {
			poligono = new Polygon(xPontos,ypontos,xPontos.length);
			// altera a cor do fundo da applet
			setBackground(Color.BLUE);
			
			//tamaho da janela da applet
			setSize(600,600);
			
			//posicao inicial do centro do poligono
			posX = getWidth()/2;
			posY = getHeight()/2;
			
			addKeyListener(new OuvinteTeclas());
			addMouseMotionListener(new OuvinteMouse());
			addMouseWheelListener(new OuvinteMouse());
		}
		
		
		//evento paint da applet
		@Override
		public void paint(Graphics g) {
			//obtem uma instancia de graphics2D
			g2d = (Graphics2D)g;
			
			//define a posicao do poligono
			g2d.translate(posX,posY);
			
			// define a escala do poligono
			g2d.scale(escala, escala);
			
			//define a rotacao do poligono
			g2d.rotate(Math.toRadians(rotacao));
			
			g2d.setColor(Color.YELLOW);
			
			g2d.fill(poligono);
			
			g2d.setColor(Color.GREEN);
			
			g2d.draw(poligono);
			
		}
		
		
		
		//classe interna para tratar dos eventos do teclado
		
		private class OuvinteTeclas extends KeyAdapter{
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					rotacao +=5;
					System.out.println("seta esquerda: "+rotacao);
					repaint();
					break;
					
				case KeyEvent.VK_RIGHT:
					rotacao -=5;
					System.out.println("seta direita: "+rotacao);
					repaint();
					break;
					
					
				default:
					break;
				}

			}
			
		}
		
		private class OuvinteMouse extends MouseAdapter{
			
			
			@Override
			public void mouseDragged(MouseEvent e) {
			
				posX = e.getX();
				
				System.out.println(e.getX());
				
				posY = e.getY();
				
				repaint();
			
			}
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				escala -=e.getWheelRotation();
				repaint();
				
				
			}
			
			
			
		}
		
		
}
