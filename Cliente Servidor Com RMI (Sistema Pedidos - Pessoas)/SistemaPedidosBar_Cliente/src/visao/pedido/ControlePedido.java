package visao.pedido;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import TipoDado.Produto;
import implementacao.ClientePrincipal;
import implementacao.MenuCliente;
import implementacao.Remota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControlePedido {

	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Produto> tblProd;

	@FXML
	TableColumn<Produto, String> colNome, colDesc;

	@FXML
	TableColumn<Produto, Double> colPreco;

	@FXML
	TableColumn<Produto, Integer> colQtde;

	@FXML
	TextField txtQuantidade;

	@FXML
	Label lblProduto;

	@FXML
	Button btnPedido, btnBebida, btnPorcao, btnDoce, btnLanche;

	Produto p = null;

	@FXML
	public void initialize() {

		AccMenu.getPanes().addAll(MenuCliente.getInstance().criarMenuCliente());

		colNome.setCellValueFactory(new PropertyValueFactory<>("NOME"));
		colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		colQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colDesc.setCellValueFactory(new PropertyValueFactory<>("DESCRICAO"));

		inserirListaTabela();

		tblProd.setOnMouseClicked(e -> {
			p = tblProd.getSelectionModel().getSelectedItem();
			if (p != null)
				lblProduto.setText(p.getNOME());
		});

		btnBebida.setOnAction(e -> aplicaFiltro(btnBebida.getText()));
		btnDoce.setOnAction(e -> aplicaFiltro(btnDoce.getText()));
		btnLanche.setOnAction(e -> aplicaFiltro(btnLanche.getText()));
		btnPorcao.setOnAction(e -> aplicaFiltro(btnPorcao.getText()));

		btnPedido.setOnAction(e -> inserirItemPedido());

	}

	public void aplicaFiltro(String filtro) {

		ObservableList<Produto> lista = Remota.getInstance().listaProdutos();
		ObservableList<Produto> listaNova = FXCollections.observableArrayList();

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getTIPO().equals(filtro)) {
				listaNova.add(lista.get(i));
			}

		}

		tblProd.setItems(validade(listaNova));

	}

	public void inserirItemPedido() {
		try {
			if (Integer.parseInt(txtQuantidade.getText()) < 1) {
				JOptionPane.showMessageDialog(null, "Valor do campo quantidade deve ser maior que 0!");
				return;
			} else {
				Cliente c = Remota.getInstance().buscaCliente(ClientePrincipal.CPF);
				int id_pedido = Remota.getInstance().id_pedido(c.getId());

				boolean b = Remota.getInstance().inserirItemPedidoDescontarProduto(p.getID(), String.valueOf(id_pedido),
						Integer.parseInt(txtQuantidade.getText()));

				if(b)
					JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso, aguarde");

				inserirListaTabela();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor do campo quantidade invalido!");
			return;
		}

	}

	public static ObservableList<Produto> validade(ObservableList<Produto> lista) {

		ObservableList<Produto> novaLista = FXCollections.observableArrayList();

		for (int i = 0; i < lista.size(); i++) {

			Calendar now = GregorianCalendar.getInstance();
			Calendar c = GregorianCalendar.getInstance();

			int dia, mes, ano;
			ano = Integer.parseInt(lista.get(i).getVALIDADE().substring(0, 4));
			mes = Integer.parseInt(lista.get(i).getVALIDADE().substring(5, 7));
			dia = Integer.parseInt(lista.get(i).getVALIDADE().substring(8, 10));
			c.set(ano, mes, dia);

			if (c.compareTo(now) > 0) {
				novaLista.add(lista.get(i));
			} else {
				System.out.println("Vencido-" + lista.get(i).getNOME());
			}
		}
		return novaLista;
	}

	public void inserirListaTabela() {

		ObservableList<Produto> lista = Remota.getInstance().listaProdutos();
		tblProd.setItems(validade(lista));
	}

}
