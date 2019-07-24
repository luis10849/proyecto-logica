package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import modelo.ManualUsuario;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ControladorManualUsuario {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ManualUsuario> tablaManualUsuario;

    @FXML
    void initialize() {
        assert tablaManualUsuario != null : "fx:id=\"tablaManualUsuario\" was not injected: check your FXML file 'manualUusario.fxml'.";
        inicializarTabla();
    }
    public void inicializarTabla() {
    	Button agregarPremisa= new Button("PREMISA");
    	agregarPremisa.setPrefWidth(250);
    	agregarPremisa.setPrefHeight(60);
    	Button conclusion= new Button("CONCLUSION");
    	conclusion.setPrefWidth(250);
    	conclusion.setPrefHeight(60);
    	Button atras= new Button();
    	atras.setGraphic(new ImageView("vistas/back.png"));
    	atras.setPrefWidth(250);
    	atras.setPrefHeight(60);
    	Button siguiente= new Button();
    	siguiente.setGraphic(new ImageView("vistas/next.png"));
    	siguiente.setPrefWidth(250);
    	siguiente.setPrefHeight(60);
    	Button limpiar=new Button();
    	limpiar.setGraphic(new ImageView("vistas/x-button.png"));
    	limpiar.setPrefWidth(250);
    	limpiar.setPrefHeight(60);
    	Button eliminarPremisa=new Button();
    	eliminarPremisa.setGraphic(new ImageView("vistas/delete.png"));
    	eliminarPremisa.setPrefWidth(250);
    	eliminarPremisa.setPrefHeight(60);
    	Button refrescar=new Button();
    	refrescar.setGraphic(new ImageView("vistas/reiniciar.png"));
    	refrescar.setPrefWidth(250);
    	refrescar.setPrefHeight(60);
    	Button validar= new Button();
    	validar.setGraphic(new ImageView("vistas/checked.png"));
        validar.setPrefWidth(250);
        validar.setPrefHeight(60);
    	 ObservableList<ManualUsuario>data=FXCollections.observableArrayList(
         		
         		new ManualUsuario(agregarPremisa,"al presionar el boton agrega una premisa "),
         		new ManualUsuario(conclusion,"al presionar el boton agrega la conclusion "),
         		new ManualUsuario(atras,"al presionar el boton, se borra el ultimo cambio echo "),
         		new ManualUsuario(siguiente,"al presionar el boton, rehace el ultimo cambio echo  "),
         		new ManualUsuario(limpiar,"al presionar el boton, limpia el campo de texto  "),
         		new ManualUsuario(eliminarPremisa,"al presionar el boton,elimina una premisa ya creada  "),
         		new ManualUsuario(refrescar,"al presionar el boton,reinicia el programa "),
         		new ManualUsuario(validar,"al presionar el boton,valida el argumento ")
         		
         		
                    );
         tablaManualUsuario.setEditable(true);	
         TableColumn firstNameCol = new TableColumn("BOTON");
 		firstNameCol.setCellValueFactory(new PropertyValueFactory<ManualUsuario, String>("boton"));
 		firstNameCol.setMinWidth(100);
 		TableColumn lastNameCol = new TableColumn("UTILIDAD");
 		lastNameCol.setCellValueFactory(new PropertyValueFactory<ManualUsuario, String>("uso"));
 		lastNameCol.impl_setWidth(400);
         		
 		tablaManualUsuario.setItems(data);
 		tablaManualUsuario.getColumns().addAll(firstNameCol, lastNameCol);
 		
    }
}
