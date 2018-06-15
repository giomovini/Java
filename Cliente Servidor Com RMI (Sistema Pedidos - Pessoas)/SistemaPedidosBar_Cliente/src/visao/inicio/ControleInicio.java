package visao.inicio;

import implementacao.MenuCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;

public class ControleInicio {

	@FXML
	Accordion AccMenu;

	@FXML
	public void initialize() {
		MenuCliente m = MenuCliente.getInstance();
		
		AccMenu.getPanes().addAll(m.criarMenuCliente());
	}
}
