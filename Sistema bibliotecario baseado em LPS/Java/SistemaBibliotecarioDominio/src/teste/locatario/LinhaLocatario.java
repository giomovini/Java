package teste.locatario;

import javafx.scene.control.Button;

public class LinhaLocatario {
	
	Button Endereco;
	gerenciador_locatarios.Locatario Loc;
	String Email, Autor, Telefone,CPF, Idade,Logradouro,Numero,Cidade,Estado,CEP,Complemento,Locatario,Sexo;
	


	public LinhaLocatario() {
		Endereco = new Button("Endereco");
		Endereco.setPrefWidth(80);
	}

	public gerenciador_locatarios.Locatario getLoc() {
		return Loc;
	}

	public void setLoc(gerenciador_locatarios.Locatario loc) {
		Loc = loc;
	}


	public Button getEndereco() {
		return Endereco;
	}

	public void setEndereco(Button endereco) {
		Endereco = endereco;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getIdade() {
		return Idade;
	}

	public void setIdade(String idade) {
		Idade = idade;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getLocatario() {
		return Locatario;
	}

	public void setLocatario(String Locatario) {
		this.Locatario = Locatario;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}


	
	
	
	
}
