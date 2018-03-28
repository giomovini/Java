package javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExemploBarChart extends Application{
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		AnchorPane painel = new AnchorPane();
		
		// cria grafico de barra e define os tipos de dados
		BarChart<String, Number> grafico = new BarChart<>(new CategoryAxis(), new NumberAxis());
		
		grafico.setCategoryGap(20);
		grafico.setTitle("Ranking das linguagens de Programação Março/2013");
		
		XYChart.Data<String, Number> dadosJava = new XYChart.Data<String,Number>("Java",18);
		XYChart.Data<String, Number> dadosC = new XYChart.Data<String,Number>("C",17);
		XYChart.Data<String, Number> dadosObjectiveC = new XYChart.Data<String,Number>("Objective-C",17);
		XYChart.Data<String, Number> dadosCPP = new XYChart.Data<String,Number>("C++",9);
		XYChart.Data<String, Number> dadosCharp = new XYChart.Data<String,Number>("C#",6);
		XYChart.Data<String, Number> dadosOutras = new XYChart.Data<String,Number>("Outras",40);
		
		
		XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
		
		
		series.setName("Porcentagem %");
		series.getData().addAll(dadosJava,dadosC,dadosObjectiveC,dadosCPP,dadosCharp,dadosOutras);
		grafico.getData().add(series);
		
		painel.getChildren().add(grafico);
		
		stage.setTitle("Exemplo de barChart");
		stage.setScene(new Scene(painel,500,400));
		stage.show();
		
		
		
		
		
	}
	
	
	
	

	
	
	
	
}
