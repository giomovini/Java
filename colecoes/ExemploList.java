import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ExemploList {

	public static void main(String[] args) {

		List<String> frutas = new ArrayList<>();
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

		while (iterator.hasNext())
			System.out.println(iterator.next());

		System.out.println("---- com programacao funcional");

		frutas.add(0, "Jabuticaba");
		frutas.remove(0);

		frutas.forEach(f -> System.out.print(f+" "));

		List<String> frutascontidas = new ArrayList<>();
		frutascontidas.add("uva");
		frutascontidas.add("maça");
		frutascontidas.add("pera");

		System.out.println(frutas.containsAll(frutascontidas));

		Collections.sort(frutas);
		System.out.println();

		frutas.forEach(f -> System.out.print(f+" "));

		Collections.sort(frutas, new Comparator<String>() {

			@Override
			public int compare(String f1, String f2) {

				return f1.compareTo(f2);
			}
		});

		System.out.println();

		frutas.forEach(f -> System.out.print(f+" "));

		List<String> verduras = new ArrayList<>();

		verduras.add("alface");
		verduras.add("repolho");
		verduras.add("couve");

		List<String> compras = new ArrayList<>();

		compras.addAll(frutas);
		compras.addAll(verduras);

		System.out.println();

		compras.forEach(f -> System.out.print(f+" "));

		List<String> naoComprar = new ArrayList<>();
		naoComprar.add("uva");
		naoComprar.add("couve");

		compras.removeAll(naoComprar);

		System.out.println();

		compras.forEach(f -> System.out.print(f+" "));
		
		List<String> intersecao = new ArrayList<>();
		intersecao.add("alface");
		intersecao.add("repolho");
		intersecao.add("melancia");
		intersecao.add("tomate");
		intersecao.add("carne");
		
		compras.retainAll(intersecao);
		
		
		
		

	}
}
