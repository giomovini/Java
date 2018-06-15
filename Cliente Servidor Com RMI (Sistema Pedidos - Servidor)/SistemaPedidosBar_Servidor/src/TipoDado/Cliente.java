package TipoDado;

import javafx.scene.control.Button;

public class Cliente implements IFuncionario{
	
	private static final long serialVersionUID = 1280592693118087216L;
	
	String Id, Nome, CPF, Senha, RG,Telefone;
	Button btnEditarDeletar;
	
	public Button getBtnEditarDeletar() {
		return btnEditarDeletar;
	}

	public void setBtnEditarDeletar(Button btnEditarDeletar) {
		this.btnEditarDeletar = btnEditarDeletar;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}



}
