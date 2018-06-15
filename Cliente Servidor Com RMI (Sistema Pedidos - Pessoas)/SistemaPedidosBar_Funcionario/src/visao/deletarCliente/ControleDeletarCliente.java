package visao.deletarCliente;

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
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDeletarCliente {

	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Cliente> tblCliente;

	@FXML
	TableColumn<Cliente, String> colNome, colRG, colCPF, colTelefone, colSenha;

	@FXML
	TableColumn<Cliente, Button> colDeletar;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		colSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));
		colDeletar.setCellValueFactory(new PropertyValueFactory<>("btnEditarDeletar"));

		inserirDadosTabela();

	}
	
	public void inserirDadosTabela(){
		
		ObservableList<Cliente> lista = Remota.getInstance().tabelaCliente("Deletar");

		tblCliente.setItems(lista);

		for (Cliente cli : lista) {
			cli.getBtnEditarDeletar().setOnAction(e -> deletar(cli));
		}
		
		
	}

	public void deletar(Cliente c) {

		boolean b = Remota.getInstance().deletarCliente(FuncPrincipal.CPF, c.getId());

		if (b) {
			JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro na deleção");
		}

		inserirDadosTabela();
	}
}
