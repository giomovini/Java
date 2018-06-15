package visao.inserirProduto;

import javax.swing.JOptionPane;

import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleInserirProduto {
	
	@FXML
	TextField txtNome,txtPreco,txtquantidade;
	
	@FXML
	TextArea txtDescricao;
	
	@FXML
	DatePicker dateValidade;
	
	@FXML
	Accordion AccMenu;

	@FXML
	Button btnInserir;
	
	@FXML
	MenuButton menuTipo;
	
	@FXML
	public void initialize(){
		
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());
		
		btnInserir.setOnAction(e -> inserirDados());
		
		MenuItem bebidas = new MenuItem("Bebida");
		MenuItem porcoes = new MenuItem("Porção");
		MenuItem doces = new MenuItem("Doce");
		MenuItem lanches = new MenuItem("Lanche");
		
		
		menuTipo.getItems().addAll(bebidas,porcoes,doces,lanches);
		
		bebidas.setOnAction(e->menuTipo.setText(bebidas.getText()));
		porcoes.setOnAction(e->menuTipo.setText(porcoes.getText()));
		doces.setOnAction(e->menuTipo.setText(doces.getText()));
		lanches.setOnAction(e->menuTipo.setText(lanches.getText()));
	}
	
	public void inserirDados(){
		double preco = 0;
		int quantidade = 0;
		
		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!");
			return;
		}
		if (txtDescricao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo descrição não pode ficar em branco!");
			return;
		}
		try{
			preco = Double.parseDouble(txtPreco.getText().replaceAll(",", "."));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo preco não tem valor coerente!");
			return;
		}
		if(menuTipo.getText().equals("Selecione")){
			JOptionPane.showMessageDialog(null, "O campo tipo não tem um item adequado escolhido!");
			return;
		}
		
		try{
			quantidade = Integer.parseInt(txtquantidade.getText());
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo quantidade não tem valor coerente!");
			return;
		}
		
		boolean b;
		
		if(dateValidade.getValue() != null)
			b = Remota.getInstance().inserirProduto(FuncPrincipal.CPF, txtNome.getText(), txtDescricao.getText(), preco, quantidade, dateValidade.getValue().toString(),menuTipo.getText());
		else
			b = Remota.getInstance().inserirProduto(FuncPrincipal.CPF, txtNome.getText(), txtDescricao.getText(), preco, quantidade, null,menuTipo.getText());
		
		if(b){
			JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
		}else{
			JOptionPane.showMessageDialog(null, "Falha na inserção do produto!");
		}
	
	}

	
}
