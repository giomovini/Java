package visao.visualizarConta;

import TipoDado.ItemPedido;

import implementacao.ClientePrincipal;
import implementacao.MenuCliente;
import implementacao.Remota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleVisualizarConta {

	@FXML
	Accordion AccMenu;

	@FXML
	TableView<ItemPedido> tblProd;

	@FXML
	TableColumn<ItemPedido, String> colNome;

	@FXML
	TableColumn<ItemPedido, Double> colPrecoU, colPrecoT;

	@FXML
	TableColumn<ItemPedido, Integer> colQtde;

	@FXML
	Label lblPrecoTotal;

	Double precoT = 0.0;

	@FXML
	public void initialize() {

		AccMenu.getPanes().addAll(MenuCliente.getInstance().criarMenuCliente());

		colNome.setCellValueFactory(new PropertyValueFactory<>("NomeProduto"));
		colPrecoU.setCellValueFactory(new PropertyValueFactory<>("PrecoUnid"));
		colQtde.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
		colPrecoT.setCellValueFactory(new PropertyValueFactory<>("Precototal"));

		inserirListaTabela();

	}

	public void inserirListaTabela() {

		ObservableList<ItemPedido> lista = Remota.getInstance().buscaItensPedidos();
		ObservableList<ItemPedido> listaN = FXCollections.observableArrayList();

		for (int i = 0; i < lista.size(); i++) {
			if (Integer.parseInt(lista.get(i).getId_Pedido()) == ClientePrincipal.ID_PEDIDO) {
				listaN.add(lista.get(i));
				precoT += lista.get(i).getPrecototal();
			}

		}

		if (precoT.toString().charAt(precoT.toString().length() - 2) == '.') {
			lblPrecoTotal.setText(precoT.toString().replace('.', ','));
			lblPrecoTotal.setText(lblPrecoTotal.getText() + "0");
		} else if (!precoT.toString().contains(".")) {
			lblPrecoTotal.setText(lblPrecoTotal.getText() + ",00");
		} else {
			lblPrecoTotal.setText(lblPrecoTotal.getText());
		}

		tblProd.setItems(listaN);
	}

}
