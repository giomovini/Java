/*
Dados 2 conjuntos A e B de N posições, gerar a partir destes o conjunto C, sabendo-se que o 
conjunto C deverá conter os elementos comuns entre A e B.
*/

import java.util.Scanner;
import java.util.Random;

class ElementosComuns
{
	public static void main(String[] args)
	{

		Scanner teclado = new Scanner(System.in);

		System.out.print("Digite o tamanho dos vetores a e b: ");
		int tamanho = teclado.nextInt();

		Random gerador = new Random();

		int a[] = new int [tamanho];
		int b[] = new int [tamanho];
		int c[] = new int [tamanho];

		for(int i=0;     i<tamanho;     a[i] = gerador.nextInt(10),i++);
		for(int i=0;     i<tamanho;      b[i] = gerador.nextInt(10),i++);
			

		for (int i=0;i<tamanho;System.out.println("A["+i+"] = "+a[i]),i++);


			System.out.println("----------------------------------");
		for (int i=0;i<tamanho;System.out.println("B["+i+"] = "+b[i]),i++);

		System.out.println("----------------------------------");
 			
		int contC=0;


 		for(int i = 0; i<tamanho;i++)
 		{
 			for(int j = 0; j<tamanho;j++)
 			{

				if(a[i]==b[j])
 				{

 					System.out.println("A["+i+"] = "+a[i]+" B["+j+"] = "+b[j]);
 					c[contC] = b[j];
 					contC++;
 					
 				}
 			}
	 	}
		System.out.println("----------------------------------");

	 	for (int i = 0;i<tamanho ;i++ ) 
	 	{
	 		System.out.println(c[i]);
	 	}
	}
}