package teste.locatario;

import java.time.LocalDate;

import gerenciador_locatarios.Locatario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import teste.ControllerTelaInicial;
import teste.Mascaras;
import teste.Tela;

public class ControllerTelaCadastroLocatario {

	@FXML
	TextField Nome, CPF, Telefone, Email, Endereco, Estado, Cidade;

	@FXML
	DatePicker Nascimento;

	@FXML
	RadioButton Masculino, Feminino;

	@FXML
	Button Salvar, Voltar;

	String sexo = "";

	@FXML
	public void initialize() {

		Masculino.setOnAction(e -> {
			Feminino.setSelected(false);
			sexo = "M";
		});
		Feminino.setOnAction(e -> {
			Masculino.setSelected(false);
			sexo = "F";
		});

		Voltar.setOnAction(e -> {
			Tela.getInstance().getPalcoSobreposto().close();
		});

		CPF.setText("");
		Mascaras.mascaraCPF(CPF);
		Telefone.setText("");
		Mascaras.mascaraTelefone(Telefone);
		Mascaras.maxField(Estado, 2);

		if (ControllerTelaInicial.editar) {
			atribuiDados(ControllerTelaInicial.Loc);
		}

		Salvar.setOnAction(e -> {

			Locatario locac = new Locatario();
			locac.setCPF(CPF.getText());
			locac.setEmail(Email.getText());
			if (Nascimento != null && Nascimento.getValue() != null)
				locac.setNascimento(Nascimento.getValue().toString());
			locac.setSexo(sexo);
			locac.setTelefone(Telefone.getText());
			locac.setNome(Nome.getText());

			locac.setCidade(Cidade.getText());
			locac.setEstado(Estado.getText());
			locac.setEndereco(Endereco.getText());

			boolean sucess = false;
			if (ControllerTelaInicial.editar) {
				locac.setId(ControllerTelaInicial.Loc.getId());
				sucess = locac.editarLocatario(locac);
			} else {
				sucess = locac.cadastrarLocatario(locac);
			}
			if (sucess) {
				ControllerTelaInicial.atualiza = true;
				Tela.getInstance().getPalcoSobreposto().close();
			}

		});

	}

	public void atribuiDados(Locatario Loc) {

		Nome.setText(Loc.getNome());
		CPF.setText(Loc.getCPF());
		Telefone.setText(Loc.getTelefone());
		Endereco.setText(Loc.getEndereco());
		Estado.setText(Loc.getEstado());
		Cidade.setText(Loc.getCidade());
		Nascimento.setValue(LocalDate.parse(Loc.getNascimento()));
		Email.setText(Loc.getEmail());

		if (Loc.getSexo().equals("M")) {
			Masculino.setSelected(true);
		} else {
			Feminino.setSelected(true);
		}

	}

}
