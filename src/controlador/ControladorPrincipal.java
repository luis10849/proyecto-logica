package controlador;

import java.io.IOException;
import java.rmi.server.LoaderHandler;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.SPACE;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import logica.ConseceunciaLogica;
import logica.Tiempo;
import modelo.PremisasObservable;
import vistas.*;

public class ControladorPrincipal {

	int pos;
	boolean agregoConclusion = false;
	private ObservableList<PremisasObservable> premisas;

	@FXML
	private TextField textFormula;

	@FXML
	private Button btnPremisa;

	@FXML
	private TableView<PremisasObservable> tablaPremisas;

	@FXML
	private TableColumn<PremisasObservable, String> columnaPremisa;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnP;

	@FXML
	private Button btnS;

	@FXML
	private Button btnQ;

	@FXML
	private Button btnR;

	@FXML
	private Button btnor;

	@FXML
	private Button btnCon;

	@FXML
	private Button btnN;

	@FXML
	private Button btnBicon;

	@FXML
	private Button btnT;

	@FXML
	private Button btnAnd;

	@FXML
	private Button btnconclusion;

	@FXML
	private Button btnPas;

	@FXML
	private Button btnFut;

	@FXML
	private Label label;

	@FXML
	private Label labeTitulo;
	@FXML
	private TextArea areaConclusion;
	@FXML
	private ImageView imagenAnd;

	@FXML
	private ImageView imagenOr;

	@FXML
	private ImageView imagenCon;

	@FXML
	private ImageView imagenBicon;

	@FXML
	private ImageView imagenNe;

	@FXML
	private ImageView limpiar;

	@FXML
	private ImageView pasado;

	@FXML
	private ImageView futuro;

	@FXML
	private Button eliminar;

	@FXML
	private ImageView imgEliminar;

	@FXML
	private Button btnreiniciar;

	@FXML
	private ImageView reiniciarImg;

	@FXML
	private MenuItem itemManual;

	@FXML
	private Menu menuAyuda;

	@FXML
	private Menu menuIdioma;

	@FXML
	private MenuItem itemEspanol;

	@FXML
	private MenuItem itemIngles;

	@FXML
	private ImageView imgValidar;

	private Idioma idioma;
	
	ControladorConsecuenciaLogica controladorConsecuencia;

	@FXML
	void consumirTeclado(KeyEvent event) {
		event.consume();
	}

	@FXML
	void observarManual(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/manualUsuario.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		stage.getIcons().add(new Image("vistas/atom.png"));
		stage.setTitle("MANUAL DE USUARIO");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void initialize() {

		idioma = new Idioma("Espanol");
		menuAyuda.setText(idioma.getProperty("menuAyuda"));
		itemManual.setText(idioma.getProperty("itemManual"));
		menuIdioma.setText(idioma.getProperty("menuIdioma"));
		itemEspanol.setText(idioma.getProperty("menuEspanol"));
		itemIngles.setText(idioma.getProperty("menuIngles"));
		labeTitulo.setText(idioma.getProperty("labelTitulo"));
		textFormula.setPromptText(idioma.getProperty("textFormula"));
		btnPremisa.setText(idioma.getProperty("btnPremisa"));
		btnconclusion.setText(idioma.getProperty("btnConclusion"));
		label.setText(idioma.getProperty("label"));
		columnaPremisa.setText(idioma.getProperty("tablaPremisas"));

		ConseceunciaLogica.agregarTiempo(new Tiempo(""));
		System.out.println("linea temporal " + ConseceunciaLogica.getLineaDelTiempo().size());
		premisas = FXCollections.observableArrayList();
		tablaPremisas.setItems(premisas);
		columnaPremisa.setCellValueFactory(new PropertyValueFactory<PremisasObservable, String>("formula"));

	}

	@FXML
	void onEspanolButton(ActionEvent event) {

		idioma = new Idioma("Espanol");

		
		menuAyuda.setText(idioma.getProperty("menuAyuda"));
		itemManual.setText(idioma.getProperty("itemManual"));
		menuIdioma.setText(idioma.getProperty("menuIdioma"));
		itemEspanol.setText(idioma.getProperty("menuEspanol"));
		itemIngles.setText(idioma.getProperty("menuIngles"));
		itemIngles.setText(idioma.getProperty("menuIngles"));
		labeTitulo.setText(idioma.getProperty("labelTitulo"));
		textFormula.setPromptText(idioma.getProperty("textFormula"));
		btnPremisa.setText(idioma.getProperty("btnPremisa"));
		btnconclusion.setText(idioma.getProperty("btnConclusion"));
		label.setText(idioma.getProperty("label"));
		columnaPremisa.setText(idioma.getProperty("tablaPremisas"));

	}

	@FXML
	void onInglesButton(ActionEvent event) {

		idioma = new Idioma("Ingles");

		menuAyuda.setText(idioma.getProperty("menuAyuda"));
		itemManual.setText(idioma.getProperty("itemManual"));
		menuIdioma.setText(idioma.getProperty("menuIdioma"));
		itemEspanol.setText(idioma.getProperty("menuEspanol"));
		itemIngles.setText(idioma.getProperty("menuIngles"));
		itemIngles.setText(idioma.getProperty("menuIngles"));
		labeTitulo.setText(idioma.getProperty("labelTitulo"));
		textFormula.setPromptText(idioma.getProperty("textFormula"));
		btnPremisa.setText(idioma.getProperty("btnPremisa"));
		btnconclusion.setText(idioma.getProperty("btnConclusion"));
		label.setText(idioma.getProperty("label"));
		columnaPremisa.setText(idioma.getProperty("tablaPremisas"));
	}

	@FXML
	void validarConsecuenciaLogica(MouseEvent event) throws IOException {
		if (agregoConclusion) {

			boolean v = ConseceunciaLogica.validarConsecuenciaLogica();

			ConseceunciaLogica.imprimir(ConseceunciaLogica.getX());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/graficoConsecuenciaLogica.fxml"));
			Parent root = loader.load();
			ControladorConsecuenciaLogica controller = loader.<ControladorConsecuenciaLogica>getController();
			ArrayList<Integer> satifacibilidadPremisas = ConseceunciaLogica.getW();
			ArrayList<String> todasLasFormulas = ConseceunciaLogica.getTodasLasFormulas();
			int[][] tablasDeVerdadPremisas = ConseceunciaLogica.getX();
			ArrayList<Integer> tablaDeVerdadConclusion = ConseceunciaLogica.getTablaDeVerdadConclusion();

			controller.setData(satifacibilidadPremisas, todasLasFormulas, tablasDeVerdadPremisas,
					tablaDeVerdadConclusion, premisas, v);
			Stage stage = new Stage();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			ConseceunciaLogica.reiniciar();
			tablaPremisas.getItems().removeAll(premisas);
			btnPremisa.setVisible(true);
			agregoConclusion = false;
		}

		else {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert1"));
			alert.showAndWait();
		}
		textFormula.requestFocus();

		textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
		pos = textFormula.getCaretPosition();
		agregoConclusion = false;
	}

	@FXML
	void reiniciarSistema(MouseEvent event) {
		ConseceunciaLogica.reiniciar();

		tablaPremisas.getItems().removeAll(premisas);
		textFormula.requestFocus();

		textFormula.setText("");
		agregoConclusion = false;

	}

	public boolean mirandoSiEliminoLaConclusion(String f) {

		if (f.charAt(0) == '_' && f.charAt(1) == '_') {
			return true;
		}
		return false;

	}

	@FXML
	void eliminarPremisa(MouseEvent event) {
		int pos = tablaPremisas.getSelectionModel().getSelectedIndex();
		if (pos > -1) {

			if (!(mirandoSiEliminoLaConclusion(premisas.get(pos).getFormula()))) {
				if (tablaPremisas.getItems().size() == 1) {

					PremisasObservable indice = tablaPremisas.getSelectionModel().getSelectedItem();
					premisas.remove(indice);

					ConseceunciaLogica.eliminarPremisa(indice.getFormula());
					tablaPremisas.refresh();
				} else {
					String anterior = premisas.get(pos - 1).getFormula();
					System.out.println("anterior  " + anterior);

					if (mirandoSiEliminoLaConclusion(anterior)) {
						tablaPremisas.getItems().remove(pos - 1);
						PremisasObservable indice = tablaPremisas.getSelectionModel().getSelectedItem();
						premisas.remove(indice);

						ConseceunciaLogica.eliminarPremisa(indice.getFormula());
						tablaPremisas.refresh();
						btnPremisa.setVisible(true);
					} else {
						PremisasObservable indice = tablaPremisas.getSelectionModel().getSelectedItem();
						premisas.remove(indice);

						ConseceunciaLogica.eliminarPremisa(indice.getFormula());
						tablaPremisas.refresh();
					}

				}
			} else {
				Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert2"));
				alert.showAndWait();
			}

		} else if (tablaPremisas.getItems().size() > 0) {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert3"));
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert4"));
			alert.showAndWait();
		}
		textFormula.requestFocus();

		textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
		pos = textFormula.getCaretPosition();
	}

	@FXML
	void pasadoPresente(ActionEvent event) {

		Button boton = (Button) event.getSource();
		if (boton.getId().equals("btnFut")) {

			int direccionEspacioTemporal = 1;
			String nuevoTiempo = ConseceunciaLogica.volverAlPasado(direccionEspacioTemporal);

			if (nuevoTiempo != "null") {
				textFormula.setText(nuevoTiempo);

				if (ConseceunciaLogica.validarPremisa(textFormula.getText()) == false) {

					textFormula.requestFocus();
					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();
				}

			}
			pos = textFormula.getCaretPosition();
		}
		if (boton.getId().equals("btnPas")) {

			int direccionEspacioTemporal = 0;
			String nuevoTiempo = ConseceunciaLogica.volverAlPasado(direccionEspacioTemporal);

			if (nuevoTiempo != "null") {
				textFormula.setText(nuevoTiempo);
				textFormula.requestFocus();
				if (textFormula.getText().length() > 0) {

					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();
				}
			}
			// textFormula.requestFocus();
			pos = textFormula.getCaretPosition();

		}
	}

	@FXML
	void volverAlFuturo(MouseEvent event) {

		ImageView img = (ImageView) (event).getSource();
		if (img.getId().equals("futuro")) {
			System.out.println("futuro");
		}
	}

	@FXML
	void limpiar(MouseEvent event) {
		textFormula.setText("");
		textFormula.requestFocus();
		ConseceunciaLogica.borrarLineasDelTiempo();
		pos = textFormula.getCaretPosition();
	}

	@FXML
	void accionImg(MouseEvent event) throws IOException {

		ImageView img = (ImageView) (event).getSource();
		String op = img.getId();
		String f = textFormula.getText();
		boolean v;

		if (f.length() > 0) {

			v = ConseceunciaLogica.validar(f, pos);
			if (v) {
				String nueva = ConseceunciaLogica.calcularFormula(f, op, pos);
				textFormula.setText(nueva);
				if (ConseceunciaLogica.validarPremisa(textFormula.getText()) == false) {
					textFormula.requestFocus();

					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();

				}
				ConseceunciaLogica.agregarTiempo(new Tiempo(textFormula.getText()));

			}

			else {

				Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert5"));
				alert.showAndWait();

				if (f.length() > 1 && ConseceunciaLogica.validarPremisa(textFormula.getText()) == false) {
					textFormula.requestFocus();
					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();
				}

			}
		} else {
			textFormula.setText(ConseceunciaLogica.calcularFormula(f, op, pos));
			ConseceunciaLogica.agregarTiempo(new Tiempo(textFormula.getText()));
			textFormula.requestFocus();
			if (textFormula.getLength() > 5) {
				textFormula.positionCaret(pos + 2);
			} else {
				textFormula.positionCaret(pos + 3);
			}
			pos = textFormula.getCaretPosition();
		}

	}

	@FXML
	void accionOr(ActionEvent event) throws IOException {
		Button boton = (Button) event.getSource();

		String op = boton.getText();
		String f = textFormula.getText();
		boolean v;

		if (f.length() > 0) {
			v = ConseceunciaLogica.validar(f, pos);
			if (v) {
				String nueva = ConseceunciaLogica.calcularFormula(f, op, pos);
				textFormula.setText(nueva);
				if (ConseceunciaLogica.validarPremisa(textFormula.getText()) == false) {
					textFormula.requestFocus();
					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();

				}
				ConseceunciaLogica.agregarTiempo(new Tiempo(textFormula.getText()));
			}

			else {

				Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert6"));
				alert.showAndWait();

				if (f.length() > 1 && ConseceunciaLogica.validarPremisa(textFormula.getText()) == false) {
					textFormula.requestFocus();

					textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
					pos = textFormula.getCaretPosition();
				}

			}
		} else {
			System.err.println("no es mayor");
			textFormula.setText(ConseceunciaLogica.calcularFormula(f, op, pos));
			ConseceunciaLogica.agregarTiempo(new Tiempo(textFormula.getText()));
			textFormula.requestFocus();
			if (textFormula.getLength() > 5) {
				textFormula.positionCaret(pos + 2);
			} else {
				textFormula.positionCaret(pos + 3);
			}
			pos = textFormula.getCaretPosition();
		}

	}

	@FXML
	void accionand(ActionEvent event) {
		String op = btnAnd.getText();
		String f = textFormula.getText();
		System.out.println("pos " + pos);
		boolean v;

		if (f.length() > 0) {
			v = ConseceunciaLogica.validar(f, pos);
			if (v) {
				String nueva = ConseceunciaLogica.calcularFormula(f, op, pos);
				textFormula.setText(nueva);
				textFormula.requestFocus();
				textFormula.positionCaret(pos + 2);
				pos = textFormula.getCaretPosition();
			} else {
				textFormula.requestFocus();
				textFormula.positionCaret(pos - 1);
				pos = textFormula.getCaretPosition();
			}
		} else {
			textFormula.setText(ConseceunciaLogica.formulaCorrecta(op));
			textFormula.requestFocus();
			textFormula.positionCaret(pos + 2);
			pos = textFormula.getCaretPosition();
		}

	}

	@FXML
	void c(ActionEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void toco(DragEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void o(MouseDragEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void k(MouseDragEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void l(MouseDragEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void x(MouseEvent event) {
		pos = textFormula.getCaretPosition();
	}

	@FXML
	void zx(MouseEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void kl(TouchEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void zxc(MouseEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void bn(SwipeEvent event) {
		int pos = textFormula.getCaretPosition();
	}

	@FXML
	void qz(KeyEvent event) {
		if (event.getCode().equals(KeyCode.LEFT)) {

			pos = textFormula.getCaretPosition() - 1;

		}
		if (event.getCode().equals(KeyCode.RIGHT)) {
			pos = textFormula.getCaretPosition() + 1;

		}
		if ((event.getCode().equals(KeyCode.BACK_SPACE))) {
			event.consume();
		}
		if ((event.getCode().equals(KeyCode.SPACE))) {
			event.consume();

		}
		if (event.getCode().equals(KeyCode.DIGIT9)) {
			event.consume();
		}

	}

	@FXML
	void agregarPremisa(ActionEvent event) {

		if (textFormula.getText().length() > 0 && ConseceunciaLogica.validarPremisa(textFormula.getText())) {
			ConseceunciaLogica.AgregarPremisa(textFormula.getText());

			/*
			 * Cuando sea conclusion
			 */

			// Agrega la premisa y se bloquea el agregar

			// Cuando diga que es consencuencia logica

			PremisasObservable nueva = new PremisasObservable(textFormula.getText());
			premisas.add(nueva);

			tablaPremisas.refresh();
			textFormula.setText("");

		} else {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert7"));
			alert.showAndWait();
		}
		textFormula.requestFocus();

		textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
		pos = textFormula.getCaretPosition();
	}

	public String linea() {
		String mayor = premisas.get(0).getFormula();
		String linea = "_";
		for (int i = 1; i < premisas.size(); i++) {

			if (mayor.length() < premisas.get(i).getFormula().length()) {
				mayor = premisas.get(i).getFormula();
			}
		}
		for (int i = 0; i < mayor.length(); i++) {
			linea += "_";
		}
		return linea;
	}

	@FXML
	void agregarConclusion(ActionEvent event) {
		if (premisas.size() > 1 && textFormula.getText().length() > 0
				&& ConseceunciaLogica.validarPremisa(textFormula.getText())) {
			agregoConclusion = true;
			PremisasObservable linea = new PremisasObservable(linea());
			premisas.add(linea);
			ConseceunciaLogica.AgregarPremisa(textFormula.getText());
			ConseceunciaLogica.examinarArgumento();
			PremisasObservable nueva = new PremisasObservable(textFormula.getText());
			premisas.add(nueva);
			areaConclusion.setText(textFormula.getText());
			textFormula.setText("");
			textFormula.requestFocus();
			System.out.println("atomos " + ConseceunciaLogica.getAtomos().toString());
			System.out.println("premisas " + ConseceunciaLogica.getTodasLasFormulas().toString());
			btnPremisa.setVisible(false);
		} else if (premisas.size() < 2) {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert8"));
			alert.showAndWait();
		} else if (textFormula.getText().length() == 0 || !(ConseceunciaLogica.validarPremisa(textFormula.getText()))) {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert9"));
			alert.showAndWait();
		}
		textFormula.requestFocus();

		textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
		pos = textFormula.getCaretPosition();

	}

	@FXML
	void logica(ActionEvent event) throws IOException {

		if (agregoConclusion) {

			boolean v = ConseceunciaLogica.validarConsecuenciaLogica();

			String data = "Hello World!";
			ConseceunciaLogica.imprimir(ConseceunciaLogica.getX());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/graficoConsecuenciaLogica.fxml"));
			Parent root = loader.load();
			ControladorConsecuenciaLogica controller = loader.<ControladorConsecuenciaLogica>getController();
			ArrayList<Integer> satifacibilidadPremisas = ConseceunciaLogica.getW();
			ArrayList<String> todasLasFormulas = ConseceunciaLogica.getTodasLasFormulas();
			int[][] tablasDeVerdadPremisas = ConseceunciaLogica.getX();
			ArrayList<Integer> tablaDeVerdadConclusion = ConseceunciaLogica.getTablaDeVerdadConclusion();

			controller.setData(satifacibilidadPremisas, todasLasFormulas, tablasDeVerdadPremisas,
					tablaDeVerdadConclusion, premisas, v);
			Stage stage = new Stage();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			ConseceunciaLogica.reiniciar();
			tablaPremisas.getItems().removeAll(premisas);
			btnPremisa.setVisible(true);
		}

		else {
			Alert alert = new Alert(AlertType.WARNING, idioma.getProperty("alert10"));
			alert.showAndWait();
		}

		textFormula.requestFocus();

		textFormula.positionCaret(ConseceunciaLogica.agujeroNegro(textFormula.getText()));
		pos = textFormula.getCaretPosition();
		agregoConclusion = false;
	}

}
