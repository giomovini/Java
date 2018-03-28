import java.util.LinkedList;
import java.util.Queue;

public class ExemploFila {
	
	public static void main(String[] args) {
		
		Queue<String> fila = new LinkedList<>();
		
		fila.offer("1");
		fila.add("2");
		fila.add("3");
		
		fila.poll();
		
		System.out.println(fila);

	}

}
