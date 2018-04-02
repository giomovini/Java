package javafx.painter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static java.lang.System.out;

import java.util.Random;

import com.sun.javafx.font.freetype.HBGlyphLayout;

public class PainterFX extends Application {

	private double raio = 5.0;
	private Color cor = Color.BLACK;
	private TipoCor tipoCor = TipoCor.NORMAL;
	private Random random = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage palco) throws Exception {

		int tamX, tamY;
		tamX = 1000;
		tamY = 800;

		AnchorPane painel = new AnchorPane();

		Circle circulo = new Circle(400.0, 200.0, raio, cor);

		painel.setOnMouseClicked((MouseEvent e) -> out.println("clicou " + e.getButton()));

		painel.setOnMouseMoved((MouseEvent e) -> out.println("Moveu X: " + e.getSceneX() + " Y: " + e.getSceneY()));

		painel.setOnMouseDragged((MouseEvent e) -> {
			out.println("Arrastou X: " + e.getSceneX() + " Y:" + e.getSceneY());
			// out.println(tipoCor);
			
				circulo.setCenterX(e.getSceneX());
				circulo.setCenterY(e.getSceneY());

				// se o botao direito do mouse for pressionado
				if (e.getButton() == MouseButton.PRIMARY) {
					double x = e.getSceneX();
					double y = e.getSceneY();

					Color novaCor;

					if (tipoCor == TipoCor.NORMAL)
						novaCor = cor;
					else if (tipoCor == TipoCor.PSICODELICA)
						novaCor = gerarCorPsicodelica(painel);
					else
						novaCor = gerarCorRandomica();

					Circle c = new Circle(x, y, raio, novaCor);
					painel.getChildren().addAll(c);
				}
			

		});

		painel.setOnScroll((ScrollEvent e) -> {
			out.println("Scroll delta Y: " + e.getDeltaY());

			double zoomFator = 1.05;
			double deltaY = e.getDeltaY();
			if (deltaY < 0) {
				zoomFator = 0.95;
			}
			raio = raio * zoomFator;
			for (Node node : painel.getChildren()) {
				if (node instanceof Circle) {
					Circle c = (Circle) node;
					c.setScaleX(c.getScaleX() * zoomFator);
					c.setScaleY(c.getScaleY() * zoomFator);
				}
			}

		});

		ColorPicker colorPicker = new ColorPicker();

		colorPicker.setOnAction(e -> {
			cor = colorPicker.getValue();
			circulo.setFill(cor);
		});

		Button btnTipoCor = new Button(tipoCor.toString());

		btnTipoCor.setOnAction(e -> {
			tipoCor = tipoCor.proxima();
			btnTipoCor.setText(tipoCor.toString());

		});

	

		Button btnlimpar = new Button("Limpar");

		btnlimpar.setOnAction(e -> {

			ObservableList<Node> lista = FXCollections.observableArrayList();
			for (Node node : painel.getChildren()) {

				if (node instanceof Circle && node != circulo) {
					lista.add(node);
				}

			}
			for (Node node : lista) {
				painel.getChildren().remove(node);
			}
		});

		painel.getChildren().addAll(new HBox(5, btnTipoCor, colorPicker, btnlimpar));

		palco.setResizable(false);
		palco.setScene(new Scene(painel, tamX, tamY));
		palco.setTitle("Painter FX");
		palco.show();
	}

	private Color gerarCorRandomica() {
		double r = random.nextDouble();
		double g = random.nextDouble();
		double b = random.nextDouble();
		double op = random.nextDouble();

		return new Color(r, g, b, op);

	}

	private Color gerarCorPsicodelica(AnchorPane painel) {

		for (Node node : painel.getChildren()) {
			if (node instanceof Circle)
				((Circle) node).setFill(gerarCorRandomica());

		}

		return gerarCorRandomica();
	}

}
