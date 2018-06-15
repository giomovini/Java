package implementacao;

import java.util.ArrayList;

import javafx.scene.control.TitledPane;

public class MenuCliente {

	static MenuCliente instancia = null;

	private MenuCliente() {

	}

	public static synchronized MenuCliente getInstance() {

		if (instancia == null) {
			instancia = new MenuCliente();
		}

		return instancia;

	}


	public ArrayList<TitledPane> criarMenuCliente() {

		ArrayList<TitledPane> listaMenu = new ArrayList<>();

		TitledPane conta = new TitledPane();
		TitledPane cardapio = new TitledPane();
		TitledPane pedido = new TitledPane();

		conta.setText("Conta");
		cardapio.setText("Cardapio");
		pedido.setText("Pedido");

		cardapio.setOnMouseClicked(e -> {
			new ClientePrincipal().cardapio();
		});

		pedido.setOnMouseClicked(e -> {
			new ClientePrincipal().pedido();
		});

		conta.setOnMouseClicked(e -> {
			new ClientePrincipal().visualizarPedido();
		});

		listaMenu.add(conta);
		listaMenu.add(cardapio);
		listaMenu.add(pedido);

		return listaMenu;

	}

}
