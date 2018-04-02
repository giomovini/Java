package javafx.painter;

public enum TipoCor {
	
	NORMAL("Cor normal"),
	PSICODELICA("Cor PSICODELICA"),
	RANDOMICA("Cor RANDOMICA");
	
	private String nome;
	
	TipoCor(String nome){
		this.nome = nome;
		
	}
	@Override
	public String toString(){
		return nome;
	}
	
	public TipoCor proxima(){
		return (this == NORMAL ? RANDOMICA : this == RANDOMICA ? PSICODELICA : NORMAL);
	}

}
