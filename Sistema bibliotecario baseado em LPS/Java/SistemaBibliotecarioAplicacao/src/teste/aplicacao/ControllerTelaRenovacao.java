package teste.aplicacao;


import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import aplicacao.renovacao.Renovacao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import teste.ControllerTelaInicial;
import teste.Tela;

public class ControllerTelaRenovacao {

	@FXML
	DatePicker DataDevolucao;
	
	@FXML 
	Button Salvar, Voltar;


	@FXML
	public void initialize() {

		Voltar.setOnAction(e -> {
			Tela.getInstance().getPalcoSobreposto().close();
		});

		Salvar.setOnAction(e->{
			if(DataDevolucao.getValue() != null) {
				Date data = Date.from(DataDevolucao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				ControllerTelaInicial.Locacao_.setPrazoDevolucao(data);
				
				Renovacao renov = new Renovacao();
				boolean retorno = renov.realizarRenovacao(ControllerTelaInicial.Locacao_);
				if(retorno)
					ControllerTelaInicial.atualiza =true;
				Tela.getInstance().getPalcoSobreposto().close();
			}else {
				JOptionPane.showMessageDialog(null, "Preencha o campo solicitado para poder salvar!");
			}
			
			
		}); 
	}

}
