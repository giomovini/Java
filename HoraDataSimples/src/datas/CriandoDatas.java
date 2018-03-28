package datas;

import java.util.Date;

public class CriandoDatas {

	public static void main(String[] args) {

		final long MILI_SEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;

		Date hoje = new Date();
		System.out.println("Hoje é " + hoje);
		System.out.println("Hoje é " + hoje.getTime());
		// retorna um long que representa o tempo desde que o java foi criado

		long tempo = hoje.getTime();

		long dias = tempo / MILI_SEGUNDOS_POR_DIA;
		long anos = dias / 365;
		long meses = anos * 12;
		long horas = dias * 24;

		System.out.println("tempo decorrido desde 1 de janeiro de 1970");
		System.out.printf("em dias: %d \nEm meses: %d \nEm anos: %d \nEm horas: %d\n\n ", dias, meses, anos, horas);

		
		//hoje + 10 dias
		long novaDataMili = hoje.getTime() + (MILI_SEGUNDOS_POR_DIA*10);
		// criando uma data atraves do construtor Date(tempo_em_milissegundos) 
		Date novaData = new Date(novaDataMili);
		System.out.println("Daqui dez dias será: "+novaData);
		
		//comparando datas 
		if(novaData.after(hoje))
			System.out.println("novaData é posterior a hoje");
		
		
		//alterando a data para 30 dias antes 
		novaData.setTime(hoje.getTime() - (MILI_SEGUNDOS_POR_DIA*30));
		
		System.out.println("30 dias atras foi: "+novaData);
		
		//comparando datas
		if(novaData.before(hoje)) //novaData.antes
		System.out.println("novaData é anterior a hoje");

		//metodos depreciados (voce pode, mas nao deve usar)
		long dataMilissegundos  = Date.parse("25/12/2015");
		Date data = new Date("25/12/2015");
		int tempoDias = data.getDay();
		int tempoHoras = data.getHours();
		
		
	
	}

}
