package com.softgraf.model;

import javafx.scene.text.Font;

public class Fonte {
	/**
	 * padrao de fontes de textos da calculadora
	 * GRANDE 22
	 * MEDIA  16
	 * PEQUENA  13
	 * MINIMA  10
	 * 
	 * declaracao : final public static font GRANDE
	 * exemplo de uso: Font f = fonte.GRANDE
	 * 
	 * */
	
	/*
	 * este comentario nao aparecera no javadoc
	 * 
	 * */
	
	
	
	
	public  final static Font GRANDE;
	public  final static Font MEDIA;
	public  final static Font PEQUENA;
	public  final static Font MINIMA;

	
	static{
		GRANDE = new Font(22);
		MEDIA = new Font(16);
		PEQUENA = new Font(13);
		MINIMA = new Font(10);
	}
	
}
