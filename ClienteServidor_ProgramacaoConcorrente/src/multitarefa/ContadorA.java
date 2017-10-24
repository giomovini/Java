package multitarefa;

public class ContadorA extends Thread{
	
	public void rodar(){
		for(int i=0;i<100;i++){
			System.out.println("contador A:"+i);
		}
		
	}
	
	@Override
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println("contador A:"+i);
		}
	}

}
