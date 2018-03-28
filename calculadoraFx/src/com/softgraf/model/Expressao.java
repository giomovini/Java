package com.softgraf.model;

/**	
 * @author henry
 * @since 12/11/2016
 * @version 1.0
 * @category Calc
 * Armazena e processa a expressao contida no visor da calculadora
 * somente os seguintes digitos sao aceitos
 * numeros: 0-9
 * virgula
 * operadores: + , - , / , * 
 * backspace(B maiusculo) : remove o ultimo digito da expressao
 * Clear ( C: maiusculo):limpa a expressao
	 * sinal de igual :processa a expressao
 * 
 * */

public class Expressao {

	private StringBuilder expressao;
	private boolean erro;


	public Expressao() {

		expressao = new StringBuilder();
		erro = false;

	}
	public String getExpressao(){
		return expressao.toString(); 
	}

	public int getLength() {
		return expressao.length();
	}

	private boolean eNumero(char digito) {
		/**retorna verdadeiro ou false*/
		return (digito >= 48 && digito <= 57);
	}

	private boolean eOperador(char digito) {
		/** retorna verdadeiro ou false
		return (digito==42 || digito ==47 || digito ==45 || digito == 43);*/
		return "+-*/".contains(String.valueOf(digito));
	}

	private char ultimoDigito() {
		return expressao.length() > 0 ? expressao.charAt(expressao.length() - 1) : 0;
	}

	private boolean existeVirgula() {
		char crc;
		for (int i = expressao.length() - 1; i >= 0; i--) {

			crc = expressao.charAt(i);
			if (eOperador(crc))
				return false;

			else if (crc == ',')
				return true;
		}
		return false;
	}

	private void limparErro() {

		if (erro) {
			expressao.delete(0, expressao.length());
			erro = false;
		}

	}

	public void guardarDigito(char digito) {
		limparErro();

		if (eNumero(digito))
			expressao.append(digito);
		else if (digito == ',' && eNumero(ultimoDigito()) && !existeVirgula())
			expressao.append(',');
		else if (digito == ',' && !eNumero(ultimoDigito()) && !existeVirgula())
			expressao.append('0').append(',');
		else if (digito == '-' && expressao.length() == 0)
			expressao.append('-');
		else if (eOperador(digito) && eNumero(ultimoDigito()))
			expressao.append(digito);
		else if (digito == 'B' && expressao.length() > 0)
			expressao.deleteCharAt(expressao.length() - 1);
		else if (digito == 'C')
			expressao.delete(0, expressao.length());
		else if (digito == '=')
			processarExpressao();
	}

	private double processaDivisao(String exp) {

		/** Converte uma expressao em um vetor
		 12/4/2 ==> ["16","4","2"]*/
		String[] arrExp = exp.split("/");

		double resultado = Double.parseDouble(arrExp[0]);

		for (int i = 1; i < arrExp.length; i++) {
			resultado /= Double.parseDouble(arrExp[i]);
		}
		return resultado;

	}

	private double processaMultiplicacao(String exp) {

		/** Converte uma expressao em um vetor
		// 12/4/2 ==> ["16","4","2"]*/
		String[] arrExp = exp.split("\\*");

		double resultado = 1;

		for (String str : arrExp) {
			resultado *= processaDivisao(str);
		}

		return resultado;

	}

	private double processaSoma(String exp) {
		
		exp.replace('.', '.');

		// Converte uma expressao em um vetor
		// 12/4/2 ==> ["16","4","2"]
		String[] arrExp = exp.split("\\+");

		double resultado = 0;

		for (String str : arrExp) {

			resultado += processaSubtracao(str);

		}

		return resultado;

	}

	private double processaSubtracao(String exp) {

		

		// Converte uma expressao em um vetor
		// 12/4/2 ==> ["16","4","2"]
		String[] arrExp = exp.split("-");

		double resultado = processaMultiplicacao(arrExp[0]);

		for (int i = 1; i < arrExp.length; i++) {
			resultado -= processaMultiplicacao(arrExp[i]);
		}

		return resultado;

	}
	
	
	

	private void processarExpressao() {

		if (expressao.length() > 0) {

			if (expressao.charAt(0) == '-')
				expressao.insert(0, '0');

			double resultado = processaSoma(expressao.toString());

			String exp = String.valueOf(resultado).replaceAll("\\.", ",");

			if (Math.round(resultado) == resultado) {
				exp = exp.substring(0, exp.length() - 2);
			}
			
			if (!Double.isFinite(resultado)) {
				erro = true;
				exp = "ERRO";
			}
			
			
			expressao = new StringBuilder(exp);
			

			System.out.println(exp);
		}

	}

}
