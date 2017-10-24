package colecoes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExemploList {

	public static void main(String[] args) {
		
		List<String> frutas  = new ArrayList<>();
		frutas.add("banana");
		frutas.add("banana");
		frutas.add("pera");
		frutas.add("maça");
		frutas.add("uva");
		frutas.add("laranja");
		
		for (int i = 0; i < frutas.size(); i++) {
			System.out.println(frutas.get(i));
		}
		
		System.out.println("---- foreach");
		
		for (String f : frutas) {
			System.out.println(f);
		}
		System.out.println("---- iterator");
		
		Iterator<String> iterator = frutas.iterator();
		
		while(iterator.hasNext())
		System.out.println(iterator.next());
		
		System.out.println("---- com programacao funcional");
		
		frutas.forEach(f -> System.out.println(f));
		
		
	}
}
