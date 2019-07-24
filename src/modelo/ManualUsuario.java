package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class ManualUsuario {

	private final SimpleStringProperty uso;

	private Button boton;

	public ManualUsuario(Button boton,String uso) {

		this.uso = new SimpleStringProperty(uso);
		this.boton = boton;
	}

	public String getUso() {
		return uso.get();
	}

	public void setUso(String u) {
	   uso.set(u);
	}

	public Button getBoton() {
		return boton;
	}

	public void setBoton(Button boton) {
		this.boton = boton;
	}

	

}
