package com.softgraf.commandline.fx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class ControleApp {

	// injeção dos componentes FX
	@FXML
	private TableView<ObservableList<StringProperty>> tblView;
	@FXML
	private TextField txtSql;
	@FXML
	private Label lblMensagem;

	private BD bd;
	private List<String> listaSql;
	private int sqlAtual = 0; // comando atual de listaSql

	// construtor da classe
	public ControleApp() {
		bd = new BD();
		if (!bd.conectar()) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("SQL Command Line");
			erro.setResizable(true);
			erro.setContentText("Erro fatal ao conectar-se com o banco de dados!");
			erro.showAndWait();
			System.exit(0);
		}
		listaSql = new ArrayList<>();
	}

	@FXML
	public void initialize() {

		CommandLine.stage.setOnCloseRequest(e -> {
			CommandLine.stage.close();
			bd.fechar();
		});

		txtSql.setOnKeyPressed(e -> acaoSql(e));
	}

	private void acaoSql(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			executaSql();

		else if (e.getCode() == KeyCode.UP && !listaSql.isEmpty()) {
			String sql = listaSql.get(sqlAtual); // recupera comando da lista
			sqlAtual--;
			// se inicio da lista -> vai para o fim
			if (sqlAtual < 0)
				sqlAtual = listaSql.size() - 1;
			txtSql.setText(sql);

		} else if (e.getCode() == KeyCode.DOWN && !listaSql.isEmpty()) {
			String sql = listaSql.get(sqlAtual);
			sqlAtual++;
			// se fim da lista -> vai para o inico
			if (sqlAtual == listaSql.size())
				sqlAtual = 0;
			txtSql.setText(sql);
		}

	}

	private void executaSql(){
		String sql = txtSql.getText().trim();
		//              0     1    2       3
		//  vetor = ["SELECT" "*" "FROM" "FILMES"] = "SELECT * FROM FILMES".split(" ")
		String comando = sql.split(" ")[0].toUpperCase();  // "SELECT"
		
		switch (comando) {
		
		case "":
			System.out.println("Faltou digitar o comando SQL");
			break;

		case "SELECT":
		case "SHOW":
		case "DESC":
		 
			executaSelect(sql, comando);
			break;
		case "UPDATE":
		case "CREATE":
		case "DELETE":
		case "INSERT":
		case "DROP":
		case "USE":
			
			executaUpdate(sql,comando);
			break;
		case "EXIT":
			CommandLine.stage.close();
			bd.fechar();
			break;
		
		
		
		default:
			System.out.println("Comando SQL não aceito: " + comando);
		}
	}

	private void executaSelect(String sql, String cmd) {
		System.out.printf("\nExecutando Comando %s...", cmd);

		try {
			// importar sempre do pacote java.sql (e nunca do java.mysql)
			PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.last();
			lblMensagem.setText(cmd + " encontrou " + rs.getRow());

			rs.beforeFirst(); // antes do primeiro registro
			tblView.getColumns().clear(); // apaga todas as colunas do TableView
			tblView.getItems().clear(); // apaga todos os dados da TabeView

			// recupera os metadados da tabela
			ResultSetMetaData metaDados = rs.getMetaData();
			System.out.println("\n\n------- METADOS --------");
			System.out.printf("%-15s %-20s %-10s %-5s %-5s \n", "TABELA", "LABEL", "NOME_TIPO", "TIPO", "TAMANHO");

			// para cada coluna dos meta dados
			for (int i = 1; i <= metaDados.getColumnCount(); i++) {
				final int NCOL = i - 1;
				String nomeTabela = metaDados.getTableName(i);
				String labelColuna = metaDados.getColumnLabel(i);
				String nomeTipoColuna = metaDados.getColumnTypeName(i);
				int tipoColuna = metaDados.getColumnType(i);
				int tamanhoColuna = metaDados.getColumnDisplaySize(i);
				System.out.printf("%-15s %-20s %-10s %-5s %-5s \n", nomeTabela, labelColuna, nomeTipoColuna, tipoColuna,
						tamanhoColuna);

				// cria as TableColumns e o CellValueFactory para mostrar os
				// valores
				// de cada celula da tabela
				TableColumn<ObservableList<StringProperty>, String> coluna = new TableColumn<>(
						labelColuna.toUpperCase());
				coluna.setCellValueFactory(param -> {
					return new SimpleStringProperty(param.getValue().get(NCOL).getValue());
				});

				tblView.getColumns().add(coluna);
			} // fim do for

			// todas as linhas da tabela
			ObservableList<ObservableList<StringProperty>> listaDados = FXCollections.observableArrayList();

			while (rs.next()) {

				// esta é uma única linha da tabela
				ObservableList<StringProperty> linha = FXCollections.observableArrayList();
				// le cada coluna da tabela
				for (int i = 1; i <= metaDados.getColumnCount(); i++) {
					// este é o dado da célula
					String dado = rs.getString(metaDados.getColumnLabel(i));
					// esta é a celula
					StringProperty celulaProperty = new SimpleStringProperty(dado);
					linha.add(celulaProperty);
				}

				listaDados.add(linha);
			}

			// finalmente atribui a lista de dados (modelo) para a table view
			tblView.setItems(listaDados);

			// adiciona a instruçã sql à lista
			listaSql.add(sql);
			sqlAtual = listaSql.size() - 1;

			// libera recursos do banco
			try {
				rs.close();
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			lblMensagem.setText("ERRO: " + e.getMessage());
		}
	}
	private void executaUpdate(String sql, String cmd){
		System.out.printf("\nExecutando comando %s...",cmd);
		
		try{
			PreparedStatement stmt  = bd.getConexao().prepareStatement(sql);
			int linhas = stmt.executeUpdate(sql);
			//retorna o numero de linhas afetadas
			lblMensagem.setText(cmd+" afetou "+linhas+" linhas");
			
			listaSql.add(sql);
			sqlAtual = listaSql.size()-1;
			
		}catch (Exception e) {
			lblMensagem.setText("ERRO: "+e.getMessage());
		}
		
	}

}
















