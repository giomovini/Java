package datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendario {

	private static Calendar calendario = Calendar.getInstance();
	private static String[] dias = { "DOMINGO", "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SABADO" };
	private static String[] meses = { "JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO", "AGOSTO","SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO" };

	public static void main(String[] args) throws ParseException {

		System.out.println(Calendar.DAY_OF_MONTH); //5
		System.out.println(Calendar.MONTH); //2
		System.out.println(Calendar.YEAR); //1
		System.out.println(Calendar.HOUR_OF_DAY); //11
		System.out.println(Calendar.MINUTE); //12
		System.out.println(Calendar.SECOND); //13
		System.out.println(Calendar.DAY_OF_WEEK);//7
		
		int diaMes = calendario.get(5);
		System.out.println("\nDia do mês: "+diaMes);
		
		int numMes = calendario.get(2);
		System.out.println("\nmês: "+numMes);
		
		int diaDaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		System.out.println("\ndia da semana: "+dias[diaDaSemana-1]);
		
		diaDoEvento("Copa do mundo", "12/06/2014");
		diaDoEvento("Olimpiadas", "05/08/2016");
		diaDoEvento("Independencia do Brasil", "07/09/1822");
		diaDoEvento("Descobrimento do Brasil", "22/04/1500");
		diaDoEvento("Viagem a lua", "20/07/1969");
		
		
		
	}
	
	private static void diaDoEvento(String msg, String data) throws ParseException{
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date evento =  formatador.parse(data);
		calendario.setTime(evento);
		int indice = calendario.get(Calendar.DAY_OF_WEEK)-1;
		String diaDaSemana = dias[indice];
		System.out.printf("%s = %s \n",msg,diaDaSemana);
		
	}
	
	

}
