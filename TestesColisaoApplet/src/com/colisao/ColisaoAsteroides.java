package com.colisao;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

import javax.swing.JOptionPane;

public class ColisaoAsteroides extends Applet implements Runnable {

	// --------------------------- DECLARAÇÃO DAS VARIAVEIS GLOBAIS

	private Thread gameloop;

	// bufer de imagem

	private BufferedImage bufferImage;

	// dimensoes da imagem

	private final int LARGURA = 1200;
	private final int ALTURA = 800;

	// imagem de fundo
	private Image fundo;

	private Graphics2D g2d;

	// salva o Affine Transform original

	private AffineTransform at;

	// objeto asteroide

	private Asteroide[] vetorAsteroides;

	// gerador randomico

	private Random gerador;

	// gera som da colisao entre asteroides

	private ClipSom somColisao;

	// numero de asteroides do vetor

	private int NUM_AST = 10;

	// ---------------------

	// implementação dos metodos da applet

	@Override
	public void start() {

		// cria um buffer de imagem para suavização gráfica
		bufferImage = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		g2d = bufferImage.createGraphics();

		// carrega a imagem de fundo
		// (http://softgraf.com/cursojava/icones/ceu.png
		fundo = carregarImagem("/com/softgraf/colisao/ceu.png");

		// instancia Affine transform

		at = new AffineTransform();

		// instancia um gerador randomico

		gerador = new Random();
		setSize(LARGURA + 40, ALTURA + 40);

		// instancia os asteroides
		vetorAsteroides = new Asteroide[NUM_AST];

		for (int i = 0; i < NUM_AST; i++) {

			vetorAsteroides[i] = new Asteroide(Color.GRAY);
		}

		// inicializa os asteroides

		for (Asteroide ast : vetorAsteroides) {

			ast.setX(gerador.nextInt(LARGURA));
			ast.setY(gerador.nextInt(ALTURA));

			ast.setVelRotacao(gerador.nextInt(10) + 1);
			double ang = gerador.nextInt(360);
			ast.setAnguloMov(ang);
			double velocidade = gerador.nextInt(3) + 1;

			ast.setVelX(Asteroide.calcAnguloMovX(ang) * velocidade);
			ast.setVelY(Asteroide.calcAnguloMovY(ang) * velocidade);

		}

		g2d.drawImage(fundo, 0, 0, this);

		// cria a thread gameloop para atualizações em tempo real

		gameloop = new Thread(this);

		
		somColisao = new ClipSom("/com/softgraf/colisao/colisao.au");
		
		gameloop.start();

	}

	@Override
	public void update(Graphics g) {

		// restaura o affine transform original

		g2d.setTransform(at);

		g2d.drawImage(fundo, 0, 0, this);

		// atualiza o ciclo do jogo
		atualizaAsteroides();
		testaColisoes();

		g.drawImage(bufferImage, 0, 0, this);

	}

	@Override
	public void stop() {
		// para a thread gameloop
		gameloop = null;
	}

	private void atualizaAsteroides() {
		for (Asteroide ast : vetorAsteroides) {
			// atualiza e rotaciona a posição XY
			ast.incXY();

			// acerta posição esquerda/direita
			if (ast.getX() < -20)
				ast.setX(LARGURA + 20);

			else if (ast.getX() > LARGURA + 20)
				ast.setX(-20);

			if (ast.getY() < -20)
				ast.setY(ALTURA + 20);
			else if (ast.getY() > ALTURA + 20)
				ast.setY(-20);

			// incrementa a rotação atual
			ast.incRotacao();

			// desenha o asteroide na tela
			ast.desenhar(g2d, at);

		}

	}

	// ------------------------ Metodos do Game

	// carrega a imagem do arquivo

	private Image carregarImagem(String arquivo) {
		URL url = null;

		try {
			url = this.getClass().getResource(arquivo);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (url == null) {
			JOptionPane.showMessageDialog(null, "erro ao carregar a imagem " + arquivo);
			return null;
		} else
			return getImage(url);

	}

	// implementa o metodo run() da interface Runnable() --------
	// permite a atualização do game
	@Override
	public void run() {

		/// obtem a thread atual

		Thread t = Thread.currentThread();

		while (t == gameloop) {
			try {
				Thread.sleep(20);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			repaint();

		}

	}


	//testa colisoes entre o asteroides
	
	private void testaColisoes(){
		Asteroide astA,astB;
		Rectangle recA, recB;
		
		for (int i = 0; i < vetorAsteroides.length -1; i++) {
			astA = vetorAsteroides[i];
			recA = astA.getLimites();
			for (int j = (i+1); j < vetorAsteroides.length ; j++) {
				
				astB = vetorAsteroides[j];
				recB = astB.getLimites();
				
				if(recA.intersects(recB)  && (!astA.isColidiu() || !astB.isColidiu()   ) ){
					somColisao.tocar();
					
					astA.setCor(Color.RED);
					
					astA.setVelRotacao(astA.getVelRotacao() * -1);
					astA.setColidiu(true);
					
					
					
					astB.setCor(Color.RED);
					
					astB.setVelRotacao(astA.getVelRotacao() * -1);
					astB.setColidiu(true);
				}
				
			}
			
			
		}
		
	}



}
