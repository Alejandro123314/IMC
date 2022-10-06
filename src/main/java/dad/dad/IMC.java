package dad;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	DoubleProperty pesoP = new SimpleDoubleProperty();
	DoubleProperty alturaP = new SimpleDoubleProperty();
	DoubleProperty imcP = new SimpleDoubleProperty();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	
		Label formaF = new Label("FORMA");
		Label imcL = new Label();
		Label pesoU = new Label(" kg");
		Label alturaU = new Label(" cm");
		Label pesoL = new Label("Peso: ");
		Label alturaL = new Label("Altura: ");
		TextField peso = new TextField();
		TextField altura = new TextField();
		
		peso.maxWidth(100);

		HBox pesoH = new HBox(pesoL,peso,pesoU);
		HBox alturaH = new HBox(alturaL,altura,alturaU);
		HBox imcH = new HBox(imcL);
		HBox formaFH = new HBox(formaF);
		
		
		
		
		pesoH.setAlignment(Pos.CENTER);
		alturaH.setAlignment(Pos.CENTER);
		imcH.setAlignment(Pos.CENTER);
		formaFH.setAlignment(Pos.CENTER);
		
		VBox caja = new VBox(9, pesoH , alturaH , imcH, formaFH);
		caja.setAlignment(Pos.CENTER);
		
		imcL.textProperty().bind(imcP.asString("%.2f"));
		
		imcP.bind(
				Bindings.when(alturaP.greaterThan(0))
				.then(pesoP.divide(alturaP.multiply(alturaP)))
				.otherwise(0)
		);
	
		peso.textProperty().bindBidirectional(pesoP, new NumberStringConverter());
		altura.textProperty().bindBidirectional(alturaP, new NumberStringConverter());
	
		
		
		//Bindings.bindBidirectional(peso.textProperty(), pesoP, new NumberStringConverter());
		
		Scene scene = new Scene(caja, 320, 220);
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

