package visao.relatorio;

import java.util.ArrayList;

import TipoDado.Caixa;
import TipoDado.ItemPedido;
import TipoDado.Pedido;
import implementacao.Remota;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControleGrafico {
	
	
	private static ControleGrafico instancia = null;

	private ControleGrafico() {
	}

	public static synchronized ControleGrafico getInstance() {

		if (instancia == null) {
			instancia = new ControleGrafico();
		}

		return instancia;

	}
	
	

	public ArrayList<Caixa> DinheiroPorDia() {

		ArrayList<Pedido> lista = Remota.getInstance().buscaTodosPedidos();
		ObservableList<ItemPedido> listaItens = Remota.getInstance().buscaItensPedidos();

		ArrayList<Caixa> listaCaixa = new ArrayList<>();

		for (int i = 0; i < lista.size(); i++) {

			Double valor = 0.0;

			for (int j = 0; j < listaItens.size(); j++) {

				if (lista.get(i).getId_pedido().toString().equals(listaItens.get(j).getId_Pedido())) {
					valor += listaItens.get(j).getPrecototal();
				}
			}

			Caixa c = new Caixa();
			c.setDia(lista.get(i).getData());
			c.setValor(valor);

			listaCaixa.add(c);
		}

		for (int i = 0; i < listaCaixa.size(); i++) {
			for (int j = 0; j < listaCaixa.size(); j++) {

				if (i != j && listaCaixa.get(i).getDia().equals(listaCaixa.get(j).getDia())) {
					listaCaixa.get(i).setValor(listaCaixa.get(i).getValor() + listaCaixa.get(j).getValor());
					listaCaixa.remove(j);
				}

			}
		}
		
		
		for (int i = 0; i < listaCaixa.size(); i++) {

				String data = listaCaixa.get(i).getDia().substring(8, 10) + "/"
						+ listaCaixa.get(i).getDia().substring(5, 7) + "/" + listaCaixa.get(i).getDia().substring(0, 4);

				listaCaixa.get(i).setDia(data);
			

		}

		return listaCaixa;

	}

	Stage palco;

	@SuppressWarnings("unchecked")
	public void grafico() {

		palco = new Stage();
		palco.setTitle("Grafico");
		palco.setResizable(false);

		AnchorPane pane = new AnchorPane();

		// cor backgorund da janela
		pane.setStyle("-fx-background-color: #FFFFFF;");
		
		LineChart<String, Number> graficoLinha = new LineChart<>(
				  new CategoryAxis(), new NumberAxis());
		
		@SuppressWarnings("rawtypes")
		XYChart.Series prod1 = new XYChart.Series();
		prod1.setName("Dia/Vendas");
		
		ArrayList<Caixa> l= DinheiroPorDia();

		for (int i = 0; i < l.size(); i++) {
			prod1.getData().add(new XYChart.Data<String, Double>(l.get(i).getDia(), l.get(i).getValor()));
		}
		
		while(prod1.getData().size()>50) {
			prod1.getData().remove(0);
		}
		

		graficoLinha.getData().addAll(prod1);
		graficoLinha.setPrefSize(890, 595);
		
		pane.getChildren().add(graficoLinha);

		Scene cena = new Scene(pane, 890, 595);

		palco.setScene(cena);

		palco.show();

	}

}
