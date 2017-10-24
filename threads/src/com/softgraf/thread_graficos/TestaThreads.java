package com.softgraf.thread_graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestaThreads extends JApplet {
	
	private JLabel lblHora,lblCronometro,lblDormir, cont1A, cont1B,cont1C,cont2,cont3,cont4;
	private JTextField txtDormir;
	private JButton btnViva,btnInterromperThread;
	private Hora hr;
	private Cronometro cr;
	private Thread hora, cronometro, graf1A, graf1B,graf1C,graf2,graf3,graf4;
	
	@Override
	public void start() {
		setLayout(null);
		setSize(800,300);
		
		// label hora
		lblHora = new JLabel("Horario: hh:mm:ss");
		lblHora.setBounds(100,20,200,20);
		add(lblHora);
		
		//lbl Cronometro
		lblCronometro = new JLabel("Cronometro: 1000");
		lblCronometro.setBounds(250,20,200,20);
		add(lblCronometro);
		
		
		//botao "esta viva"
		btnViva = new JButton("Esta viva?");
		btnViva.setBounds(10,80,100,30);
		add(btnViva);
		
		
		//informa se a thread Cronometro está viva ou morta 
		btnViva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cronometro.isAlive()) // thread viva ou morta?
					System.out.println("Thread Cronometro esta viva");
				else 
					System.out.println("Thread Cronometro esta morta");
				
			}
		});
		
		
		//Label Dormir
		lblDormir = new JLabel("Dormir (milissegundos): ");
		lblDormir.setBounds(130,80,150,30);
		add(lblDormir);
		
		//campo dormir
		txtDormir = new JTextField();
		txtDormir.setBounds(270,80,40,30);
		add(txtDormir);
		
		txtDormir.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
			
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					int tempo = Integer.parseInt(txtDormir.getText());
					cr.setTempo(tempo);
				}
			
			
			}
			
			
		});
		
		// Botao para interromper a thread do cronometro
		
		btnInterromperThread = new JButton("Interromper Thread");
		btnInterromperThread.setBounds(330,80,150,30);
		add(btnInterromperThread);
		
		btnInterromperThread.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// interrompe a thread dormindo ou acordada
				cronometro.interrupt();
				
			}
		});
		
	
		
		//thread hora
		hr = new Hora(lblHora);  // objeto Runnable
		hora = new Thread(hr);
		hora.start();
		
		
		//  thread Cronometro
		cr = new Cronometro(lblCronometro, 2000);
		cronometro = new Thread(cr);
		cronometro.start();
		
		
		
		//Labels dos contadores
		
		cont1A = new JLabel("1A");
		cont1A.setBounds(750,160,50,30);
		add(cont1A);
		
		cont1B = new JLabel("1B");
		cont1B.setBounds(750,170,50,30);
		add(cont1B);
		
		cont1C = new JLabel("1C");
		cont1C.setBounds(750,180,50,30);
		add(cont1C);
		
		cont2 = new JLabel("2");
		cont2.setBounds(750,210,50,30);
		add(cont2);
		
		cont3 = new JLabel("3");
		cont3.setBounds(750,240,50,30);
		add(cont3);
		
		cont4 = new JLabel("4");
		cont4.setBounds(750,270,50,30);
		add(cont4);
		
		//cria threads de gráficos com prioridades diferentes
		// A prioridade máxima vai depender do sistema operacional.
		// Ex. :No windows NT temos prioridade máxima = 7
		
		graf1A = new Thread(new Grafico(this,170,Color.RED,cont1A));
		graf1A.setPriority(Thread.MAX_PRIORITY);
		
		graf1B = new Thread(new Grafico(this,180,Color.RED,cont1B));
		graf1B.setPriority(Thread.MAX_PRIORITY);
		
		graf1C = new Thread(new Grafico(this,190,Color.RED,cont1C));
		graf1C.setPriority(Thread.MAX_PRIORITY);
		
		graf2 = new Thread(new Grafico(this,220,Color.GREEN,cont2));
		graf2.setPriority(Thread.NORM_PRIORITY);
		
		graf3 = new Thread(new Grafico(this,250,Color.BLUE,cont3));
		graf3.setPriority(Thread.MIN_PRIORITY);
		
		graf4 = new Thread(new Grafico(this,280,Color.GRAY,cont4));
		graf4.setDaemon(true); // Daemon tem baixa priordade
		
		
		graf1A.start();
		graf1B.start();
		graf1C.start();
		graf2.start();
		graf3.start();
		graf4.start();
		
		
		
		
	}

}
