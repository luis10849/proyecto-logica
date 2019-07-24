package Aplicacion;

import controlador.Idioma;
import javafx.application.Application;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;
	private Idioma idioma;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/vistas/interfaz.fxml"));
		Scene scene = new Scene(root);
		idioma = new Idioma("Espanol");
		primaryStage.setTitle(idioma.getProperty("titulo"));
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("vistas/atom.png"));
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 
	 */
	public static void main(String args[]) {

		launch(args);
	}
}
