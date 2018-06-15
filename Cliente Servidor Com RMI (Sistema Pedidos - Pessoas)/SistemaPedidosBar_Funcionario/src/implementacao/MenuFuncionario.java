package implementacao;

import java.util.ArrayList;


import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class MenuFuncionario {

	static MenuFuncionario instancia = null;

	private MenuFuncionario() {

	}

	public static synchronized MenuFuncionario getInstance() {

		if (instancia == null) {
			instancia = new MenuFuncionario();
		}

		return instancia;

	}

	public Button inserirCliente, editarCliente, deletarCliente;
	public Button inserirProduto, editarProduto, deletarProduto;
	public Button inserirFuncionario, editarFuncionario, deletarFuncionario;
	public Button fecharConta, abrirConta;
	

	private int width = 220;

	public ArrayList<TitledPane> criarMenuFuncionario() {

		ArrayList<TitledPane> listaMenu = new ArrayList<>();

		AnchorPane Acliente = botoesCliente();
		AnchorPane Aproduto = botoesProduto();
		AnchorPane Afuncionario = botoesFuncionario();
		AnchorPane Aconta = botaoConta();

		TitledPane cliente = new TitledPane();
		cliente.setText("Cliente");
		cliente.setContent(Acliente);

		TitledPane produto = new TitledPane();
		produto.setText("Produto");
		produto.setContent(Aproduto);

		TitledPane funcionario = new TitledPane();
		funcionario.setText("Funcionario");
		funcionario.setContent(Afuncionario);

		TitledPane conta = new TitledPane();
		conta.setText("Conta");
		conta.setContent(Aconta);
		
		TitledPane caixa = new TitledPane();
		caixa.setText("Caixa");
		caixa.setOnMouseClicked(e->new FuncPrincipal().relatorio());
		
		
		listaMenu.add(conta);
		listaMenu.add(cliente);
		listaMenu.add(produto);
		
		if(FuncPrincipal.ADM) {
			listaMenu.add(funcionario);
			listaMenu.add(caixa);
		}
		acoesBotoes();

		return listaMenu;

	}

	private void acoesBotoes() {

		inserirCliente.setOnAction(e -> {
			new FuncPrincipal().inserirCliente();
		});

		editarCliente.setOnAction(e -> {
			new FuncPrincipal().editarCliente();
		});

		deletarCliente.setOnAction(e -> {
			new FuncPrincipal().deletarCliente();
		});
		
		inserirProduto.setOnAction(e -> {
			new FuncPrincipal().inserirProduto();
		});

		editarProduto.setOnAction(e -> {
			new FuncPrincipal().editarProduto();
		});

		deletarProduto.setOnAction(e -> {
			new FuncPrincipal().deletarProduto();
		});
		
		inserirFuncionario.setOnAction(e -> {
			new FuncPrincipal().inserirFuncionario();
		});

		editarFuncionario.setOnAction(e -> {
			new FuncPrincipal().editarFuncionario();
		});

		deletarFuncionario.setOnAction(e -> {
			new FuncPrincipal().deletarFuncionario();
		});
		
		abrirConta.setOnAction(e -> {
			new FuncPrincipal().abrirConta();
		});
		
		fecharConta.setOnAction(e -> {
			new FuncPrincipal().fecharConta();
		});
		
	}

	private AnchorPane botaoConta() {

		int height = 28;
		int translateY = 1;

		AnchorPane pane = new AnchorPane();

		abrirConta = criarBotao("Abrir Conta", height, translateY);
		translateY += height + 1;
		fecharConta = criarBotao("Fechar Conta", height, translateY);
		translateY += height + 1;

		pane.setPrefWidth(width);
		pane.setPrefHeight(translateY);

		pane.getChildren().addAll(abrirConta, fecharConta);

		return pane;

	}


	private AnchorPane botoesFuncionario() {

		int height = 28;
		int translateY = 1;

		AnchorPane pane = new AnchorPane();

		inserirFuncionario = criarBotao("Inserir", height, translateY);
		translateY += height + 1;
		editarFuncionario = criarBotao("Editar", height, translateY);
		translateY += height + 1;
		deletarFuncionario = criarBotao("Deletar", height, translateY);
		translateY += height + 1;

		pane.setPrefWidth(width);
		pane.setPrefHeight(translateY);

		pane.getChildren().addAll(inserirFuncionario, editarFuncionario, deletarFuncionario);

		return pane;

	}

	private AnchorPane botoesProduto() {
		int height = 28;
		int translateY = 1;

		AnchorPane pane = new AnchorPane();

		inserirProduto = criarBotao("Inserir", height, translateY);
		translateY += height + 1;
		editarProduto = criarBotao("Editar", height, translateY);
		translateY += height + 1;
		deletarProduto = criarBotao("Deletar", height, translateY);
		translateY += height + 1;

		pane.setPrefWidth(width);
		pane.setPrefHeight(translateY);

		pane.getChildren().addAll(inserirProduto, editarProduto, deletarProduto);

		return pane;

	}

	private AnchorPane botoesCliente() {
		int height = 28;
		int translateY = 1;

		AnchorPane pane = new AnchorPane();

		inserirCliente = criarBotao("Inserir", height, translateY);
		translateY += height + 1;
		editarCliente = criarBotao("Editar", height, translateY);
		translateY += height + 1;
		deletarCliente = criarBotao("Deletar", height, translateY);
		translateY += height + 1;

		pane.setPrefWidth(width);
		pane.setPrefHeight(translateY);

		pane.getChildren().addAll(inserirCliente, editarCliente, deletarCliente);

		return pane;

	}

	private Button criarBotao(String nome, int height, int translateY) {
		Button btn = new Button(nome);
		btn.setPrefWidth(width);
		btn.setPrefHeight(height);
		btn.setTranslateY(translateY);
		return btn;
	}

}
