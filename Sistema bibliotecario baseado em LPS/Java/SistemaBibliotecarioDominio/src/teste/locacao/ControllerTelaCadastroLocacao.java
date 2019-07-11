package teste.locacao;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import gerenciador_exemplares.Exemplar;
import gerenciador_locacoes.Locacao;
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
import teste.exemplar.LinhaExemplar;
import teste.locatario.LinhaLocatario;

public class ControllerTelaCadastroLocacao {


	@FXML
	TableView<LinhaLocatario> TabLocatario;
	
	@FXML
	TableView<LinhaExemplar>TabExemplar;
	
	@FXML
	TableColumn<LinhaExemplar, String>col_titulo,col_autor,col_edicao;
	
	@FXML
	TableColumn<LinhaLocatario, String>col_locatario,col_cpf;

	@FXML
	TextField txt_locatario,txt_exemplar;
	
	@FXML 
	Button Voltar,Salvar;
	
	@FXML
	DatePicker DataDevolucao;
	
	@FXML
	public void initialize() {
		col_locatario.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Locatario"));
		col_cpf.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("CPF"));
		col_titulo.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Titulo"));
		col_autor.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("autor"));
		col_edicao.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Edicao"));
		converteListaLocatario();
		converteListaExemplar();
		
		txt_locatario.setOnKeyReleased(e -> {
			if (txt_locatario.getText().equals("")) {
				converteListaLocatario();
			} else {
				filtraListaLocatario();
			}
		});
		
		txt_exemplar.setOnKeyReleased(e -> {
			if (txt_exemplar.getText().equals("")) {
				converteListaExemplar();
			} else {
				filtraListaExemplar();
			}

		});
		
		Salvar.setOnAction(e->{
			Locatario locatario = null;
			Exemplar exemplar = null;
			
			if(TabExemplar.getSelectionModel().getSelectedItem() != null) {
				exemplar = TabExemplar.getSelectionModel().getSelectedItem().getEx();
			}else {
				JOptionPane.showMessageDialog(null, "Selecione um registro na tabela de Exemplares!");
			}
			
			if(TabLocatario.getSelectionModel().getSelectedItem() != null) {
				locatario = TabLocatario.getSelectionModel().getSelectedItem().getLoc();
			}else {
				JOptionPane.showMessageDialog(null, "Selecione um registro na tabela de Locatários!");
			}
			
			if(locatario != null && exemplar != null ) {
				Locacao locacao = new Locacao();
				locacao.setLocatario(locatario);
				locacao.setExemplar(exemplar);
				locacao.setStatus("LOCADO");
				locacao.setDataLocacao(new Date());
				
				if(DataDevolucao.getValue() != null) {
					Date data = Date.from(DataDevolucao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					locacao.setPrazoDevolucao(data);
				}
				
				if(locacao.validarLocacao(locacao)) {
					
					locacao.realizarLocacao(locacao);
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					JOptionPane.showMessageDialog(null, "Empréstimo salvo com sucesso!\nA devolução deve ser feita até "+formato.format(locacao.getPrazoDevolucao()));
					Tela.getInstance().getPalcoSobreposto().close();
					ControllerTelaInicial.atualiza = true;
				}else{
					JOptionPane.showMessageDialog(null, "Não foi possivel registrar o empréstimo, confira a disponibilidade do exemplar e a data do prazo de devolução e tente novamente!");
				}
				
			}
			
		});
		
		Voltar.setOnAction(e -> {
			Tela.getInstance().getPalcoSobreposto().close();
		});

	}
	
	public void converteListaExemplar() {
		ObservableList<LinhaExemplar> listaExemplar = FXCollections.observableArrayList();

		ArrayList<gerenciador_exemplares.Exemplar> listaExemplares;
		listaExemplares = (ArrayList<gerenciador_exemplares.Exemplar>) new gerenciador_exemplares.Exemplar().buscarExemplares();

		for (gerenciador_exemplares.Exemplar Ex : listaExemplares) {
			LinhaExemplar linha = new LinhaExemplar();

			linha.setTitulo(Ex.getTitulo());
			linha.setEdicao(String.valueOf(Ex.getEdicao()));
			linha.setAutor(Ex.getAutor().getNome());
			linha.setEx(Ex);

			listaExemplar.add(linha);

		}
		TabExemplar.setItems(listaExemplar);
	}
	
	public void filtraListaExemplar() {

		ObservableList<LinhaExemplar> listaExemplar = FXCollections.observableArrayList();

		ArrayList<gerenciador_exemplares.Exemplar> listaExemplares;
		listaExemplares = (ArrayList<gerenciador_exemplares.Exemplar>) new gerenciador_exemplares.Exemplar()
				.buscarExemplares(txt_exemplar.getText(), "");

		for (gerenciador_exemplares.Exemplar Ex : listaExemplares) {
			LinhaExemplar linha = new LinhaExemplar();

			linha.setTitulo(Ex.getTitulo());
			linha.setIdioma(Ex.getIdioma());
			linha.setEdicao(String.valueOf(Ex.getEdicao()));
			linha.setAnoLancamento(String.valueOf(Ex.getAnoLancamento()));
			linha.setQtdeDisponivel(String.valueOf(Ex.getQtdeDisponivel()));
			linha.setCategoria(Ex.getCategoria());
			linha.setClassificacaoIdade(String.valueOf(Ex.getClassificacaoIdade()));
			linha.setLocalizacao(Ex.getLocalizacao());
			linha.setCodBarras(Ex.getCodBarras());
			linha.setAutor(Ex.getAutor().getNome());
			linha.setEx(Ex);

			listaExemplar.add(linha);

		}
		TabExemplar.setItems(listaExemplar);
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
