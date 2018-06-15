package visao.fecharConta;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import TipoDado.Conta;
import TipoDado.ItemPedido;
import TipoDado.Pedido;
import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleFecharConta {

	@FXML
	Accordion AccMenu;

	@FXML
	AnchorPane AncorPrincipal;

	@FXML
	TableView<Conta> tblConta;

	@FXML
	TableColumn<Conta, String> colNome, colCPF, colMesa, colValor;

	@FXML
	Button btnFinalizar;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colMesa.setCellValueFactory(new PropertyValueFactory<>("mesa"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

		inserirDadosTabela();

		btnFinalizar.setOnAction(e->finalizarConta());

	}
	
	public void finalizarConta() {

		
		Remota.getInstance().fecharConta(FuncPrincipal.CPF, tblConta.getSelectionModel().getSelectedItem().getId_pedido(),tblConta.getSelectionModel().getSelectedItem().getCpf());
		JOptionPane.showMessageDialog(null, "Conta finalizada com sucesso!");
		inserirDadosTabela();
		
	}

	public void inserirDadosTabela() {

		ArrayList<Pedido> lista = Remota.getInstance().buscaTodosPedidos();
		ObservableList<Conta> listaCompleta = FXCollections.observableArrayList();

		ObservableList<ItemPedido> listaItens = Remota.getInstance().buscaItensPedidos();

		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getFinalizado() == 0) {

				Conta c = new Conta();
				Cliente cl = Remota.getInstance().buscaClienteID(lista.get(i).getId_cliente().toString());

				c.setMesa(lista.get(i).getMesa().toString());
				c.setId_pedido(lista.get(i).getId_pedido().toString());
				c.setNome(cl.getNome());
				c.setCpf(cl.getCPF());
				c.setValor("0");

				listaCompleta.add(c);
			}
		}

		for (int i = 0; i < listaCompleta.size(); i++) {

			for (int j = 0; j < listaItens.size(); j++) {

				if (listaItens.get(j).getId_Pedido().equals(listaCompleta.get(i).getId_pedido())) {

					double v = Double.parseDouble(listaCompleta.get(i).getValor());
					v = v + listaItens.get(j).getPrecototal();
					listaCompleta.get(i).setValor(String.valueOf(v));
				}

			}

		}

		tblConta.setItems(listaCompleta);

	}

}
