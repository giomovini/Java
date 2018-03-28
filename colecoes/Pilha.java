import java.util.ArrayList;
import java.util.Stack;

public class Pilha {

	public static void main(String[] args) {
		
		
		Stack<String>pilha = new Stack<>();
		
		pilha.push("java");
		pilha.push("php");
		pilha.push("shell");
		pilha.push("c++");
		pilha.push("delphi");
		pilha.push(".net");
		
		while (!pilha.isEmpty()) {
			System.out.println(pilha.pop());
			
		}
		
		//consulta sem remover
		System.out.println(pilha.peek());
		
	}

}
