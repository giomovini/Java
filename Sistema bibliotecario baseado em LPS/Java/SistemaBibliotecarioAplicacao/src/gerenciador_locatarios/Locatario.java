package gerenciador_locatarios;

import java.util.List;

import javax.swing.JOptionPane;

import aplicacao.impressao_carteirinha.ImpressaoCarteirinha;
import teste.locacao.TabelaLocacao;
import teste.locatario.TabelaLocatario;

public class Locatario extends Pessoa implements IManterLocatario{

	String Email, Telefone, CPF, Nascimento, Cidade, Estado, Endereco;
	ImpressaoCarteirinha impressaoCarteirinha;

	
	public boolean cadastrarLocatario(Locatario Loc) {
		boolean sucess = Loc.validarLocatario(Loc);
		if (sucess) {
			sucess = TabelaLocatario.getInstance().salvarLocatario(Loc);
		}

		JOptionPane.showMessageDialog(null, sucess ? "Locatário salvo com sucesso!"
				: "Não foi possível salvar os dados, confira as informações e tente novamente!");
		return sucess;
	}

	public boolean editarLocatario(Locatario Loc) {
		boolean sucess = Loc.validarLocatario(Loc);
		if (sucess) {
			sucess = TabelaLocatario.getInstance().editarLocatario(Loc);
		}

		JOptionPane.showMessageDialog(null, sucess ? "Locatário salvo com sucesso!"
				: "Não foi possível salvar os dados, confira as informações e tente novamente!");
		return sucess;
	}

	public boolean deletarLocatario(Locatario Loc) {
		boolean sucess = true;
		if (TabelaLocacao.getInstance().buscarLocacoes(Loc.getNome(), "", "T").size() > 0) {
			sucess = false;
		} else {
			sucess = TabelaLocatario.getInstance().deletarLocatario(Loc);
		}

		JOptionPane.showMessageDialog(null, sucess ? "Registro deletado com sucesso!"
				: "Não foi possivel realizar a deleção do locatário\nVerifique a existência de uma locação associada com este locatário!");
		return sucess;

	}

	public boolean validarLocatario(Locatario Loc) {

		if (Loc.getCPF().equals("") || Loc.getNome().equals("") || Loc.getEmail().equals("")
				|| Loc.getTelefone().equals("") || Loc.getNascimento().equals("") || Loc.getEndereco().equals("")
				|| Loc.getCidade().equals("") || Loc.getEstado().equals("")) {
			return false;
		}

		return true;

	}

	public List<Locatario> buscarLocatarios() {
		return TabelaLocatario.getInstance().buscarLocatarios();
	}

	public List<Locatario> buscarLocatarios(String Nome) {
		return TabelaLocatario.getInstance().buscarLocatarios(Nome);
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

	public String getNascimento() {
		return Nascimento;
	}

	public void setNascimento(String nascimento) {
		Nascimento = nascimento;
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

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public ImpressaoCarteirinha getImpressaoCarteirinha() {
		return impressaoCarteirinha;
	}

	public void setImpressaoCarteirinha(ImpressaoCarteirinha impressaoCarteirinha) {
		this.impressaoCarteirinha = impressaoCarteirinha;
	}

}
