package atividadeCapitais;


class Cidade
{

	int id;
	int distanciaAteCidade;
	boolean visitada;
	
	public int getDistanciaAteCidade() {
		return distanciaAteCidade;
	}

	public void setDistanciaAteCidade(int distanciaAteCidade) {
		this.distanciaAteCidade = distanciaAteCidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public Cidade(int id,int distanciaAteCidade) {
		super();
		this.id = id;
		this.distanciaAteCidade = distanciaAteCidade;
		this.visitada = false;
	}
	
}