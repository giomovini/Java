package com.softgraf.sincronizacao.financeiro;

public class Financeiro implements Runnable{

	public Conta conta;
	private double valorSaque;
	
	public Financeiro(Conta conta,double valorSaque) {
		this.conta = conta;
		this.valorSaque = valorSaque;
	}
	
	synchronized private void efetuarSaque(double valorSaque){
		String nome = Thread.currentThread().getName();
	
		if(conta.getSaldo() >= valorSaque){
			System.out.println("Saldo atual =  R$ "+conta.getSaldo()+" - "+nome+" irá sacar  R$"+valorSaque   );
			
			
			
			
			
			try {
				
				Thread.sleep(1);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			conta.sacar(valorSaque);
			
			System.out.println(nome+ " sacou R$"+valorSaque+" - Saldo atual = R$ "+conta.getSaldo());
			
			
		}else{
			System.out.println(nome+" não consegui sacar R$ "+valorSaque+" saldo atual: "+conta.getSaldo());
		}
	
	
	
	
	}
	
	
	
	
	
	@Override
	public void run() {
		efetuarSaque(valorSaque);
		
		if(conta.getSaldo() <= 0)
			System.out.println("O dinheiro acabou!");
		
	}
	

}
