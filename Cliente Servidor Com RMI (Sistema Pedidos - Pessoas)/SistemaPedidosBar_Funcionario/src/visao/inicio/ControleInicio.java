package visao.inicio;

import implementacao.MenuFuncionario;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;


public class ControleInicio {

	@FXML
	Accordion AccMenu;

	@FXML
	public void initialize() {
		MenuFuncionario m = MenuFuncionario.getInstance();
		AccMenu.getPanes().addAll(m.criarMenuFuncionario());
	}
}
