

import java.util.ArrayList;
import java.util.Scanner;

public class JogoForca {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite a palavra secreta");
		String palavra = teclado.nextLine();
		char[] achados = new char[palavra.length()];
		String letra; // letras que eu vou chutar
		boolean encontrado; // a letra digitada existe na palavra
		int totalEncontradas = 0; // quantas letras ja foram encontradas no
									// total

		String digitadas = ""; // todas as letras que ja foram digitadas

		ArrayList<String> listacorpo = new ArrayList<String>();
		listacorpo.add("pé esquerdo");
		listacorpo.add("pé direito");
		listacorpo.add("mão esquerda");
		listacorpo.add("mão direta");
		listacorpo.add("tronco");
		listacorpo.add("perna direita");
		listacorpo.add("perna esquerda");
		listacorpo.add("braço direito");
		listacorpo.add("braço esquerdo");
		listacorpo.add("cabeça");

		System.out.println("-----------------Jogo da Forca 1.0 -------------------");
		imprimeAchados(achados);

		// loop do jogo
		do {
			System.out.println("\n faça uma tentativa. Digite apenas 01 letra");
			letra = teclado.next().toUpperCase();
			// System.out.println(letra);

			if (digitadas.contains(letra)) {
				System.out.println("Voce ja digitou esta letra! tente outra");
				continue;
			} else {
				digitadas = digitadas.concat(letra);
			}
			// System.out.println("continuou o jogo...");

			encontrado = false;
			for (int i = 0; i < palavra.length(); i++) {
				if (palavra.substring(i, i + 1).equalsIgnoreCase(letra.substring(0, 1))) {
					achados[i] = letra.charAt(0);
					encontrado = true;
					totalEncontradas++;
				}

			}

			imprimeAchados(achados);

			if (totalEncontradas == palavra.length()) {
				System.out.println("\n\nParabéns !! Você ganhou");
				break;
			}

			if (!encontrado) {
				if (listacorpo.size() >= 0) {
					System.out.println("\n Errou! Você acaba de perder: " + listacorpo.get(0));
					listacorpo.remove(0);
				}
				if (listacorpo.size() == 0) {
					System.out.println("\n\n Você acaba de ser enforcado!!!" + " Perdeu o jogo! HUEHUEHU");
				} else {
					System.out.println("\nRestam " + listacorpo.size() + " partes do corpo");
				}

			}

		} while (listacorpo.size() > 0);
		
		teclado.close();
	}

	// imprime na tela as letras mostradas
	// ex: __ A __ A __ O
	private static void imprimeAchados(char[] achados) {

		System.out.println("\n");
		for (int i = 0; i < achados.length; i++) {
			if (achados[i] == 0) {
				System.out.print("__ ");
			} else {
				System.out.print(" " + achados[i] + " ");
			}
		}

	}

}
