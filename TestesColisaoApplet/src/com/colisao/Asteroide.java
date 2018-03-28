package com.colisao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Asteroide {
	
	//variaveis do objeto
	private Shape shape;
	private Color cor;
	private boolean colidiu;
	private double x,y;
	private double velX,velY;
	private double anguloMov;
	private double rotacao;
	private double velRotacao;
	
	
	// definicao do formato (poligonal) do asteroide;
	private int[] astX = {-20,-13,0,20,22,20,12,2,-10,-22,-16};	
	private int[] astY = {20,23,17,20,16,-20,-22,-14,-17,-20,-5};	
	
	//construtor da classe
	
	public Asteroide(Color cor){
		this.shape = new Polygon(astX,astY,astX.length);
		this.cor = cor;
		
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public double getAnguloMov() {
		return anguloMov;
	}
	
	public double getVelRotacao() {
		return velRotacao;
	}
	
	public boolean isColidiu() {
		return colidiu;
	}
	
	public void setColidiu(boolean colidiu){
			this.colidiu = colidiu;
		
	}
	
	public Rectangle getLimites(){
		
		return new Rectangle((int) getX() - 22, (int) getY() -22 , 44 , 44 );
		
	}
	
	public void setCor(Color x) {
		this.cor = x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	public void incXY(){
		this.x += velX;
		this.y += velY;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public void setAnguloMov(double angulo) {
		
		//matém angulo do movimento entre 0  e 360
		
		if(angulo >=360)
			this.anguloMov = 0;
		else if(angulo < 0)
			this.anguloMov = 0;
		else
			this.anguloMov = angulo;
		
	}
	
	public void setVelRotacao(double velRotacao) {
		this.velRotacao = velRotacao;
	}
	
	public void incRotacao(){
		this.rotacao += velRotacao;
		//mantem o angulo entre 0 e 359
		
		if(rotacao>=360)
			this.rotacao=0;
		else if(rotacao<0)
			this.rotacao = 359;
		
		
	}
	
	//calcula angulo de movimento do asteroide em x
	
	public static double calcAnguloMovX(double angulo){
		
		return Math.cos(Math.toRadians(angulo));
		
	}
	
	public static double calcAnguloMovY(double angulo){
		
		return Math.sin(Math.toRadians(angulo));
	}
	
	public void desenhar(Graphics2D g2d, AffineTransform origem){
		g2d.setTransform(origem);//restaura o affine transform original
		
		g2d.translate(x, y);	// define a origem das coordenadas x,y
		g2d.rotate(Math.toRadians(rotacao));    // altera a rotação das coords. X,Y
		g2d.setColor(cor); // define a cor de preenchimento do asteroide
		g2d.fill(shape);// desenha o shape do asteroide
	
	}
	
	
	

}
