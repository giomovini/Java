package visao.deletarProduto;

import javax.swing.JOptionPane;

import TipoDado.Produto;
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

public class ControleDeletarProduto {
	
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
	TableColumn<Produto, Button> colDeletar;

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
		colDeletar.setCellValueFactory(new PropertyValueFactory<>("btnEditarDeletar"));

		inserirListaTabela();

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

	public void inserirListaTabela() {

		ObservableList<Produto> lista = Remota.getInstance().tabelaProduto("Deletar");
		atualizarLista(lista);

		tblProd.setItems(lista);

		for (Produto p : lista) {
			p.getBtnEditarDeletar().setOnAction(e -> deletar(p));
		}
	}

	public void deletar(Produto p) {

		boolean b = Remota.getInstance().deletarProduto(FuncPrincipal.CPF, p.getID());

		if (b) {
			JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");

			inserirListaTabela();

		} else {
			JOptionPane.showMessageDialog(null, "Falha na deleção!");
		}

	}

}
