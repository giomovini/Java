package visao.deletarFuncionario;

import javax.swing.JOptionPane;

import TipoDado.Funcionario;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDeletarFunc {

	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Funcionario> tblFunc;

	@FXML
	TableColumn<Funcionario, String> colNome, colRG, colCPF, colSetor, colSenha;

	@FXML
	TableColumn<Funcionario, Button> colDeletar;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		colSetor.setCellValueFactory(new PropertyValueFactory<>("Setor"));
		colSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));
		colDeletar.setCellValueFactory(new PropertyValueFactory<>("btnEditarDeletar"));

		
		inserirListaDados();
	}

	public void inserirListaDados() {

		ObservableList<Funcionario> lista = Remota.getInstance().tabelaFuncionario("Deletar");

		tblFunc.setItems(lista);

		for (Funcionario funcionario : lista) {
			funcionario.getBtnEditarDeletar().setOnAction(e -> deletar(funcionario));
		}

	}

	public void deletar(Funcionario f) {

		boolean b = Remota.getInstance().deletarFuncionario(f.getId());

		if (b) {
			JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro na deleção");
		}

		inserirListaDados();
	}
}
