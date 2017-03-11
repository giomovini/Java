/*
Escreva um algoritmo que gere uma matriz M[5,5] aleatoriamente e calcula a soma:
a) da linha 4 de M
b) da coluna 2 de M
c) da diagonal principal
d) da diagonal secundária-----
e) de todos os elementos da matriz
Escreva estas somas e a matriz.
*/
import java.util.Random;
class MatriRandomicaCalculos
{
	public static void main(String[] args)
	{
		Random gerador = new Random();

		float somaL,coluna, diagonalP, diagonalS,todosElementos,ordem;
		somaL =0;
		coluna=0;
		diagonalP=0;
		diagonalS=0;
		todosElementos=0;
		ordem=5;
		
		float m[][] = new float[(int)ordem][(int)ordem];

		for(int i=0; i<ordem; i++)
		{
			for(int j=0; j<ordem; j++)
			{
				m[i][j]= gerador.nextInt(10);
			}
			
		}
		for(int i=0; i<ordem; i++)
		{
			 System.out.println(); 
			for(int j=0; j<ordem; j++)
			{
				System.out.print(m[(i)][(j)]+"    ");
			}
		}

		for(int i=3, j=0; j<ordem; j++)
		{
			somaL = somaL+m[i][j];
		}

		System.out.println("\n Soma da linha 4 = "+somaL);

		for(int i=0, j=1; i<ordem; i++)
		{
			coluna = coluna+m[i][j];
		}

		System.out.println("\n Soma da coluna 2 = "+coluna);

		for(int i=0, j=0; i<ordem; i++,j++)
		{
			diagonalP = diagonalP+m[i][j];
		}
		System.out.println("\n Soma da diagonal principal = "+diagonalP);

		for(int i = 0, k =1; i<ordem; i++,k++)
		{
			diagonalS = diagonalS+ m[i][(int)ordem-k];
		}
		System.out.println("\n Soma da diagonal secundaria = "+diagonalS);
		
		for (int i=0;i<ordem ;i++ ) 
		{
			for (int j=0 ;j<ordem ;j++ ) 
			{
				todosElementos = todosElementos+m[i][j];
			}
		}
		System.out.println("\n Soma de todos os elementos = "+todosElementos);
	}
}