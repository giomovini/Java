package implementacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import TipoDado.ItemPedido;
import TipoDado.Pedido;
import TipoDado.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import sun.rmi.registry.RegistryImpl;
import visao.conexaoServidor.ControleConexaoServidor;

public class Remota {

	static Remota instancia = null;

	private Remota() {

	}

	public static synchronized Remota getInstance() {

		if (instancia == null) {
			instancia = new Remota();
		}

		return instancia;

	}

	RegistryImpl registryImpl;
	private ServidorInterface servidor;

	public void registrarCliente(String porta, String ip) {
		try {
			registryImpl = new RegistryImpl(Integer.parseInt(porta));

			ClienteInterface cliente = new ClienteImplementacao();

			Naming.rebind("rmi://" + ip + ":" + porta + "/olaMundo", cliente);
			ControleConexaoServidor.conectado = true;

			ControleConexaoServidor.registrado = true;
		} catch (RemoteException | NumberFormatException | MalformedURLException e) {

			ControleConexaoServidor.registrado = false;
			JOptionPane.showMessageDialog(null, "informações de conexão incorretas", "Conexão",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public boolean conectarCliente(String porta, String IP, String CPF, String Senha) {

		if (!ControleConexaoServidor.registrado)
			registrarCliente(porta, IP);

		int respostaServ = 0;

		try {
			servidor = (ServidorInterface) Naming
					.lookup("rmi://" + ClientePrincipal.IP + ":" + ClientePrincipal.PORTA + "/olaMundo");

			Cliente c = Remota.getInstance().buscaCliente(CPF);

			if (c == null) {
				JOptionPane.showMessageDialog(null, "informações de Login incorretas", "Login",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			int idPedido = Remota.getInstance().id_pedido(c.getId());

			ClientePrincipal.ID_PEDIDO = idPedido;

			if (idPedido == 0) {
				JOptionPane.showMessageDialog(null,
						"Não há uma conta aberta!\n Procure um terminal para abrir uma conta.");
				return false;
			}

			respostaServ = servidor.conectarCliente(CPF, Senha, IP, porta);

			if (respostaServ == 1 || respostaServ == 2) {
				ControleConexaoServidor.conectado = true;

			} else {
				JOptionPane.showMessageDialog(null, "informações de Login incorretas", "Login",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

		} catch (NumberFormatException | MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "informações de conexão incorretas", "Conexão",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public void desconectar() {
		try {
			servidor.desconectarCliente(ClientePrincipal.CPF);
		} catch (NumberFormatException | RemoteException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Produto> listaProdutos() {

		ArrayList<Produto> lista = null;
		ObservableList<Produto> listaR = FXCollections.observableArrayList();
		try {
			lista = servidor.buscarInformacoesTodosProdutos();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (lista == null)
			return null;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getQuantidade() > 0) {
				listaR.add(lista.get(i));
			}
		}

		for (int i = 0; i < listaR.size(); i++) {
			listaR.get(i).setBtnEditarDeletar(new Button("Pedido"));
			listaR.get(i).getBtnEditarDeletar().setPrefWidth(80);
		}

		return listaR;

	}

	public boolean inserirItemPedidoDescontarProduto(String id_produto, String id_pedido, int quantidade) {
		boolean b = false;
		try {
			Produto p = servidor.buscarProduto(id_produto);
			if (quantidade > p.getQuantidade()) {
				JOptionPane.showMessageDialog(null, "Quantidade indisponivel!");
				return false;
			}

			b = servidor.inserirItemPedido(ClientePrincipal.CPF, id_produto, id_pedido, quantidade);
			servidor.editarProduto(ClientePrincipal.CPF, p.getID(), p.getNOME(), p.getDESCRICAO(), p.getPreco(),
					(p.getQuantidade() - quantidade), p.getVALIDADE(), p.getTIPO());

		} catch (RemoteException e) {
			e.printStackTrace();
			return b;
		}
		return b;
	}

	public int id_pedido(String id_cliente) {
		try {
			return servidor.buscaIdPedido(id_cliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public Cliente buscaCliente(String cpf) {
		try {
			Cliente c = servidor.buscarInformacoesCliente(cpf);
			return c;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ObservableList<ItemPedido> buscaItensPedidos() {

		ObservableList<ItemPedido> listaR = FXCollections.observableArrayList();
		ArrayList<ItemPedido> l = null;

		try {
			l = servidor.buscarInformacoesTodosItensPedidos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < l.size(); i++) {
			listaR.add(l.get(i));
		}

		return listaR;
	}

	public Pedido buscaPedido(String id) {
		try {
			return servidor.buscaPedido(id);
		} catch (RemoteException e) {

			e.printStackTrace();
			return null;
		}
	}

}
