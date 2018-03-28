import java.util.Random;
import java.util.Scanner;

public class Principal {

	static Scanner teclado = new Scanner(System.in);

	static int tamX;
	static int tamY;
	static int maquinas;
	static double distancia = 0; // valor inicial 
	static int iteracoes;
	//variaveis globais para manipulação de varios metodos

	public static void main(String[] args) {

		System.out.println("tamanho do mapa ");
		tamX = teclado.nextInt();
		tamY = tamX;

		System.out.println("Quantidade de maquinas, (maximo " + (tamX * tamY) + " )");
		maquinas = teclado.nextInt();

		System.out.println("quantidade de iteracoes");
		iteracoes = teclado.nextInt();

		Principal m = new Principal();

		int posicoes[][] = m.gerarMatriz(tamX, tamY);
		int melhoresPosicoes[][] = new int[maquinas][3];
		
		
		m.imprimirCordenadas(posicoes);

		double melhorDistancia = -1;
		// valor apenas para definir a primeira iteracao
		

		int i = 1;
		for (; iteracoes > 0; iteracoes--, i++) {
			System.out.print("iteracao " + i + "   =    ");
			distancia = 0;
			m.calcularRota(posicoes);
			

			
			if (melhorDistancia == -1) {
				melhorDistancia = distancia;
				melhoresPosicoes = m.copiaMatriz(posicoes);
			}
			if (melhorDistancia > distancia) {
				
				melhorDistancia = distancia;
				melhoresPosicoes = m.copiaMatriz(posicoes);
				//System.out.println(melhorDistancia+"--------------");
				
				
				
			}
			m.permutar(posicoes);

		}

		System.out.println("Melhor Distancia = " + melhorDistancia);
		
		System.out.println("\n\nMelhores Posicoes");
		m.imprimirCordenadas(melhoresPosicoes);

	}

	int[][] gerarMatriz(int tamanhoX, int tamanhoY) {

		Principal m = new Principal();

		int posicoes[][] = new int[maquinas][3];

		int x[][] = new int[tamanhoX][tamanhoY];

		for (int i = 0; i < tamanhoX; i++) {

			for (int j = 0; j < tamanhoY; j++) {

				x[i][j] = 0;
			}
		}

		int posicaoX, posicaoY;

		int maq;

		Random ger = new Random();

		int k = 0;

		while (k < maquinas) {

			maq = ger.nextInt(5) + 1;

			posicaoX = ger.nextInt(tamanhoX);
			posicaoY = ger.nextInt(tamanhoY);

			if (m.existeMaquina(x, posicaoX, posicaoY)) {
				posicaoX = ger.nextInt(tamanhoX);
				posicaoY = ger.nextInt(tamanhoY);
			} else {
				posicoes[k][2] = k;
				posicoes[k][0] = posicaoX;
				posicoes[k][1] = posicaoY;

				x[posicaoX][posicaoY] = maq;

				k++;

			}

		}

		imprimirMatiz(x);

		return posicoes;

	}
	//funcao que gera a matriz com maquinas em posicoes aleatorias
		
	void imprimirMatiz(int matriz[][]) {

		for (int i = 0; i < tamX; i++) {

			for (int j = 0; j < tamY; j++) {

				if (matriz[i][j] == 0) {

					System.out.print("- ");
				} else {
					System.out.print("* ");
				}

			}
			System.out.println();

		}

	}
	//funcao que imprime a matriz
	
	void imprimirCordenadas(int matriz[][]) {
		for (int i = 0; i < maquinas; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 2)
					System.out.println("M =  " + ((matriz[i][j]) + 1));

				else
					System.out.print("" + matriz[i][j] + " ");

			}
			System.out.println();

		}

	}
	//funcao que imprime o par de cordenadas
	
	void calcularRota(int matriz[][]) {

		Principal m = new Principal();

		for (int i = 0; i < maquinas - 1; i++) {

			distancia += Math.sqrt(Math.pow((m.posicao(matriz, i)[0]) - (m.posicao(matriz, i + 1)[0]), 2)
					+ Math.pow((m.posicao(matriz, i)[1]) - (m.posicao(matriz, i + 1)[1]), 2));

		}
		System.out.println(distancia);
	}
	//funcao que calcula a distancia entre as maquinas
	
	void permutar(int matriz[][]) {

		int x, y, trocar;
		Random ger = new Random();

		if (maquinas > 1)
			trocar = ger.nextInt(maquinas - 1);
		else
			trocar = 0;

		x = matriz[trocar][0];
		y = matriz[trocar][1];

		if (maquinas > 1){
			matriz[trocar][0] = matriz[trocar + 1][0];
		matriz[trocar][1] = matriz[trocar + 1][1];

		matriz[trocar + 1][0] = x;
		matriz[trocar + 1][1] = y;
	}

	}
	//permuta as maquinas entre si
	
	int[] posicao(int matriz[][], int k) {
		int posicao[] = new int[2];

		for (int i = 0; i < maquinas; i++) {

			if (matriz[i][2] == k) {
				posicao[0] = matriz[i][0];
				posicao[1] = matriz[i][1];
			}

		}
		return posicao;

	}
	
	boolean existeMaquina(int matriz[][], int x, int y) {

		if (matriz[x][y] == 0) {
			return false;
		} else {
			return true;
		}
	}
	// funcao que verifica a existencia de maquinas no mapa
	
	int[][] copiaMatriz(int posicoes[][]){
		int aux[][] = new int[maquinas][3];
		
		for (int i = 0; i < maquinas; i++) {
			for (int j = 0; j < 3; j++) {

				aux[i][j] = posicoes[i][j];
				
			}
		}
		
		return aux;
	}
	
}
