package visao.editarProduto;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import TipoDado.Produto;
import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleEditarProduto {
	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Produto> tblProd;

	@FXML
	TableColumn<Produto, String> colID, colNome, colDescricao, colValidade;

	@FXML
	TableColumn<Produto, Double> colPreco;

	@FXML
	TableColumn<Produto, Integer> colQtde;

	@FXML
	TextField txtNome, txtPreco, txtQuantidade;

	@FXML
	DatePicker dateValidade;

	@FXML
	TextArea txtDescricao;

	@FXML
	Button btnEditar;
	
	@FXML
	MenuButton menuTipo;

	private String IdLinha = "";

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("NOME"));
		colDescricao.setCellValueFactory(new PropertyValueFactory<>("DESCRICAO"));
		colValidade.setCellValueFactory(new PropertyValueFactory<>("VALIDADE"));
		colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		colQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		ObservableList<Produto> lista = Remota.getInstance().tabelaProduto("Editar");
		atualizarLista(lista);

		tblProd.setItems(lista);
		
		tblProd.setOnMouseClicked(e->{
			mostrarDados(tblProd.getSelectionModel().getSelectedItem());
		});

		btnEditar.setOnAction(e -> editar());
		
		MenuItem bebidas = new MenuItem("Bebida");
		MenuItem porcoes = new MenuItem("Porção");
		MenuItem doces = new MenuItem("Doce");
		MenuItem lanches = new MenuItem("Lanche");
		
		
		menuTipo.getItems().addAll(bebidas,porcoes,doces,lanches);
		
		bebidas.setOnAction(e->menuTipo.setText(bebidas.getText()));
		porcoes.setOnAction(e->menuTipo.setText(porcoes.getText()));
		doces.setOnAction(e->menuTipo.setText(doces.getText()));
		lanches.setOnAction(e->menuTipo.setText(lanches.getText()));

	}

	public void atualizarLista(ObservableList<Produto> lista) {

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getVALIDADE() != null) {
				String data = lista.get(i).getVALIDADE().substring(8, 10) + "/"
						+ lista.get(i).getVALIDADE().substring(5, 7) + "/" + lista.get(i).getVALIDADE().substring(0, 4);

				lista.get(i).setVALIDADE(data);
			}

		}

	}

	public void mostrarDados(Produto p) {
		IdLinha = p.getID();
		txtDescricao.setText(p.getDESCRICAO());
		txtNome.setText(p.getNOME());
		txtPreco.setText(p.getPreco().toString());
		txtQuantidade.setText(p.getQuantidade().toString());
		menuTipo.setText(p.getTIPO());

		if (p.getVALIDADE() != null) {
			int dia = Integer.parseInt(p.getVALIDADE().substring(0, 2));
			int mes = Integer.parseInt(p.getVALIDADE().substring(3, 5));
			int ano = Integer.parseInt(p.getVALIDADE().substring(6, 10));

			dateValidade.setValue(LocalDate.of(ano, mes, dia));
		} else {
			dateValidade.setValue(null);
		}

	}

	public void editar() {

		double preco = 0;
		int quantidade = 0;
		String data = null;

		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ser vazio!");
			return;
		}
		if (txtDescricao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo descrição não pode ser vazio!");
			return;
		}

		try {
			preco = Double.parseDouble(txtPreco.getText().replaceAll(",", "."));
			
			if(preco<0.0){
				JOptionPane.showMessageDialog(null, "O campo preco não tem valor coerente!");
				return;
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo preco não tem valor coerente!");
			return;
		}

		try {
			quantidade = Integer.parseInt(txtQuantidade.getText());
			if(quantidade<0){
				JOptionPane.showMessageDialog(null, "O campo quantidade não tem valor coerente!");
				return;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo quantidade não tem valor coerente!");
			return;
		}

		if (dateValidade.getValue() != null)
			data = dateValidade.getValue().toString();

		boolean b = Remota.getInstance().editarProduto(FuncPrincipal.CPF, IdLinha, txtNome.getText(),
				txtDescricao.getText(), preco, quantidade, data,menuTipo.getText());

		if (b) {
			JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");

			ObservableList<Produto> lista = Remota.getInstance().tabelaProduto("Editar");
			atualizarLista(lista);

			tblProd.setItems(lista);
		} else {
			JOptionPane.showMessageDialog(null, "Falha na edição!");
		}

	}

}
