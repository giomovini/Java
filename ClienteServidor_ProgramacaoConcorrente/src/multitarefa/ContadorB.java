package multitarefa;

public class ContadorB  extends Thread{
	
	public void rodar(){
		for(int i=0;i<100;i++){
			System.out.println("contador B:"+i);
		}
		
	}
	
	@Override
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println("contador B:"+i);
		}
	}

}
