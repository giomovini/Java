package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import gerenciador_exemplares.Exemplar;
import gerenciador_exemplares.ImprimirResultados;
import gerenciador_locacoes.Locacao;
import gerenciador_locatarios.Locatario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import teste.exemplar.LinhaExemplar;
import teste.locacao.LinhaLocacao;
import teste.locatario.LinhaLocatario;

public class ControllerTelaInicial {

	@FXML
	TabPane Abas;

	@FXML
	AnchorPane ancor;

	@FXML
	ImageView Inicial_imagem;

	@FXML
	TableView<LinhaLocatario> TabLocatario;

	@FXML
	TableColumn<LinhaLocatario, String> Locatario, CPF, Sexo, Idade, Email, Telefone;

	@FXML
	TableView<LinhaExemplar> TabExemplar;

	@FXML
	TableColumn<LinhaExemplar, String> Exemplar, Disponivel, Autor, Idioma, Edicao, Lancamento, Categoria,
			Classificacao, CodBarras, Localizacao;

	@FXML
	TableColumn<LinhaLocatario, Button> Endereco;

	@FXML
	TableView<LinhaLocacao> TabLocacao;

	@FXML
	TableColumn<LinhaLocacao, String> Locatario_L, Exemplar_L, Status, DataEmprestimo, PrazoDevolucao, DataDevolucao;

	@FXML
	Button InserirLocatario, DeletarLocatario, EditarLocatario, InserirExemplar, EditarExemplar, DeletarExemplar,
			InserirLocacao, DevolverLocacao, Imprimir;

	@FXML
	TextField locatario_busca, Exemplar_Busca, Autor_Busca, L_Locatario, L_Exemplar;

	@FXML
	CheckBox Locados, Devolvidos;
	
	public static ArrayList<gerenciador_exemplares.Exemplar> listaImpressao;
	public static boolean atualiza = false;
	public static boolean editar = false;
	public static Locatario Loc;
	public static gerenciador_exemplares.Exemplar Exemplar_;

	@FXML
	public void initialize() {

		Thread atualizacaoTabelas = new Thread(t1);
		atualizacaoTabelas.start();

		Tela.getInstance().getPalco().setOnCloseRequest(e -> {
			BancoOperacoes.fechar();
			if (Tela.getInstance().getPalcoSobreposto() != null) {
				Tela.getInstance().getPalcoSobreposto().close();
			}
		});

		Inicial_imagem.setImage(new Image(Imagens.inicial.Imagem));

		propriedadesLocatario();

		propriedadesExemplar();

		propriedadesLocacao();

	}

	public void propriedadesLocacao() {

		Locatario_L.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("Locatario"));
		Exemplar_L.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("Exemplar"));
		Status.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("Status"));
		DataEmprestimo.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("DataLocacao"));
		PrazoDevolucao.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("PrazoDevolucao"));
		DataDevolucao.setCellValueFactory(new PropertyValueFactory<LinhaLocacao, String>("DataDevolucao"));

		Locados.setSelected(true);
		Devolvidos.setSelected(true);

		L_Locatario.setOnKeyReleased(e -> {
			converteListaLocacao(true);
		});
		L_Exemplar.setOnKeyReleased(e -> {
			converteListaLocacao(true);
		});

		Locados.setOnAction(e -> {
			converteListaLocacao(true);
		});

		Devolvidos.setOnAction(e -> {
			converteListaLocacao(true);
		});

		InserirLocacao.setOnAction(e -> {
			editar = false;
			Tela.getInstance().AbrirTelaSobreposta(Arquivos.inserirLocacao, Titulos.inserirLocatario);
		});

		DevolverLocacao.setOnAction(e -> {
			if (TabLocacao.getSelectionModel().getSelectedItem() != null) {
				Locacao locacao = TabLocacao.getSelectionModel().getSelectedItem().getLoc();
				locacao.realizarDevolucao(locacao);
				converteListaLocacao(false);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
			}
		});

		converteListaLocacao(false);

	}

	public void propriedadesLocatario() {

		Locatario.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Locatario"));
		CPF.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("CPF"));
		Sexo.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Sexo"));
		Idade.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Idade"));
		Email.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Email"));
		Telefone.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, String>("Telefone"));
		Endereco.setCellValueFactory(new PropertyValueFactory<LinhaLocatario, Button>("Endereco"));

		locatario_busca.setOnKeyReleased(e -> {
			if (locatario_busca.getText().equals("")) {
				converteListaLocatario(false);
			} else {
				converteListaLocatario(true);
			}

		});

		InserirLocatario.setOnAction(e -> {
			editar = false;
			Tela.getInstance().AbrirTelaSobreposta(Arquivos.inserirLocatario, Titulos.inserirLocatario);
		});

		EditarLocatario.setOnAction(e -> {
			editar = true;

			if (TabLocatario.getSelectionModel().getSelectedItem() != null) {
				Loc = TabLocatario.getSelectionModel().getSelectedItem().getLoc();
				Tela.getInstance().AbrirTelaSobreposta(Arquivos.inserirLocatario, Titulos.editarLocatario);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
			}

		});

		DeletarLocatario.setOnAction(e -> {
			if (TabLocatario.getSelectionModel().getSelectedItem() != null) {
				Locatario locatario = TabLocatario.getSelectionModel().getSelectedItem().getLoc();
				locatario.deletarLocatario(locatario);
				converteListaLocatario(false);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
			}
		});

		converteListaLocatario(false);
	}

	public void propriedadesExemplar() {

		Exemplar.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Titulo"));
		Autor.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("autor"));
		Idioma.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Idioma"));
		Edicao.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Edicao"));
		Lancamento.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("anoLancamento"));
		Categoria.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Categoria"));
		Classificacao.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("classificacaoIdade"));
		CodBarras.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("codBarras"));
		Localizacao.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("Localizacao"));
		Disponivel.setCellValueFactory(new PropertyValueFactory<LinhaExemplar, String>("qtdeDisponivel"));
		
		Imprimir.setOnAction(e->{
			ImprimirResultados imprimir = new ImprimirResultados();
			Exemplar ex = new Exemplar();
			ex.setImpressao(imprimir);
			ex.getImpressao().imprimirRegistrosExemplar(listaImpressao);
		});

		Exemplar_Busca.setOnKeyReleased(e -> {
			if (Exemplar_Busca.getText().equals("") && Autor_Busca.getText().equals("")) {
				converteListaExemplar(false);
			} else {
				converteListaExemplar(true);
			}

		});

		Autor_Busca.setOnKeyReleased(e -> {
			if (Exemplar_Busca.getText().equals("") && Autor_Busca.getText().equals("")) {
				converteListaExemplar(false);
			} else {
				converteListaExemplar(true);
			}
		});

		InserirExemplar.setOnAction(e -> {
			editar = false;
			Tela.getInstance().AbrirTelaSobreposta(Arquivos.inserirExemplar, Titulos.inserirExemplar);
		});

		EditarExemplar.setOnAction(e -> {
			editar = true;

			if (TabExemplar.getSelectionModel().getSelectedItem() != null) {
				Exemplar_ = TabExemplar.getSelectionModel().getSelectedItem().getEx();
				Tela.getInstance().AbrirTelaSobreposta(Arquivos.inserirExemplar, Titulos.editarExemplar);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
			}

		});

		DeletarExemplar.setOnAction(e -> {
			if (TabExemplar.getSelectionModel().getSelectedItem() != null) {
				gerenciador_exemplares.Exemplar Ex = TabExemplar.getSelectionModel().getSelectedItem().getEx();
				if (Ex.deletarExemplar(Ex)) {
					converteListaExemplar(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
			}
		});

		converteListaExemplar(false);
	}

	public void converteListaLocacao(boolean filtro) {
		ObservableList<LinhaLocacao> ListaLocacao = FXCollections.observableArrayList();

		ArrayList<Locacao> listaLocacoes;
		Locacao Loc = new Locacao();
		if (filtro) {
			String status = "NADA";
			if (Locados.isSelected() && Devolvidos.isSelected()) {
				status = "T";
			} else if (Locados.isSelected() && !Devolvidos.isSelected()) {
				status = "LOCADO";
			} else if (!Locados.isSelected() && Devolvidos.isSelected()) {
				status = "DEVOLVIDO";
			}

			listaLocacoes = (ArrayList<Locacao>) Loc.buscarLocacoes(L_Locatario.getText(), L_Exemplar.getText(),
					status);

		} else {
			listaLocacoes = (ArrayList<Locacao>) Loc.buscarLocacoes();
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		for (Locacao locacao : listaLocacoes) {
			LinhaLocacao linha = new LinhaLocacao();

			if (locacao.getDataDevolucao() == null) {
				linha.setDataDevolucao("");
			} else {
				linha.setDataDevolucao(formato.format(locacao.getDataDevolucao()));
			}

			linha.setDataLocacao(formato.format(locacao.getDataLocacao()));
			linha.setExemplar(locacao.getExemplar().getTitulo());
			linha.setLocatario(locacao.getLocatario().getNome());
			linha.setPrazoDevolucao(formato.format(locacao.getPrazoDevolucao()));
			linha.setStatus(locacao.getStatus());

			linha.setLoc(locacao);

			ListaLocacao.add(linha);

		}
		ControllerTelaInicial.atualiza = true;
		TabLocacao.setItems(ListaLocacao);
	}

	@SuppressWarnings("unchecked")
	public void converteListaExemplar(boolean filtro) {
		ObservableList<LinhaExemplar> listaExemplar = FXCollections.observableArrayList();

		ArrayList<gerenciador_exemplares.Exemplar> listaExemplares;
		gerenciador_exemplares.Exemplar exemplar = new gerenciador_exemplares.Exemplar();
		if (!filtro) {
			listaExemplares = (ArrayList<gerenciador_exemplares.Exemplar>) new gerenciador_exemplares.Exemplar().buscarExemplares();
		} else {
			listaExemplares = (ArrayList<gerenciador_exemplares.Exemplar>) exemplar.buscarExemplares(Exemplar_Busca.getText(),
					Autor_Busca.getText());
		}
		
		listaImpressao = (ArrayList<gerenciador_exemplares.Exemplar>) listaExemplares.clone();

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
			linha.setAutor(Ex.getAutor().getNome() + " (" + Ex.getAutor().getWebSite() + ")");
			linha.setEx(Ex);

			listaExemplar.add(linha);

		}
		TabExemplar.setItems(listaExemplar);
	}

	public void converteListaLocatario(boolean filtro) {
		ObservableList<LinhaLocatario> ListaLocatario = FXCollections.observableArrayList();

		ArrayList<Locatario> listaLocatarios;
		Locatario Loc = new gerenciador_locatarios.Locatario();

		if (filtro) {
			listaLocatarios = (ArrayList<Locatario>) Loc.buscarLocatarios(locatario_busca.getText());
		} else {
			listaLocatarios = (ArrayList<Locatario>) Loc.buscarLocatarios();
		}

		for (Locatario locatario : listaLocatarios) {
			LinhaLocatario linha = new LinhaLocatario();
			linha.setCPF(locatario.getCPF());
			linha.setLocatario(locatario.getNome());
			linha.setSexo(locatario.getSexo().equals("M") ? "Masculino" : "Feminino");
			linha.setEmail(locatario.getEmail());
			linha.setTelefone(locatario.getTelefone());
			linha.getEndereco().setOnAction(e -> JOptionPane.showMessageDialog(null, formataEndereco(locatario),
					"Endereço", JOptionPane.INFORMATION_MESSAGE));
			linha.setIdade(calculaIdade(locatario.getNascimento()));
			linha.setLoc(locatario);

			ListaLocatario.add(linha);

		}
		TabLocatario.setItems(ListaLocatario);
	}

	public String formataEndereco(Locatario Loc) {

		String Endereco = "Cidade: " + Loc.getCidade() + "\n" + "Estado: " + Loc.getEstado() + "\n" + "Endereço: "
				+ Loc.getEndereco();
		return Endereco;
	}

	public String calculaIdade(String nasc) {

		double ANO_EM_MILISEGUNDOS = 30.0 * 24.0 * 60.0 * 60.0 * 1000.0 * 12;
		String idade = "";

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date dataInicio = format.parse(nasc);
			Date dataFim = new Date();

			idade = String.valueOf((int) ((dataFim.getTime() - dataInicio.getTime()) / ANO_EM_MILISEGUNDOS));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idade;
	}

	private Runnable t1 = new Runnable() {

		public void run() {
			while (true) {
				if (atualiza) {
					converteListaLocatario(false);
					converteListaExemplar(false);
					converteListaLocacao(false);
					atualiza = false;
				}
				try {
					Thread.sleep(2000);
					if (!Tela.getInstance().getPalco().isShowing())
						return;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	};

}
