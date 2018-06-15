package visao.inserirCliente;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JOptionPane;

import implementacao.FuncPrincipal;
import implementacao.MenuFuncionario;
import implementacao.Remota;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControleInserirCliente {

	@FXML
	Label lblTitulo, lblStatusSistema;

	@FXML
	Accordion AccMenu;

	@FXML
	AnchorPane AncorMenu, AncorPrincipal;

	@FXML
	TextField txtNome, txtCPF, txtRG, txtTelefone, txtSenha;

	@FXML
	DatePicker dateNasc;

	@FXML
	Button btnInserir;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());

		btnInserir.setOnAction(e -> inserirDados());

	}

	private void inserirDados() {

		if (txtNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo nome não pode ficar em branco!");
			return;
		}else if(txtNome.getText().length() >100){
			JOptionPane.showMessageDialog(null, "O campo nome deve ter no maximo 100 caracteres!");
			return;
			
		}
		if (txtCPF.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo CPF não pode ficar em branco!");
			return;
		}else if(txtCPF.getText().length() != 11){
			JOptionPane.showMessageDialog(null, "O campo CPF não tem valor valido!");
			return;
		}
		if (txtRG.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo RG não pode ficar em branco!");
			return;
		}else if(txtRG.getText().length() >20){
			JOptionPane.showMessageDialog(null, "O campo RG não tem valor valido!");
			return;
		}
		if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo senha não pode ficar em branco!");
			return;
		}else if(txtSenha.getText().length() >16){
			JOptionPane.showMessageDialog(null, "O campo senha deve ter no maximo 16 caracteres!");
			return;
		}
		if (txtTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo telefone não pode ficar em branco!");
			return;
		}else if(txtTelefone.getText().length() >11){
			JOptionPane.showMessageDialog(null, "O campo telefone tem no maximo 11 numeros, DD + numero!");
			return;
		}
		
		// 18 anos atras
		Calendar requisito = GregorianCalendar.getInstance(Locale.US);
		requisito.set(Calendar.YEAR, requisito.get(Calendar.YEAR)-18);
		
		
		Calendar date = GregorianCalendar.getInstance(Locale.US);
		
	
		date.set(Calendar.YEAR, Integer.parseInt(dateNasc.getValue().toString().substring(0,4)));
		
		date.set(Calendar.MONTH, Integer.parseInt(dateNasc.getValue().toString().substring(5,7))-1);
		
		date.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateNasc.getValue().toString().substring(8,10)));
		
		
		if(date.compareTo(requisito) == 1){
			JOptionPane.showMessageDialog(null, "O cliente precisa ter pelo menos 18 anos!");
			return;
		}

		Remota.getInstance().inserirCliente(FuncPrincipal.CPF,txtNome.getText(),
		txtRG.getText(), txtCPF.getText(), txtTelefone.getText(),
		txtSenha.getText());
	}


}
