package implementacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


import TipoDado.Cliente;
import TipoDado.Funcionario;
import TipoDado.ItemPedido;
import TipoDado.Pedido;
import TipoDado.PessoaTabela;
import TipoDado.Produto;
import banco.acesso.TabCliente;
import banco.acesso.TabFuncionario;
import banco.acesso.TabItemPedido;
import banco.acesso.TabPedido;
import banco.acesso.TabProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import visao.servidor.ControleServidor;

public class ServidorImplementacao extends UnicastRemoteObject implements ServidorInterface {

	private static final long serialVersionUID = -8757329442966065370L;

	public static ObservableList<PessoaTabela> listaClientes = FXCollections.observableArrayList();
	public static ObservableList<PessoaTabela> listaFuncionario = FXCollections.observableArrayList();

	public ServidorImplementacao() throws RemoteException {
		super();
	}

	@Override
	public boolean inserirCliente(String cpfResponsavel, String nome, String rg, String cpf, String telefone,
			String senha) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);

		atualizaLog("Inserir Cliente", f.getNome());

		return TabCliente.getInstance().inserirCliente(nome, rg, cpf, telefone, senha);

	}

	@Override
	public boolean editarCliente(String cpfResponsavel, String cpfAntigo, String nome, String rg, String cpfNovo,
			String telefone, String senha) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);

		atualizaLog("Editar Cliente", f.getNome());

		return TabCliente.getInstance().editarCliente(cpfAntigo, nome, rg, cpfNovo, telefone, senha);
	}

	@Override
	public boolean deletarCliente(String cpfResponsavel, String id) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);

		atualizaLog("Deletar Cliente", f.getNome());

		return TabCliente.getInstance().deletarCliente(id);
	}

	@Override
	public void desconectarCliente(String cpf) throws RemoteException {

		for (int i = 0; i < listaClientes.size(); i++) {
			if (cpf.equals(listaClientes.get(i).getCpf())) {
				atualizaLog("Desconectar Cliente", listaClientes.get(i).getNome());
				listaClientes.remove(i);
				i = listaClientes.size();
			}
		}

	}

	@Override
	public int conectarCliente(String cpf, String senha, String ipCliente, String portaCliente) throws RemoteException {
		Cliente c = TabCliente.getInstance().buscarInformacoesCliente(cpf);
		if (c == null)
			return 0;
		if (!c.getSenha().equals(senha))
			return 0;

		PessoaTabela p = new PessoaTabela();
		p.setCpf(cpf);
		p.setIp(ipCliente);
		p.setNome(c.getNome());
		p.setPorta(portaCliente);

		listaClientes.add(p);

		atualizaLog("Conectar Cliente", c.getNome());

		return 1;
	}

	// 0 - não fez login
	// 1 - funcionario normal
	// 2 - administrador
	@Override
	public int conectarFuncionario(String cpf, String senha, String ipFunc, String portaFunc) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpf);
		if (f == null) {
			return 0;
		}
		if (!f.getSenha().equals(senha)) {
			return 0;
		}

		PessoaTabela p = new PessoaTabela();
		p.setCpf(cpf);
		p.setIp(ipFunc);
		p.setNome(f.getNome());
		p.setPorta(portaFunc);

		listaFuncionario.add(p);

		atualizaLog("Conectar Funcionário", f.getNome());

		if (f.getSetor().equals("Administrador")) {
			return 2;
		} else {
			return 1;
		}

	}

	@Override
	public void desconectarFunc(String cpf) throws RemoteException {

		for (int i = 0; i < listaFuncionario.size(); i++) {
			if (cpf.equals(listaFuncionario.get(i).getCpf())) {
				atualizaLog("Desconectar Funcionário", listaFuncionario.get(i).getNome());
				listaFuncionario.remove(i);
				i = listaFuncionario.size();
			}
		}

	}

	@Override
	public int inserirFuncionario(String cpfResponsavel, String nome, String rg, String cpf, String senha, String setor)
			throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);

		atualizaLog("Inserir Funcionário", f.getNome());

		return TabFuncionario.getInstance().inserirFuncionario(nome, rg, cpf, senha, setor);
	}

	@Override
	public boolean editarFuncionario(String cpfResponsavel, String cpfAntigo, String nome, String rg, String cpf,
			String senha, String setor) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Editar Funcionário", f.getNome());

		return TabFuncionario.getInstance().editarFuncionario(cpfAntigo, nome, rg, cpf, senha, setor);
	}

	@Override
	public Funcionario buscarInformacoesFuncionario(String cpf) throws RemoteException {

		Funcionario x = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpf);

		return x;
	}

	@Override
	public ArrayList<Funcionario> buscarInformacoesTodosFuncionario() throws RemoteException {
		return TabFuncionario.getInstance().buscarInformacoesTodosFuncionario();
	}

	@Override
	public boolean deletarFuncionario(String cpfResponsavel, String id) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Deletar Funcionário", f.getNome());

		return TabFuncionario.getInstance().deletarFuncionario(id);
	}

	@Override
	public Cliente buscarInformacoesCliente(String cpf) throws RemoteException {
		return TabCliente.getInstance().buscarInformacoesCliente(cpf);
	}

	@Override
	public ArrayList<Cliente> buscarInformacoesTodosClientes() throws RemoteException {
		return TabCliente.getInstance().buscarInformacoesTodosClientes();
	}

	@Override
	public boolean inserirProduto(String cpfResponsavel, String nome, String descricao, double preco, int quantidade,
			String validade, String tipo) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Inserir produto", f.getNome());

		return TabProduto.getInstance().inserirProduto(nome, descricao, preco, quantidade, validade, tipo);
	}

	@Override
	public boolean editarProduto(String cpfResponsavel, String id, String nome, String descricao, double preco,
			int quantidade, String validade, String tipo) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		if (f == null) {
			Cliente c = TabCliente.getInstance().buscarInformacoesCliente(cpfResponsavel);
			atualizaLog("Editar produto", c.getNome());
		} else {
			atualizaLog("Editar produto", f.getNome());
		}

		return TabProduto.getInstance().editarProduto(id, nome, descricao, preco, quantidade, validade, tipo);
	}

	@Override
	public boolean deletarProduto(String cpfResponsavel, String id) throws RemoteException {

		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Deletar produto", f.getNome());
		return TabProduto.getInstance().deletarProduto(id);
	}

	@Override
	public ArrayList<Produto> buscarInformacoesTodosProdutos() throws RemoteException {
		return TabProduto.getInstance().buscarInformacoesTodosProdutos();
	}

	@Override
	public boolean inserirConta(String cpfResponsavel, String cpfCliente, String mesa) throws RemoteException {
		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Abrir Conta", f.getNome());
		return TabPedido.getInstance().inserirConta(cpfCliente, mesa);
	}

	@Override
	public boolean finalizarConta(String cpfResponsavel, String idConta) throws RemoteException {
		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Finalizar Conta", f.getNome());
		return TabPedido.getInstance().finalizarConta(idConta);
	}

	@Override
	public boolean editarConta(String cpfResponsavel, String idContaAntigo, String idCliente, String mesa)
			throws RemoteException {
		Funcionario f = TabFuncionario.getInstance().buscarInformacoesFuncionario(cpfResponsavel);
		atualizaLog("Editar Conta", f.getNome());
		return TabPedido.getInstance().editarConta(idContaAntigo, idCliente, mesa);
	}

	@Override
	public ArrayList<Pedido> buscarTodosPedidos() throws RemoteException {
		return TabPedido.getInstance().buscarTodosPedidos();
	}

	@Override
	public boolean inserirItemPedido(String cpfResponsavel, String id_produto, String id_pedido, int quantidade)
			throws RemoteException {
		Cliente c = TabCliente.getInstance().buscarInformacoesCliente(cpfResponsavel);
		atualizaLog("Pedido de produto", c.getNome());

		return TabItemPedido.getInstance().inserirItemPedido(id_produto, id_pedido, quantidade);
	}

	@Override
	public Produto buscarProduto(String id_produto) throws RemoteException {
		return TabProduto.getInstance().buscarProduto(id_produto);
	}

	public static String dataHoraAtual() {
		Calendar atual = GregorianCalendar.getInstance(Locale.US);

		String dia, mes, ano;

		dia = String.valueOf(atual.get(Calendar.DAY_OF_MONTH));
		mes = String.valueOf(atual.get(Calendar.MONTH) + 1);
		ano = String.valueOf(atual.get(Calendar.YEAR));

		if (dia.length() == 1)
			dia = "0" + dia;
		if (mes.length() == 1)
			mes = "0" + mes;

		String data = dia + "/" + mes + "/" + ano;

		String hora = String.valueOf(atual.get(Calendar.HOUR_OF_DAY));
		String minutos = String.valueOf(atual.get(Calendar.MINUTE));
		String segundos = String.valueOf(atual.get(Calendar.SECOND));

		String tempo = hora + ":" + minutos + ":" + segundos;

		return data + "  -  " + tempo;

	}

	public void atualizaLog(String acao, String ator) {
		ControleServidor.log += ator + "  -  " + acao + "  -  " + dataHoraAtual() + "\n";
	}

	@Override
	public int buscaIdPedido(String id_cliente) throws RemoteException {
		return TabPedido.getInstance().buscaIdPedido(id_cliente);
	}

	@Override
	public ArrayList<ItemPedido> buscarInformacoesTodosItensPedidos() throws RemoteException {
		return TabItemPedido.getInstance().buscarInformacoesTodosItensPedidos();
	}

	@Override
	public Cliente buscarInformacoesClienteID(String id) throws RemoteException {
		return TabCliente.getInstance().buscarInformacoesClienteID(id);
	}

	@Override
	public Pedido buscaPedido(String idPedido) throws RemoteException {
		// TODO Auto-generated method stub
		return TabPedido.getInstance().buscaPedido(idPedido);
	}

}
