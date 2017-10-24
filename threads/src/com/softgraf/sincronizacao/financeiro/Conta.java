package com.softgraf.sincronizacao.financeiro;

public class Conta {

	private double saldo;
	
	public Conta(double saldoInicial) {
		saldo = saldoInicial;
	}
	
	public double getSaldo(){
		return saldo;
	}
	
	public void sacar (double valorSaque){
		this.saldo -=valorSaque;
	}
	
	
}
