/*
Encontrar o maior elemento e a sua respectiva posição de uma matriz B de dimensão 7x5.
Os valores são inseridos aleatoriamente
*/
import java.util.Random;
class MaiorValorNaMatriz
{
	public static void main(String[] args)
	{
		Random gerador = new Random();

		int linha = 7;
		int coluna = 5;
		int maior=0;
		int l=0,c=0;
		
		int b[][] = new int[linha][coluna];
		


		for (int i=0;i<7 ;i++ ) 
		{
			for (int j=0;j<5 ;j++ ) 
			{
				b[i][j] = gerador.nextInt(40);
			}
			
		}

		for (int i=0;i<7 ;i++ ) 
		{
			System.out.println();
			for (int j=0;j<5 ;j++ ) 
			{
				System.out.print(b[i][j]+"    ");
			}
			
		}
		
		for (int i=0;i<7 ;i++ ) 
		{
			System.out.println();
			for (int j=0;j<5 ;j++ ) 
			{
				if(b[i][j]>maior){
					maior = b[i][j];
					l=i;
					c=j;

				}
			}
			
		}
		System.out.println("Maior valor = "+maior+" Posicao b["+(l+1)+"]["+(c+1)+"]");










	}
}