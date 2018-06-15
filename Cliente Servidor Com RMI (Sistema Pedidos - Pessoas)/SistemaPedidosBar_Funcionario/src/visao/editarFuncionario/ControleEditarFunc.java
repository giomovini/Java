package visao.editarFuncionario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import TipoDado.Funcionario;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleEditarFunc {
	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Funcionario> tblFunc;

	@FXML
	TableColumn<Funcionario, String> colNome, colRG, colCPF, colSetor, colSenha;

	@FXML
	TextField txtNome, txtRG, txtCPF, txtSenha, txtCPFAntigo;

	@FXML
	MenuButton MenuSetor;

	@FXML
	Button btnEditar;

	List<MenuItem> ItensMenu = new ArrayList<>();

	private String cpfLinha = "";

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		colSetor.setCellValueFactory(new PropertyValueFactory<>("Setor"));
		colSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));

		ItensMenu.add(new MenuItem("Atendente"));
		ItensMenu.add(new MenuItem("Administrador"));

		MenuSetor.getItems().addAll(ItensMenu);

		for (MenuItem menuItem : ItensMenu) {
			menuItem.setOnAction(e -> itemSelecionado(menuItem));
		}
		inserirListaDados();

		btnEditar.setOnAction(e -> editar());

	}

	public void inserirListaDados() {
		ObservableList<Funcionario> lista = Remota.getInstance().tabelaFuncionario("Editar");

		tblFunc.setItems(lista);

		tblFunc.setOnMouseClicked(e -> {
			mostrarDados(tblFunc.getSelectionModel().getSelectedItem());
		});

	}

	private void itemSelecionado(MenuItem menuItem) {
		MenuSetor.setText(menuItem.getText());
	}

	public void mostrarDados(Funcionario f) {
		if (f != null) {
			cpfLinha = f.getCPF();
			txtCPF.setText(f.getCPF());
			txtNome.setText(f.getNome());
			txtRG.setText(f.getRG());
			txtSenha.setText(f.getSenha());

			MenuSetor.setText(f.getSetor());
		}
	}

	boolean validar() {

		boolean b;

		if (!cpfLinha.equals(txtCPF.getText())) {
			b = Remota.getInstance().existeCpfFuncionario(txtCPF.getText());

			if (b == true) {
				JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
				return false;
			}

		}

		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ser vazio!");
			return false;
		}
		if (MenuSetor.getText().equals("SELECIONE")) {
			JOptionPane.showMessageDialog(null, "Selecione algum setor!");
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
		if (cpfLinha.equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um funcionário na tabela!");
			return false;
		}

		return true;

	}

	public void editar() {

		if (validar()) {
			boolean b = Remota.getInstance().editarFuncionario(cpfLinha, txtNome.getText(), txtRG.getText(),
					txtCPF.getText(), txtSenha.getText(), MenuSetor.getText());

			if (b) {
				JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso!");

				inserirListaDados();

			} else {
				JOptionPane.showMessageDialog(null, "Falha da edição!");
			}

		}
	}
}
