package com.softgraf.sincronizacao.sinaleiro;

public class TesteSinaleiro {
	
	
	
	public static void main(String[] args) {
		
		Sinaleiro s = new Sinaleiro();
		
		System.out.println(s.getCor());
		
		s.esperaMudar();
		
		System.out.println(s.getCor());
		s.esperaMudar();
		
		System.out.println(s.getCor());
		s.esperaMudar();
		
		System.out.println(s.getCor());
		s.esperaMudar();
		
		System.out.println(s.getCor());
		s.esperaMudar();
		
		s.desligar();
	}

}
