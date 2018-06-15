package visao.abrirConta;

import javax.swing.JOptionPane;

import TipoDado.Cliente;
import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControleAbirConta {

	@FXML
	Accordion AccMenu;
	
	@FXML
	AnchorPane AncorPrincipal;

	@FXML
	TableView<Cliente> tblCliente;

	@FXML
	TableColumn<Cliente, String> colNome, colRG, colCPF, colTelefone;
	
	@FXML
	TextField txtCPF,txtMesa;
	
	@FXML
	Button btnSalvar;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colRG.setCellValueFactory(new PropertyValueFactory<>("RG"));
		colCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));

		inserirDadosTabela();
		
		AncorPrincipal.setOnMouseMoved(e->atualizaCPF());
		
		btnSalvar.setOnAction(e->inserir());

	}
	
	public void atualizaCPF(){
		
		try{
			txtCPF.setText(tblCliente.getSelectionModel().getSelectedItem().getCPF());
		}catch(Exception e){
			
		}
	}
	
	public void inserirDadosTabela(){
		
		ObservableList<Cliente> lista = Remota.getInstance().tabelaCliente("");

		tblCliente.setItems(lista);
		
	}
	
	public void inserir(){
		
		if(txtCPF.getText().equals("")){
			JOptionPane.showMessageDialog(null, "O campo CPF não pode ser vazio!");
			return;
		}else{
			boolean b = Remota.getInstance().existeCliente(txtCPF.getText());
			if(!b){
				JOptionPane.showMessageDialog(null, "CPF não cadastrado ou inválido!");
				return;
			}
		}
		
		if(txtMesa.getText().equals("")){
			JOptionPane.showMessageDialog(null, "O campo mesa não pode ser vazio!");
			return;
		}
		
		
		Cliente c = Remota.getInstance().buscaCliente(txtCPF.getText());
		
		int id_pedido = Remota.getInstance().id_pedido(c.getId());
		
		if(id_pedido != 0){
			JOptionPane.showMessageDialog(null, "Cliente já possui uma conta aberta!");
			return;
		}
		
		
		boolean b = Remota.getInstance().abrirConta(FuncPrincipal.CPF, txtCPF.getText(), txtMesa.getText());
	
		if(b){
			JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Falha na criação da conta!");
		}
		
	}
	

}
