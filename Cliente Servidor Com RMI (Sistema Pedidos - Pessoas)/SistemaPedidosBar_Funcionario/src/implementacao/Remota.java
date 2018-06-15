package implementacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import TipoDado.Funcionario;
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

	public void registrarFuncionario(String porta, String ip) {
		try {
			registryImpl = new RegistryImpl(Integer.parseInt(porta));

			FuncionarioInterface funcionario = new FuncionarioImplementacao();

			Naming.rebind("rmi://" + ip + ":" + porta + "/olaMundo", funcionario);
			ControleConexaoServidor.conectado = true;

			ControleConexaoServidor.registrado = true;
		} catch (RemoteException | NumberFormatException | MalformedURLException e) {

			ControleConexaoServidor.registrado = false;
			JOptionPane.showMessageDialog(null, "informações de conexão incorretas", "Conexão",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}
	}

	public void conectarFuncionario(String porta, String IP, String CPF, String Senha) {

		if (!ControleConexaoServidor.registrado)
			registrarFuncionario(porta, IP);

		int respostaServ = 0;

		try {
			servidor = (ServidorInterface) Naming
					.lookup("rmi://" + FuncPrincipal.IP + ":" + FuncPrincipal.PORTA + "/olaMundo");

			respostaServ = servidor.conectarFuncionario(CPF, Senha, IP, porta);

			if (respostaServ == 1 || respostaServ == 2) {
				ControleConexaoServidor.conectado = true;

			} else {
				JOptionPane.showMessageDialog(null, "informações de Login incorretas", "Login",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

		} catch (NumberFormatException | MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "informações de conexão incorretas", "Conexão",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}

		if (ControleConexaoServidor.conectado) {

			if (respostaServ == 1) {
				FuncPrincipal.ADM = false;
			} else if (respostaServ == 2) {
				FuncPrincipal.ADM = true;
			}
			new FuncPrincipal().inicio();
		}

	}

	public boolean existeCpfFuncionario(String cpf) {

		// ArrayList<String> l = null;

		Funcionario l = null;

		try {
			l = servidor.buscarInformacoesFuncionario(cpf);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (l != null)
			return true;
		else
			return false;
	}

	public void inserirFuncionario(String cpf, String nome, String rg, String setor, String senha) {

		try {

			Funcionario l;
			l = servidor.buscarInformacoesFuncionario(cpf);

			if (l != null) {
				JOptionPane.showMessageDialog(null, "CPF já cadastrado");
				return;
			}

			int i = servidor.inserirFuncionario(FuncPrincipal.CPF, nome, rg, cpf, senha, setor);

			if (i == 1) {
				JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Erro na inserção");
			}

		} catch (NumberFormatException | RemoteException e) {
			JOptionPane.showMessageDialog(null, "Erro na inserção");
			e.printStackTrace();
		}

	}

	public void desconectar() {
		try {
			servidor.desconectarFunc(FuncPrincipal.CPF);
		} catch (NumberFormatException | RemoteException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Funcionario> tabelaFuncionario(String botao) {

		ObservableList<Funcionario> listaR = FXCollections.observableArrayList();

		try {
			ArrayList<Funcionario> lista = servidor.buscarInformacoesTodosFuncionario();

			for (int i = 0; i < lista.size(); i++) {

				Funcionario f = new Funcionario();

				f.setCPF(lista.get(i).getCPF());
				f.setId(lista.get(i).getId());
				f.setNome(lista.get(i).getNome());
				f.setRG(lista.get(i).getRG());
				f.setSenha(lista.get(i).getSenha());
				f.setSetor(lista.get(i).getSetor());
				f.setBtnEditarDeletar(new Button(botao));
				f.getBtnEditarDeletar().setPrefWidth(95);

				listaR.add(f);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return listaR;
	}

	public boolean deletarFuncionario(String id) {

		try {
			return servidor.deletarFuncionario(FuncPrincipal.CPF, id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarFuncionario(String cpfAntigo, String nome, String rg, String cpf, String senha, String setor) {
		try {
			return servidor.editarFuncionario(FuncPrincipal.CPF, cpfAntigo, nome, rg, cpf, senha, setor);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean inserirCliente(String cpfResponsavel, String nome, String rg, String cpf, String telefone,
			String senha) {
		try {

			Cliente a = servidor.buscarInformacoesCliente(cpf);

			if (a != null) {
				JOptionPane.showMessageDialog(null, "CPF já cadastrado");
				return false;
			}

			boolean i = servidor.inserirCliente(cpfResponsavel, nome, rg, cpf, telefone, senha);

			if (i) {
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Erro no cadastro");
			}

			return true;
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Erro no cadastro");
			e.printStackTrace();
			return false;
		}
	}

	public ObservableList<Cliente> tabelaCliente(String botao) {

		ObservableList<Cliente> listaR = FXCollections.observableArrayList();

		try {
			ArrayList<Cliente> lista = servidor.buscarInformacoesTodosClientes();

			for (int i = 0; i < lista.size(); i++) {

				Cliente f = new Cliente();

				f.setCPF(lista.get(i).getCPF());
				f.setId(lista.get(i).getId());
				f.setNome(lista.get(i).getNome());
				f.setRG(lista.get(i).getRG());
				f.setSenha(lista.get(i).getSenha());
				f.setTelefone(lista.get(i).getTelefone());
				f.setBtnEditarDeletar(new Button(botao));
				f.getBtnEditarDeletar().setPrefWidth(95);

				listaR.add(f);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return listaR;
	}
	
	public Cliente buscaClienteID(String id) {
		try {
			return servidor.buscarInformacoesClienteID(id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean editarCliente(String cpfResponsavel, String cpfAntigo, String nome, String rg, String cpf,
			String senha, String telefone) {
		try {

			return servidor.editarCliente(cpfResponsavel, cpfAntigo, nome, rg, cpf, telefone, senha);

		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarCliente(String cpfResponsavel, String id) {

		try {
			return servidor.deletarCliente(cpfResponsavel, id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean existeCliente(String cpf) {

		Cliente l = null;
		try {
			l = servidor.buscarInformacoesCliente(cpf);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (l != null);
	}

	public boolean inserirProduto(String cpfResponsavel, String nome, String descricao, double preco, int quantidade,
			String validade, String tipo) {

		try {
			return servidor.inserirProduto(cpfResponsavel, nome, descricao, preco, quantidade, validade, tipo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarProduto(String cpfResponsavel, String id, String nome, String descricao, double preco,
			int quantidade, String validade, String tipo) {

		try {
			return servidor.editarProduto(cpfResponsavel, id, nome, descricao, preco, quantidade, validade, tipo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarProduto(String cpfResponsavel, String id) {

		try {
			return servidor.deletarProduto(cpfResponsavel, id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ObservableList<Produto> tabelaProduto(String botao) {

		ObservableList<Produto> listaR = FXCollections.observableArrayList();

		try {
			ArrayList<Produto> lista = servidor.buscarInformacoesTodosProdutos();

			for (int i = 0; i < lista.size(); i++) {

				Produto p = new Produto();

				p.setID(lista.get(i).getID());
				p.setNOME(lista.get(i).getNOME());
				p.setDESCRICAO(lista.get(i).getDESCRICAO());
				p.setPreco(lista.get(i).getPreco());
				p.setQuantidade(lista.get(i).getQuantidade());
				p.setVALIDADE(lista.get(i).getVALIDADE());
				p.setTIPO(lista.get(i).getTIPO());
				p.setBtnEditarDeletar(new Button(botao));
				p.getBtnEditarDeletar().setPrefWidth(78);

				listaR.add(p);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return listaR;
	}

	public boolean abrirConta(String cpfResponsavel, String cpfCliente, String mesa) {
		try {
			return servidor.inserirConta(cpfResponsavel, cpfCliente, mesa);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}

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

	public ArrayList<Pedido> buscaTodosPedidos() {

		ArrayList<Pedido> listaR = null;
		try {
			listaR = servidor.buscarTodosPedidos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaR;
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
	
	public void fecharConta(String cpfResponsavel,String idConta,String cpfCliente){
		try {
			servidor.finalizarConta(cpfResponsavel, idConta);
			servidor.removerCliente(cpfCliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
