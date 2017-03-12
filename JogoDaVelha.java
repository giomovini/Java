

import java.util.Scanner;


public class JogoDaVelha {

  
    public static void main(String[] args) {
       
      

		Scanner teclado = new Scanner(System.in);
		int opcao;
		
		char k = 0;
		int jog = 0;
		int jogador = 0;
		int l = -1;
		int c = -1;

		do {
            char matriz[][] = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, };
			System.out.println("\nMenu de opcoes: ");
			System.out.println("1 = Jogar");
			System.out.println("0 = Sair");

			System.out.print("\nEscolha: ");
			opcao = teclado.nextInt();

			switch (opcao) {
			case 1: // opcao == 1
				System.out.println("\nVamos la!");
				for (int i = 0; i <= 8; i++) {
					if (jog % 2 == 0) {
						System.out.println("\njogador 1");
						jog++;
						k = 'X';
						jogador = 1;
					} else if (jog % 2 != 0) {
						System.out.println("\n jogador 2");
						jog++;
						k = 'O';
						jogador = 2;
					}
					while ((l < 0 || l > 2) && (c < 0 || c > 2)) {
						System.out.print("\nDigite o numero da linha: ");
						l = teclado.nextInt();
						System.out.print("\nDigite o numero da coluna: ");
						c = teclado.nextInt();
					}
					matriz[l][c] = k;

					for (int linha = 0; linha <= 2; linha++) {
						System.out.println();
						for (int col = 0; col <= 2; col++) {
							System.out.print("_" + matriz[linha][col] + "_|");
						}
					}
					
					l = -1;
					c = -1;
				
				if((matriz[0][0] == k && matriz[1][1] == k && matriz[2][2] == k) ||
						(matriz[0][0] == k && matriz[0][1] == k && matriz[0][2] == k)||
						(matriz[0][1] == k && matriz[1][1] == k && matriz[2][1] == k)||
						(matriz[1][0] == k && matriz[1][1] == k && matriz[1][2] == k)||
						(matriz[2][0] == k && matriz[2][1] == k && matriz[2][2] == k)||
						(matriz[0][0] == k && matriz[1][0] == k && matriz[2][0] == k)||
						(matriz[0][2] == k && matriz[1][2] == k && matriz[2][2] == k)||
						(matriz[0][2] == k && matriz[1][1] == k && matriz[2][0] == k)
						){
					System.out.println("\njogador " + jogador + " venceu!");
                                        
					
					
				}else if(i==8){
                                    System.out.println("Deu Velha");
                                }
                                
                                }
                                
                                
				
				break;
		

			case 0:
				System.out.println("\nSaindo...");
				break;

			default:// outro
				System.out.println("\nERRO!");

			}

		} while (opcao != 0);

		System.out.println("fim");

	



    }
    
}
