package com.softgraf.control;

import com.softgraf.model.Expressao;
import com.softgraf.model.Fonte;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * classe principal para execucao da calculadora
 * componentes injetados
 * 			btnBack, btnClear, btnMenos, btnDiv, btnMult, btnIgual, btnVirgula, btnMais, btnZero, btnUm, btnDois,
			btnTres, btnQuatro, btnCinco, btnSeis, btnSete, btnOito, btnNove
 * 
 * */



public class ControlleApp {

	@FXML
	Button btnBack, btnClear, btnMenos, btnDiv, btnMult, btnIgual, btnVirgula, btnMais, btnZero, btnUm, btnDois,
			btnTres, btnQuatro, btnCinco, btnSeis, btnSete, btnOito, btnNove;

	@FXML
	TextField txtVisor;

	private Expressao expressao;
	private String teclas;

	@FXML
	public void initialize() {

		expressao = new Expressao();

		teclas = "BC-/*=,+0123456789";

		txtVisor.setEditable(false);
		definirBotoes();

		txtVisor.setOnKeyPressed(e -> acaoTeclado(e));

	}

	private void acaoTeclado(KeyEvent e) {

		if (e.getCode() == KeyCode.ENTER) {
			guardarDigito('=');
		} else if (e.getCode() == KeyCode.BACK_SPACE) {
			guardarDigito('B');
		} else if (e.getText().length() > 0) {

			char digito = e.getText().toUpperCase().charAt(0);
			if (teclas.contains(Character.toString(digito)))
				guardarDigito(digito);

		}

	}

	private void guardarDigito(char digito) {

		expressao.guardarDigito(digito);

		txtVisor.setText(expressao.getExpressao());

		if (expressao.getLength() < 10) {
			txtVisor.setFont(Fonte.GRANDE);
		} else if (expressao.getLength() > 11 && expressao.getLength() < 16) {
			txtVisor.setFont(Fonte.MEDIA);
		} else if (expressao.getLength() > 15 && expressao.getLength() < 21) {
			txtVisor.setFont(Fonte.PEQUENA);
		} else if (expressao.getLength() > 20) {
			txtVisor.setFont(Fonte.MINIMA);
		}

	}

	private void definirBotoes() {
		Button botoes[] = { btnBack, btnClear, btnMenos, btnDiv, btnMult, btnIgual, btnVirgula, btnMais, btnZero, btnUm,
				btnDois, btnTres, btnQuatro, btnCinco, btnSeis, btnSete, btnOito, btnNove };
		for (int i = 0; i < botoes.length; i++) {

			char digito = teclas.charAt(i);
			botoes[i].setOnAction(e -> guardarDigito(digito));

		}

	}

}
