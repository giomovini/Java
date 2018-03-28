package datas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatandoDatas {

	public static void main(String[] args) throws ParseException {

		Date agora = new Date();
		// formatação de datas com SimpleDateFormat
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yy");
		String data = formatador.format(agora);
		System.out.println("Data: " + data);

		// formatacao de hora
		formatador.applyPattern("hh:mm:ss");
		String hora = formatador.format(agora);
		System.out.println("Hora = " + hora);

		formatador.applyPattern("dd/MM/yy HH:mm:ss");
		Date novaData = formatador.parse("02/07/2016 14:39:15");
		System.out.println(novaData);

		
		//data por extenso: Ponta grossa 02 de julho de 2016
		formatador.applyPattern("dd");
		String dia = formatador.format(agora);
		formatador.applyPattern("MMMMM");
		String mes = formatador.format(agora);
		formatador.applyPattern("yyyy");
		String ano = formatador.format(agora);
		
		System.out.printf("Ponta Grossa, %s de %s de %s", dia, mes, ano);
		
		// formatacao de datas com Date format
		
		//formatador de datas para o brasil
		
		Locale brasil = new Locale("pt","BR"); //protuguês, brasil
		DateFormat forBrasil = DateFormat.getDateInstance(DateFormat.LONG, brasil);
		System.out.printf("\nPonta Grossa, %s", forBrasil.format(agora));
		
		DateFormat forFranca = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
		System.out.printf("\nParis, %s", forFranca.format(agora));
		
		DateFormat forEUA = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
		System.out.printf("\nNew York, %s", forEUA.format(agora));
		
		
		
	}

}
