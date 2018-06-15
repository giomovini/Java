package TipoDado;

import javafx.scene.control.Button;

public class Funcionario implements IFuncionario{

	private static final long serialVersionUID = -3977777935194874438L;

	String Id, Nome, CPF, Senha, RG, Setor;
	
	Button btnEditarDeletar;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}


	public Button getBtnEditarDeletar() {
		return btnEditarDeletar;
	}

	public void setBtnEditarDeletar(Button btnEditarDeletar) {
		this.btnEditarDeletar = btnEditarDeletar;
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

	public String getSetor() {
		return Setor;
	}

	public void setSetor(String setor) {
		Setor = setor;
	}

}
