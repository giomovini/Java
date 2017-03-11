/*Dado o vetor F com 20 elementos inteiros, substituir cada elemento por si mesmo multiplicado pela sua posição no conjunto.*/
import java.util.Random;
class multiplicacaoVetorPosicao{
	public static void main(String[] args){

		int f[] = new int[20];
		//declaração do vetor (20 posições)
		Random gerador = new Random();
		//metodo aleatorio para preencher o vetor
		for(int i = 0; i<20; i++)
		{
			f[i]= gerador.nextInt(1000);
		}//preenchimento do vetor
		System.out.println("-----------------------------------");
		for(int i = 0; i<=19; i++)
		{
			System.out.println("Posicao "+i+" * "+f[i]+" = "+(i*f[i]));
		}
		//multiplicação pelo seu indice


	}
}