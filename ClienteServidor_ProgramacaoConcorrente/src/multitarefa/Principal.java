package multitarefa;

public class Principal {

	public static void main(String[] args) {
		ContadorA a = new ContadorA();
		ContadorB b = new ContadorB();
		a.start();
		b.start();

	}

}
