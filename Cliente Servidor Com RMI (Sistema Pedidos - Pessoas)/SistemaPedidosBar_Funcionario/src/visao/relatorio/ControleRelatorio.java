package visao.relatorio;

import java.util.ArrayList;


import TipoDado.Caixa;
import implementacao.MenuFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleRelatorio {
	@FXML
	Accordion AccMenu;

	@FXML
	TableView<Caixa> tblRel;

	@FXML
	TableColumn<Caixa, String> colDia;
	
	@FXML
	TableColumn<Caixa, Double> colVenda;

	@FXML
	Button btnGrafico;


	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colDia.setCellValueFactory(new PropertyValueFactory<>("Dia"));
		colVenda.setCellValueFactory(new PropertyValueFactory<>("Valor"));
		
		inserirListaDados();

		btnGrafico.setOnAction(e -> ControleGrafico.getInstance().grafico());

	}

	public void inserirListaDados() {
		
		ArrayList<Caixa> x = ControleGrafico.getInstance().DinheiroPorDia();
		
		
		ObservableList<Caixa> lista = FXCollections.observableArrayList();
		
		for (Caixa caixa : x) {
			lista.add(caixa);
		}

		tblRel.setItems(lista);
	}
}
