package implementacao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import TipoDado.Cliente;
import TipoDado.Funcionario;
import TipoDado.ItemPedido;
import TipoDado.Pedido;
import TipoDado.Produto;

public interface ServidorInterface extends Remote {
	
	
	
	// ITEM PEDIDO
	
	public boolean inserirItemPedido(String cpfResponsavel,String id_produto,String id_pedido,int quantidade) throws RemoteException;
	public ArrayList<ItemPedido> buscarInformacoesTodosItensPedidos() throws RemoteException;
	

	//CONTA
	public boolean inserirConta(String cpfResponsavel,String cpfCliente, String mesa) throws RemoteException;
	
	public boolean finalizarConta(String cpfResponsavel,String idConta) throws RemoteException;
	
	public boolean editarConta(String cpfResponsavel,String idContaAntigo,String idCliente,String mesa) throws RemoteException;
	
	public ArrayList<Pedido> buscarTodosPedidos() throws RemoteException;
	
	public int buscaIdPedido(String id_cliente)throws RemoteException;
	
	// PRODUTO
	public boolean inserirProduto(String cpfResponsavel,String nome, String descricao, double preco, int quantidade, String validade,String tipo)
			throws RemoteException;

	public boolean editarProduto(String cpfResponsavel,String id, String nome, String descricao, double preco, int quantidade,
			String validade,String tipo) throws RemoteException;

	public ArrayList<Produto> buscarInformacoesTodosProdutos() throws RemoteException;
	
	public boolean deletarProduto(String cpfResponsavel,String id)throws RemoteException;
	
	
	public Produto buscarProduto(String id_produto)throws RemoteException;
	
	// Cliente

	public boolean inserirCliente(String cpfResponsavel, String nome, String rg, String cpf, String telefone,
			String senha) throws RemoteException;

	public boolean editarCliente(String cpfResponsavel, String cpfAntigo, String nome, String rg, String cpfNovo,
			String telefone, String senha) throws RemoteException;

	public boolean deletarCliente(String cpfResponsavel, String id) throws RemoteException;

	public int conectarCliente(String cpf, String senha, String ipCliente, String portaCliente)
			throws RemoteException;

	public void desconectarCliente(String cpf) throws RemoteException;

	public Cliente buscarInformacoesCliente(String cpf) throws RemoteException;

	public ArrayList<Cliente> buscarInformacoesTodosClientes() throws RemoteException;
	
	public Cliente buscarInformacoesClienteID(String id) throws RemoteException;
	
	public void removerCliente(String cpf) throws RemoteException;

	// Funcionario

	public int inserirFuncionario(String cpfResponsavel, String nome, String rg, String cpf, String senha, String setor)
			throws RemoteException;

	public boolean editarFuncionario(String cpfResponsavel, String cpfAntigo, String nome, String rg, String cpf,
			String senha, String setor) throws RemoteException;

	public Funcionario buscarInformacoesFuncionario(String cpf) throws RemoteException;

	public ArrayList<Funcionario> buscarInformacoesTodosFuncionario() throws RemoteException;

	public boolean deletarFuncionario(String cpfResponsavel, String id) throws RemoteException;

	public int conectarFuncionario(String cpf, String senha, String ipFunc, String portaFunc) throws RemoteException;

	public void desconectarFunc(String cpf) throws RemoteException;
}