package com.colisao;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

public class testeIntersecao extends Applet implements KeyListener {

	private Shape shape1,shape2;
	
	private int [] coordX = {-30,30,30,-30};
	private int [] coordY = {-20,-20,20,20};
	
	private AffineTransform at;
	
	private Point pt1,pt2;
	
	Rectangle r1,r2;
	
	@Override
	public void start() {
		
		setSize(800,600);
		
		shape1 = new Polygon(coordX,coordY,coordX.length);
		shape2 = new Polygon(coordX,coordY,coordX.length);
		
		at = new AffineTransform();
		pt1 = new Point(50, 50);
		pt2 = new Point(200, 100);
		
		addKeyListener(this);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setTransform(at);
		
		g2d.setColor(Color.white);
		
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		
		r1 = getLimites(pt1);
		r2 = getLimites(pt2);
		
		if(r1.intersects(r2)){
			g2d.setColor(Color.RED);
		}else{
			g2d.setColor(Color.BLACK);
		}
			
		g2d.translate(pt1.x, pt1.y);
		
		
	
		g2d.draw(shape1);
		g2d.setTransform(at);
		g2d.translate(pt2.x, pt2.y);
		g2d.draw(shape2);
		
	}
	
	private Rectangle getLimites(Point p ){
		return new Rectangle(p.x -30, p.y -20, 60, 40);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			pt1.y -=4;
			repaint();
			break;
			
		case KeyEvent.VK_RIGHT:
			pt1.x+=4;
			repaint();
			break;
		case KeyEvent.VK_LEFT:
			pt1.x -=4;
			repaint();
			break;
		case KeyEvent.VK_DOWN:
			pt1.y +=4;
			repaint();
			break;
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
