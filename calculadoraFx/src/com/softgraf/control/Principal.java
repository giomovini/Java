package com.softgraf.control;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

public class Principal extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Metodo main classe principal
	 * @param main
	 * @return void
	 * @deprecated
	 * 
	 * */

	@Override
	public void start(Stage palco) throws Exception {
		
		URL fxml = this.getClass().getResource("/com/softgraf/view/TelaCalc.fxml");
		Parent painel = (Parent) FXMLLoader.load(fxml);
		
		palco.setScene(new Scene(painel,173,232));
		palco.setTitle("Calculadora FX");
		palco.setResizable(false);
		palco.show();

	}
	/**
	 * Metodo start classe principal
	 * @return void
	 * @deprecated
	 * 
	 * */

}
