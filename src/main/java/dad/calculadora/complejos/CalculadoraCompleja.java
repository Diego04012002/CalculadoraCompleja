package dad.calculadora.complejos;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {
	
	private TextField operadorR;
	private TextField operadorR2;
	private TextField operadorI;
	private TextField operadorI2;
	private TextField resultadoR;
	private TextField resultadoI;
	private ComboBox<String> operadores;
	private Separator linea;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		operadorR= new TextField();
		operadorR.setPrefWidth(50);
		operadorR.setAlignment(Pos.CENTER);
		operadorR.setText("0");
		
		operadorR2= new TextField();
		operadorR2.setPrefWidth(50);
		operadorR2.setAlignment(Pos.CENTER);
		operadorR2.setText("0");
		
		operadorI= new TextField();
		operadorI.setPrefWidth(50);
		operadorI.setAlignment(Pos.CENTER);
		operadorI.setText("0");
		
		operadorI2= new TextField();
		operadorI2.setPrefWidth(50);
		operadorI2.setAlignment(Pos.CENTER);
		operadorI2.setText("0");
		
		resultadoR= new TextField();
		resultadoR.setPrefWidth(50);
		resultadoR.setAlignment(Pos.CENTER);
		resultadoR.setText("0");
		resultadoR.setDisable(true);
		
		resultadoI= new TextField();
		resultadoI.setPrefWidth(50);
		resultadoI.setAlignment(Pos.CENTER);
		resultadoI.setText("0");
		resultadoI.setDisable(true);
		
		linea= new Separator();
		linea.setPrefWidth(120);
		
		operadores= new ComboBox<String>();
		operadores.getItems().addAll("+", "-", "*", "/");
		
		HBox arriba = new HBox();
		arriba.setSpacing(5);
		arriba.setAlignment(Pos.CENTER);
		arriba.getChildren().addAll(operadorR, new Label("+"), operadorI, new Label("i"));
		
		HBox medio = new HBox();
		medio.setSpacing(5);
		medio.setAlignment(Pos.CENTER);
		medio.getChildren().addAll(operadorR2, new Label("+"), operadorI2, new Label("i"));
		
		HBox abajo = new HBox();
		abajo.setSpacing(5);
		abajo.setAlignment(Pos.CENTER);
		abajo.getChildren().addAll(resultadoR, new Label("+"), resultadoI, new Label("i"));
		
		VBox operacion = new VBox();
		operacion.setFillWidth(false);
		operacion.setSpacing(5);
		operacion.setAlignment(Pos.CENTER);
		operacion.getChildren().addAll(arriba, medio, linea, abajo);
		
		VBox combo = new VBox();
		combo.setFillWidth(false);
		combo.setAlignment(Pos.CENTER);
		combo.getChildren().add(operadores);
		
		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);
		root.getChildren().addAll(combo, operacion);
		
		Scene escena = new Scene(root, 320, 220);
		
		primaryStage.setScene(escena);
		primaryStage.show();
		primaryStage.setTitle("Calculador Compleja");
		
		//para la suma y resta
		Complejo a= new Complejo();
		Complejo b= new Complejo();
		Complejo c= new Complejo();
		Complejo d= new Complejo();
	
		Bindings.bindBidirectional(operadorR.textProperty(), a.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorR2.textProperty(), b.realProperty(), new NumberStringConverter());
		
		Bindings.bindBidirectional(operadorI.textProperty(), c.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorI2.textProperty(), d.imaginarioProperty(), new NumberStringConverter());
		
		//para la multiplicacion y multiplicacion
		Complejo resultado1RM= new Complejo();
		Complejo resultado2RM= new Complejo();
		
		Complejo resultado12M= new Complejo();
		Complejo resultado22M= new Complejo();

		Complejo resultado1IM= new Complejo();
		Complejo resultado2IM= new Complejo();
		
		Complejo resultado12IM= new Complejo();
		Complejo resultado22IM= new Complejo();
		
		Bindings.bindBidirectional(operadorR.textProperty(), resultado1RM.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorI.textProperty(), resultado2RM.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorR2.textProperty(), resultado12M.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorI2.textProperty(), resultado22M.imaginarioProperty(), new NumberStringConverter());
		
		Bindings.bindBidirectional(operadorR.textProperty(), resultado1IM.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorI2.textProperty(), resultado2IM.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorR2.textProperty(), resultado12IM.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(operadorI.textProperty(), resultado22IM.imaginarioProperty(), new NumberStringConverter());
		
		operadores.getSelectionModel().selectedItemProperty().addListener((o, ov, nv)->{
			if(nv=="+") {
				DoubleBinding resultadR=a.realProperty().add(b.realProperty());
				resultadoR.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(b.realProperty().isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadR.asString("%.0f"))));
				
				DoubleBinding resultadI=c.imaginarioProperty().add(d.imaginarioProperty());
				resultadoI.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(d.imaginarioProperty().isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadI.asString("%.0f"))));
			}
			if(nv=="-") {
				DoubleBinding resultadR=a.realProperty().subtract(b.realProperty());
				resultadoR.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(b.realProperty().isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadR.asString("%.0f"))));
				
				
				DoubleBinding resultadI=c.imaginarioProperty().subtract(d.imaginarioProperty());
				resultadoI.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(d.imaginarioProperty().isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadI.asString("%.0f"))));
			}
			if(nv=="*") {
				DoubleBinding resultadoRealReal=resultado1RM.realProperty().multiply(resultado12M.realProperty()).subtract(resultado2RM.imaginarioProperty().multiply(resultado22M.imaginarioProperty()));
				resultadoR.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(resultadoRealReal.isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadoRealReal.asString("%.0f"))));
				

				DoubleBinding resultadoImaIma=resultado1IM.realProperty().multiply(resultado2IM.imaginarioProperty()).add(resultado22IM.imaginarioProperty().multiply(resultado12IM.realProperty()));
				resultadoI.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(resultadoImaIma.isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadoImaIma.asString("%.0f"))));
			}
		    if(nv=="/") {
				DoubleBinding operacionR=resultado1RM.realProperty().multiply(resultado12M.realProperty()).add(resultado2RM.imaginarioProperty().multiply(resultado22M.imaginarioProperty()));
				DoubleBinding alCuadrado1=resultado12M.realProperty().multiply(resultado12M.realProperty()).add(resultado22M.imaginarioProperty().multiply(resultado22M.imaginarioProperty()));
				DoubleBinding resultadoRealReal=operacionR.divide(alCuadrado1);
				resultadoR.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(operacionR.isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadoRealReal.asString("%.2f"))));

				DoubleBinding operacionI=resultado22IM.imaginarioProperty().multiply(resultado12IM.realProperty()).subtract(resultado1IM.realProperty().multiply(resultado2IM.imaginarioProperty()));
				DoubleBinding alCuadrado2=resultado12IM.realProperty().multiply(resultado12IM.realProperty()).add(resultado2IM.imaginarioProperty().multiply(resultado2IM.imaginarioProperty()));
				DoubleBinding resultadoImaIma=operacionI.divide(alCuadrado2);
				resultadoI.textProperty().bind(Bindings.concat("")
		                .concat(Bindings
		                		.when(operacionI.isEqualTo(0))
		                		.then("0")
		                		.otherwise(resultadoImaIma.asString("%.2f"))));
		    }	
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
