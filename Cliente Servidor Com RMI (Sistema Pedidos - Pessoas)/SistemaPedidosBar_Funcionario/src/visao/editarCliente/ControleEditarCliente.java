package visao.editarCliente;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleEditarCliente {
	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Cliente> tblCliente;

	@FXML
	TableColumn<Cliente, String> colNome, colRG, colCPF, colTelefone, colSenha;

	@FXML
	TextField txtNome, txtRG, txtCPF, txtSenha, txtTelefone;

	@FXML
	Button btnEditar;

	private String cpfLinha = "";

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		colSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));

		inserirDadosTabela();

		btnEditar.setOnAction(e -> editar());
		
	}


	public void inserirDadosTabela() {

		ObservableList<Cliente> lista = Remota.getInstance().tabelaCliente("Editar");

		tblCliente.setItems(lista);

		tblCliente.setOnMouseClicked(e -> {
			mostrarDados(tblCliente.getSelectionModel().getSelectedItem());
		});

	}

	public void mostrarDados(Cliente f) {
		cpfLinha = f.getCPF();
		txtCPF.setText(f.getCPF());
		txtNome.setText(f.getNome());
		txtRG.setText(f.getRG());
		txtSenha.setText(f.getSenha());
		txtTelefone.setText(f.getTelefone());
	}

	boolean validar() {

		boolean b;

		if (cpfLinha.equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela!");
			return false;
		}

		if (!cpfLinha.equals(txtCPF.getText())) {
			b = Remota.getInstance().existeCliente(txtCPF.getText());

			if (b == true) {
				JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
				return false;
			}

		}

		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ser vazio!");
			return false;
		}
		if (txtRG.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo RG não pode ser vazio!");
			return false;
		}
		if (txtCPF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo CPF não pode ser vazio!");
			return false;
		}
		if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo Senha não pode ser vazio!");
			return false;
		}
		if (txtTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo Telefone não pode ser vazio!");
			return false;
		}

		return true;

	}

	public void editar() {

		if (validar()) {
			boolean b = Remota.getInstance().editarCliente(FuncPrincipal.CPF, cpfLinha, txtNome.getText(),
					txtRG.getText(), txtCPF.getText(), txtSenha.getText(), txtTelefone.getText());

			if (b) {
				JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Falha da edição!");
			}

		}
		inserirDadosTabela();

	}
}
