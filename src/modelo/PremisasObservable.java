package modelo;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;

public class PremisasObservable {

	private StringProperty formula;

	private final Button boton;
	private ArrayList<Integer> valoresDeVerdad;

	@Override
	public String toString() {
		return "PremisasObservable [formula=" + formula + ", boton=" + boton + ", valoresDeVerdad=" + valoresDeVerdad
				+ "]";
	}

	public String getFormula() {
		return formula.get();
	}

	public void setFormula(StringProperty formula) {
		this.formula = formula;
	}

	public PremisasObservable(String formula) {

		this.boton = new Button("elimnar");

		
         
		this.formula = new SimpleStringProperty(formula);
		this.valoresDeVerdad=null;
		
		

	}

	

	
	public ArrayList<Integer> getValoresDeVerdad() {
		return valoresDeVerdad;
	}

	public void setValoresDeVerdad(ArrayList<Integer> valoresDeVerdad) {
		this.valoresDeVerdad = valoresDeVerdad;
	}

	public Button getBoton() {
		return boton;
	}

	

}
