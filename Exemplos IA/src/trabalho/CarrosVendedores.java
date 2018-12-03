package trabalho;

import java.util.ArrayList;

public class CarrosVendedores {
	
	
	private static CarrosVendedores uniqueInstance;
	 
    private CarrosVendedores() {
    }
 
    //singleton
    public static synchronized CarrosVendedores getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new CarrosVendedores();
        return uniqueInstance;
    }
	
    // verifica qual eh o vendedor para atribuir uma lista diferente 
    public ArrayList<Carro> carrosVendedor(String vendedor){
    	if(vendedor.equals("Vendedor2")) {
    		return carrosVendedor2();
    	}else {
    		return carrosVendedor1();
    	}
    	
    }
	
    
    /*
     * Adiciona carros em uma lista e envia ao vendedor
     */
    
	public ArrayList<Carro> carrosVendedor1(){
		
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(2017, 173, "BRANCO", "HONDA CIVIC TOURING", "AUTO", 108000.00));
		carros.add(new Carro(2012, 80, "CINZA", "FIAT IDEA", "MANUAL", 29900.00));
		carros.add(new Carro(2014, 167, "CINZA", "FORD FUSION", "AUTO", 65900.00));
		carros.add(new Carro(2004, 109, "BRANCO", "CHEVROLET CORSA SEDAN", "MANUAL", 14490.00));
		carros.add(new Carro(2014, 136, "BRANCO", "BMW SERIE 1", "AUTO", 74900.00));
		carros.add(new Carro(2004, 109, "PRETO", "CHEVROLET MONTANA", "MANUAL", 22900.00));
		
		return carros;
	}

	/*
     * Adiciona carros em uma lista e envia ao vendedor
     */
	
	public ArrayList<Carro> carrosVendedor2(){
		
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(2012, 108, "BRANCO", "JAC J3", "MANUAL", 19900.00));
		carros.add(new Carro(2014, 72, "CINZA", "VOLKSWAGEN GOL", "MANUAL", 24990.00));
		carros.add(new Carro(2018, 109, "CINZA", "ARGO DRIVE", "MANUAL", 51500.00));
		carros.add(new Carro(2015, 122, "BRANCO", "HYUNDAI HB20", "AUTO", 46900.00));
		carros.add(new Carro(2016, 143, "PRETO", "TOYOTA COROLLA", "AUTO", 74499.00));
		carros.add(new Carro(2017, 135, "CINZA", "FIAT TORO", "AUTO", 70990.00));
		
		
		return carros;
	}
	
	
}
