package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExemploChart extends Application {

	
	public static void main(String[] args) {
		launch();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AnchorPane painel = new AnchorPane();
		PieChart grafico = new PieChart();
		
		ObservableList<PieChart.Data> dados = FXCollections.observableArrayList(
				
				new PieChart.Data("Java", 18),
				new PieChart.Data("C", 17),
				new PieChart.Data("Objective-C", 10),
				new PieChart.Data("C++", 9),
				new PieChart.Data("C#", 6),
				new PieChart.Data("Outras", 40)
				
				);
		
		
		grafico.setData(dados);
		
		grafico.setTitle("Ranking de Linguagens de programação março/2013");
		grafico.setPrefSize(600, 600);
		grafico.setTranslateX(300);
		grafico.setTranslateY(80);
		grafico.setAnimated(true);
		grafico.setLabelLineLength(50.0);
		grafico.setLegendSide(Side.LEFT);
		
		
		grafico.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event evento){
				
				double valor = grafico.getData().get(5).getPieValue() -3;
				grafico.getData().get(5).setPieValue(valor);				
			};
			
		});
		
		
		grafico.setClockwise(false);
		
		grafico.setStartAngle(90);
	//	grafico.setRotate(45);
		grafico.setLabelsVisible(false);
		grafico.setLegendVisible(false);
		
		
		
		painel.getChildren().add(grafico);
		Scene cena = new Scene(painel,1200,800);
	//	grafico.setStyle("-fx-background-color: lightgray");
		cena.getStylesheets().add("javafx/chart.css");
		
		primaryStage.setTitle("Exemplo pie chart");
		primaryStage.setScene(cena);
		primaryStage.show();
		
		
		
		
		
		
	}

}
