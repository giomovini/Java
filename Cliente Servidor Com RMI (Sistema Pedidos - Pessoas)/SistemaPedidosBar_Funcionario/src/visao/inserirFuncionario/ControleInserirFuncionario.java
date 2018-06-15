package visao.inserirFuncionario;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControleInserirFuncionario {

	@FXML
	Label lblTitulo, lblStatusSistema;

	@FXML
	Accordion AccMenu;

	@FXML
	AnchorPane AncorMenu, AncorPrincipal;

	@FXML
	TextField txtNome, txtCPF, txtRG, txtSenha;

	@FXML
	MenuButton MenuSetor;

	@FXML
	Button btnInserir;

	List<MenuItem> ItensMenu = new ArrayList<>();

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		btnInserir.setOnAction(e -> inserirDados());

		ItensMenu.add(new MenuItem("Atendente"));
		ItensMenu.add(new MenuItem("Administrador"));

		MenuSetor.getItems().addAll(ItensMenu);

		for (MenuItem menuItem : ItensMenu) {
			menuItem.setOnAction(e -> itemSelecionado(menuItem));
		}

	}

	private void itemSelecionado(MenuItem menuItem) {
		MenuSetor.setText(menuItem.getText());
	}

	// FUNCAO COM ERRO
	private void inserirDados() {

		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!");
			return;
		}
		if (txtCPF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo CPF não pode ficar em branco!");
			return;
		}
		if (txtRG.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo RG não pode ficar em branco!");
			return;
		}
		if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo senha não pode ficar em branco!");
			return;
		}
		if (MenuSetor.getText().equals("SELECIONE")) {
			JOptionPane.showMessageDialog(null, "Selecione algum item no setor!");
			return;
		}

		// verifica se o cpf ja existe
		Remota.getInstance().inserirFuncionario(txtCPF.getText(), txtNome.getText(), txtRG.getText(), MenuSetor.getText(), txtSenha.getText());
	}

}
