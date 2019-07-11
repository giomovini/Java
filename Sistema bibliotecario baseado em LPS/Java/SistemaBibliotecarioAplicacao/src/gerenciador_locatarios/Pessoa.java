package gerenciador_locatarios;

public abstract class Pessoa {
 
	private int id;
	private String Nome,Sexo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
}


