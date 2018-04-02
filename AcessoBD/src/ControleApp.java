package com.softgraf.consultasql.fx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.sun.java.accessibility.util.java.awt.ListTranslator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControleApp {

	@FXML
	private TextField txtSql, txtRegistro;

	@FXML
	private Button btnInicio, btnFim, btnSair, btnProxima, btnAnterior, btnExecutar;

	@FXML
	private Label lblMensagem;

	@FXML
	private TableView<FilmesProperty> tblFilmes;

	@FXML
	private TableColumn<FilmesProperty, String> colCodigo, colTitulo, colGenero, colProdutora;

	@FXML
	private TableColumn<FilmesProperty, Date> colDataCompra;

	private Alert erro;
	private BD bd;
	private PreparedStatement stmt;
	private ResultSet rs;

	private ObservableList<FilmesProperty> ListaFilmes;

	final private int LINHAS_POR_PAGINA = 13;

	public ControleApp() {
		erro = new Alert(AlertType.ERROR);
		erro.setTitle("Consulta Filmes");
		erro.setResizable(true);

		bd = new BD();
		if (!bd.conectar()) {
			erro.setHeaderText(null);
			erro.setContentText("Erro ao conctar-se com  banco");
			erro.showAndWait();
			System.exit(0);
		}
	}

	@FXML
	public void initialize() {

		colCodigo.setCellValueFactory(new PropertyValueFactory<FilmesProperty, String>("codigo"));
		colTitulo.setCellValueFactory(new PropertyValueFactory<FilmesProperty, String>("titulo"));
		colGenero.setCellValueFactory(new PropertyValueFactory<FilmesProperty, String>("genero"));
		colProdutora.setCellValueFactory(new PropertyValueFactory<FilmesProperty, String>("produtora"));
		colDataCompra.setCellValueFactory(new PropertyValueFactory<FilmesProperty, Date>("dataCompra"));

		carregaTabela();
		inicializaListaFilmes();
		tblFilmes.setItems(ListaFilmes);

		btnExecutar.setOnAction(this::handleExecutaSql);
		btnInicio.setOnAction(e -> {
			tblFilmes.scrollTo(ListaFilmes.get(0));
			tblFilmes.getSelectionModel().select(0);

		});
		btnFim.setOnAction(e -> {
			tblFilmes.scrollTo(ListaFilmes.get(ListaFilmes.size() - 1));
			tblFilmes.getSelectionModel().select(ListaFilmes.size() - 1);
		});
		btnProxima.setOnAction(e -> proximaPag());
		btnAnterior.setOnAction(e -> anteiorPag());

		txtRegistro.setOnKeyTyped(e -> {
			char crc = e.getCharacter().charAt(0);

			if (crc >= '0' && crc <= '9') {
				String linha = txtRegistro.getText().concat(Character.toString(crc));
				int row = Integer.parseInt(linha);

				if (row > 0)
					row--;
				if (row >= ListaFilmes.size())
					row = ListaFilmes.size() - 1;
				tblFilmes.scrollTo(ListaFilmes.get(row));
				tblFilmes.getSelectionModel().select(row);
			} else {
				e.consume();
			}

		});
		// txtRegistro.setOnKeyTyped(e -> irPara());

		btnSair.setOnAction(e -> fechar());
		ConsultaFilmes.stage.setOnCloseRequest(e -> fechar());

	}

	public void fechar() {
		ConsultaFilmes.stage.close();
		
		try {
			bd.fechar();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void carregaTabela() {
		String sql = "SELECT * FROM filmes";
		System.out.print("carregando tabela filmes ..");

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery();

			rs.last();
			lblMensagem.setText("Encontrado total de " + rs.getRow() + " registros");

			rs.beforeFirst();
			System.out.println("ok");

		} catch (SQLException e) {
			erro.setHeaderText("Erro Fatal ao carregar tabela filmes");
			erro.setContentText(e.getMessage());
			erro.showAndWait();
			fechar();
		}
	}

	private void inicializaListaFilmes() {
		System.out.print("inicializando lista filmes...");
		ListaFilmes = FXCollections.observableArrayList();
		try {
			while (rs.next()) {
				ListaFilmes.add(new FilmesProperty(rs.getString("codigo"), rs.getString("titulo"),
						rs.getString("genero"), rs.getString("produtora"), rs.getDate("dataCompra")));
			}
			System.out.println("OK");
		} catch (SQLException e) {
			erro.setHeaderText("Erro fatal ao inicializar ListaFilmes");
			erro.setContentText(e.getMessage());
			erro.showAndWait();
			e.printStackTrace();
		}

	}

	private void handleExecutaSql(ActionEvent evento) {

		String sql = "SELECT * FROM filmes " + txtSql.getText().trim() + " ;";
		System.out.print("Executando sentença SQL...");

		try {
			rs.close();
			stmt.close();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		try {
			stmt = bd.getConexao().prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			System.out.println("ok");
			rs.last();
			lblMensagem.setText("Encontrado um total de " + rs.getRow());
			rs.beforeFirst();

			inicializaListaFilmes();
			tblFilmes.setItems(ListaFilmes);

		} catch (SQLException e) {
			System.out.println("Erro\n " + e.getMessage());
			erro.setHeaderText("Erro sentença Sql");
			erro.setContentText(e.getMessage());
			erro.showAndWait();
		}

	}

	private void proximaPag() {
		int row = tblFilmes.getSelectionModel().getSelectedIndex();
		if (row == -1) {
			row = LINHAS_POR_PAGINA;
		} else {
			row += LINHAS_POR_PAGINA;
			if (row >= ListaFilmes.size()) {
				row = ListaFilmes.size() - 1;
			}
		}

		tblFilmes.scrollTo(ListaFilmes.get(row));
		tblFilmes.getSelectionModel().select(row);

	}

	private void anteiorPag() {
		int row = tblFilmes.getSelectionModel().getSelectedIndex();

		if (row == -1) {
			row = 0;
		} else {
			row = row - LINHAS_POR_PAGINA;
			if (row <= LINHAS_POR_PAGINA) {
				row = 0;
			}
		}
		tblFilmes.scrollTo(ListaFilmes.get(row));
		tblFilmes.getSelectionModel().select(row);

	}

}
