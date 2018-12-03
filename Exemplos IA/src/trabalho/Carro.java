package trabalho;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Carro implements Serializable{
	
	private int ano,potenciaCV;
	private String cor,nome,cambio;
	private double preco;
	
	
	public Carro(int ano, int potenciaCV, String cor, String nome, String cambio, double preco) {
		super();
		this.ano = ano;
		this.potenciaCV = potenciaCV;
		this.cor = cor;
		this.nome = nome;
		this.cambio = cambio;
		this.preco = preco;
	}
	
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getPotenciaCV() {
		return potenciaCV;
	}
	public void setPotenciaCV(int potencia) {
		this.potenciaCV = potencia;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCambio() {
		return cambio;
	}
	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
