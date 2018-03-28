package datas;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JFrame;
import javax.swing.JLabel;

public class RelogioSimples {
	
	public static void main(String[] args) {
		
	
	JFrame janela = new JFrame("Relogio");
	janela.setSize(200,150);
	janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	janela.setLocationRelativeTo(null);
	janela.setLayout(null);
	janela.setResizable(false);

	JLabel segundo = new JLabel("00:00:00");
	segundo.setSize(150,40);
	segundo.setLocation(25,30);
	
	Font fonte = new Font(Font.SERIF, Font.BOLD,40);
	segundo.setFont(fonte);
	janela.add(segundo);
	
	long ultimo = System.currentTimeMillis();
	long atual;
	String shora;
	SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");
	
	janela.setVisible(true);
	
	final long MILI_SEGUNDOS_POR_minuto = 1000;
	
	
	
	while(true){
	shora = formatador.format(new Date());
	segundo.setText(shora);
	
	do{
		atual = System.currentTimeMillis();
	}while (ultimo +1000>atual);
	
	ultimo = atual;
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	

}
