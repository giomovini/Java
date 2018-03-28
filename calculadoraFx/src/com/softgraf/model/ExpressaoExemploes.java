package com.softgraf.model;

import java.util.Scanner;

public class ExpressaoExemploes {
	public static void main(String[] args) {
	
		
		System.out.println((char)41);
		
		
		System.out.println(fatorial(5));

		
		/*
		System.out.println("Digite o primeiro valor em seguida o segundo");
		Scanner teclado = new Scanner(System.in);
		
		String calculo = teclado.nextLine();
		

		int total=0;
		String[]vetor = calculo.split("\\+");
		
		for(int i=0;i<vetor.length;i++){
			
			total += Integer.valueOf(vetor[i]);
			
		}
		System.out.println(total);
		
		
		*/
		
		
		
		
		
		
		
		
		
		/*
		int posMais = calculo.indexOf("+");
		String a = calculo.substring(0, posMais);
		String b = calculo.substring(posMais+1);
		
		int total = Integer.valueOf(a)+Integer.valueOf(b);
		
		System.out.println(total);
		*/
		
		
		
	
		
		
		
		

	}
	
	
	
	private static int fatorial(int i){
		return (i==1) ? 1 :  i * fatorial(i-1);
	}
	


}
