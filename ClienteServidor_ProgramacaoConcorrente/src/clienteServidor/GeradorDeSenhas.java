package clienteServidor;

import java.util.Random;

public class GeradorDeSenhas {

	private  Random x = new Random();


	private  char gerarLetraMinuscula() {
		return (char) (x.nextInt(26) + 97);
	}

	private  char gerarLetraMaiuscula() {
		return (char) (gerarLetraMinuscula() - 32);
	}

	private char gerarLetra() {
		return x.nextBoolean() ? gerarLetraMaiuscula() : gerarLetraMinuscula();
	}

	private char gerarNumero() {
		return (char) (x.nextInt(10) + 48);
	}

	private  char gerarAlfaNumerico() {
		return x.nextBoolean() ? gerarLetra() : gerarNumero();
	}

	public  String gerarPalavra() {

		
		return new String(gerarPalavra(6));

	}
	public  String gerarPalavra(int x) {

		StringBuilder palavra = new StringBuilder();

		for (int i = 0; i < x; i++) {
			palavra.append(gerarAlfaNumerico());
		}
		return new String(palavra);

	}
	/*private  void imprimeNumero(){
		
		System.out.println( (System.currentTimeMillis())%100);		
	}*/
	public  String gerarSenhaVariavel(){
		return gerarPalavra(6 + x.nextInt(7));
	}
	

}
