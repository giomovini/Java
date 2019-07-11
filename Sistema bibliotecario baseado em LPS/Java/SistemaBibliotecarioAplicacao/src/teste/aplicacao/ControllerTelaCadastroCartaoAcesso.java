package teste.aplicacao;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import aplicacao.cartao_acesso.CartaoAcesso;
import gerenciador_locatarios.Locatario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import teste.ControllerTelaInicial;
import teste.Tela;
import teste.locatario.LinhaLocatario;

public class ControllerTelaCadastroCartaoAcesso {


	@FXML
	TableView<LinhaLocatario> TabLocatario;
	
	
	@FXML
	TableColumn<LinhaLocatario, String>col_locatario,col_cpf;

	@FXML
	TextField txt_locatario,txt_CodBarras;
	
	@FXML
	DatePicker Validade;
	
	@FXML 
	Button Voltar,Salvar;
	
	
	@FXML
	public void initialize() {
		col_locatario.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Locatario"));
		col_cpf.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("CPF"));
		converteListaLocatario();
		
		txt_locatario.setOnKeyReleased(e -> {
			if (txt_locatario.getText().equals("")) {
				converteListaLocatario();
			} else {
				filtraListaLocatario();
			}
		});
		
		Salvar.setOnAction(e->{
			Locatario locatario = null;
			
			
			if(TabLocatario.getSelectionModel().getSelectedItem() != null) {
				locatario = TabLocatario.getSelectionModel().getSelectedItem().getLoc();
			}else {
				JOptionPane.showMessageDialog(null, "Selecione um registro na tabela de Locatários!");
			}
			
			if(locatario != null) {
				CartaoAcesso cartao = new CartaoAcesso();
				cartao.setLocatario_(locatario);
				cartao.setCodBarras(txt_CodBarras.getText());
				cartao.setValidade(new Date());
				
				if(Validade.getValue() != null) {
					Date data = Date.from(Validade.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					cartao.setValidade(data);
				}
				cartao.cadastrarCartao(cartao);
				ControllerTelaInicial.atualiza  = true;
			}
			
		});
		
		Voltar.setOnAction(e -> {
			Tela.getInstance().getPalcoSobreposto().close();
		});

	}
	
	
	
	public void converteListaLocatario() {
		ObservableList<LinhaLocatario> ListaLocatario = FXCollections.observableArrayList();

		ArrayList<Locatario> listaLocatarios;
		listaLocatarios = (ArrayList<Locatario>) new Locatario().buscarLocatarios();

		for (Locatario locatario : listaLocatarios) {
			LinhaLocatario linha = new LinhaLocatario();
			linha.setCPF(locatario.getCPF());
			linha.setLocatario(locatario.getNome());
			linha.setLoc(locatario);

			ListaLocatario.add(linha);

		}
		TabLocatario.setItems(ListaLocatario);
	}

	
	public void filtraListaLocatario() {

		ObservableList<LinhaLocatario> ListaLocatarioFX = FXCollections.observableArrayList();

		ArrayList<Locatario> listaFiltro = (ArrayList<Locatario>) new Locatario()
				.buscarLocatarios(txt_locatario.getText());

		for (Locatario locatario : listaFiltro) {
			LinhaLocatario linha = new LinhaLocatario();
			linha.setCPF(locatario.getCPF());
			linha.setLocatario(locatario.getNome());
			linha.setLoc(locatario);

			ListaLocatarioFX.add(linha);

		}
		TabLocatario.setItems(ListaLocatarioFX);
	}
	

}
