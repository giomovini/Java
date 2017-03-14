
public class ContadorThread extends Thread{
	
	public static void main(String[] args) {
		new ContadorThread().start();
		new ContadorThread().contador1();
		// chama os metodos 
		// os dois contadores são executados simultaneamente

	}
	
	
	
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println("Contador 2 ==== "+i);
		}
	}
	
	
	public void contador1(){
		
		for(int i=0;i<100;i++){
			System.out.println("Contador 1 ==== "+i);
		}
		
	}
	
	
}
