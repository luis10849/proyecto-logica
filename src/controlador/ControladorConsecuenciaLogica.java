package controlador;

import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.ResizeFeatures;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import modelo.*;

public class ControladorConsecuenciaLogica {

	String text;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<StringProperty> table;

	@FXML
	private TextArea area;

	@FXML
	private Button atras;

	@FXML
	private Button next;

	@FXML
	private TextArea areaValido;
	private String texto;

	private Idioma idioma;
	@FXML
	void pasoApaso(ActionEvent event) {

	}
	
	
	@FXML
	void initialize() {


		idioma = new Idioma("Espanol");
		
		area.setText(idioma.getProperty("area"));
		table.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> autoResizeColumns(table));

	}


	public void setData(ArrayList<Integer> satifacibilidadPremisas, ArrayList<String> todasLasFormulas,
			int[][] tablasDeVerdadPremisas, ArrayList<Integer> tablaDeVerdadConclusion,
			ObservableList<PremisasObservable> premisas, boolean v) {
		int[][] matriz = laMatriz(satifacibilidadPremisas, todasLasFormulas, tablasDeVerdadPremisas,
				tablaDeVerdadConclusion, premisas);
		ObservableList<StringProperty> x = FXCollections.observableArrayList();

		for (int i = 0; i < matriz.length; i++) {
			String a = "";
			for (int j = 0; j < matriz[0].length; j++) {

				a += (matriz[i][j]);

			}
			x.add(new SimpleStringProperty(a));
		}
		table.setItems(x);

		for (int i = 0; i < x.size(); i++) {
			if (i == 0) {
				TableColumn<StringProperty, String> firstNameCol = new TableColumn("ik");
				firstNameCol.setMaxWidth(1000);
				firstNameCol.setMinWidth(100);
				int a = i;
				firstNameCol.setCellValueFactory(
						CellData -> new SimpleStringProperty(CellData.getValue().get().charAt(a) + ""));
				table.getColumns().add(firstNameCol);
			} else {
				if ((i - 1) < todasLasFormulas.size()) {
					TableColumn<StringProperty, String> firstNameCol = new TableColumn(todasLasFormulas.get(i - 1));
					firstNameCol.setMaxWidth(1000);
					firstNameCol.setMinWidth(100);
					int a = i;
					firstNameCol.setCellValueFactory(
							CellData -> new SimpleStringProperty(CellData.getValue().get().charAt(a) + ""));
					table.getColumns().add(firstNameCol);
				}
			}


		}

		texto = areaValido.getText();
		if (v) {
			texto += "HAY CONSECUENCIA LOGICA \nPOR LO TANTO El ARGUMENTO ES VALIDO";
		} else {
			texto += "NO HAY CONSECUENCIA LOGICA \nPOR LO TANTO EL ARGUMENTO  ES INVALIDO";
		}
		areaValido.setText(texto);
		imprimir(matriz);
	}

	public int[][] laMatriz(ArrayList<Integer> satifacibilidadPremisas, ArrayList<String> todasLasFormulas,
			int[][] tablasDeVerdadPremisas, ArrayList<Integer> tablaDeVerdadConclusion,
			ObservableList<PremisasObservable> premisas) {

		int[][] matriz = new int[tablasDeVerdadPremisas.length][todasLasFormulas.size() + 1];

		for (int i = 0; i < satifacibilidadPremisas.size(); i++) {
			matriz[i][0] = satifacibilidadPremisas.get(i);
		}
		for (int j = 1; j < matriz[0].length; j++) {

			for (int i = 0; i < matriz.length; i++) {
				if ((j - 1) < tablasDeVerdadPremisas[0].length) {
					matriz[i][j] = tablasDeVerdadPremisas[i][j - 1];
				}
			}

		}
		for (int i = 0; i < tablaDeVerdadConclusion.size(); i++) {
			matriz[i][matriz[0].length - 1] = tablaDeVerdadConclusion.get(i);
		}
		return matriz;
	}

	public static void imprimir(int[][] matriz) {
		for (int x = 0; x < matriz.length; x++) {
			for (int y = 0; y < matriz[x].length; y++) {
				if (y != matriz[x].length - 1) {
					System.out.print("\t");
				}
			}
		}
	}

	public void configurandoPremisas(ObservableList<PremisasObservable> premisas, int[][] tablasDeVerdadPremisas) {

		for (int i = 0; i < premisas.size() - 1; i++) {

			PremisasObservable p = premisas.get(i);
			p.setValoresDeVerdad(sacarTabla(i, tablasDeVerdadPremisas));

		}
	}

	public ArrayList<Integer> sacarTabla(int i, int[][] x) {
		ArrayList<Integer> t = new ArrayList<Integer>();

		for (int j = 0; j < x.length; j++) {
			;
			t.add(x[j][i]);

		}
		return t;
	}

	public void customResize(TableView<?> view) {

		AtomicLong width = new AtomicLong();
		view.getColumns().forEach(col -> {
			width.addAndGet((long) col.getWidth());
		});
		double tableWidth = view.getWidth();

		if (tableWidth > width.get()) {
			view.getColumns().forEach(col -> {
				col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
			});
		}
	}

	public static void autoResizeColumns(TableView<?> table) {
		// Set the right policy
		table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		table.getColumns().stream().forEach((column) -> {
			// Minimal width = columnheader
			Text t = new Text(column.getText());
			double max = t.getLayoutBounds().getWidth();
			for (int i = 0; i < table.getItems().size(); i++) {
				// cell must not be empty
				if (column.getCellData(i) != null) {
					t = new Text(column.getCellData(i).toString());
					double calcwidth = t.getLayoutBounds().getWidth();
					// remember new max-width
					if (calcwidth > max) {
						max = calcwidth;
					}
				}
			}
			// set the new max-widht with some extra space
			column.setPrefWidth(max + 10.0d);
		});
	}

}
