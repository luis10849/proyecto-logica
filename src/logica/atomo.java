package logica;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class atomo {

	private char nombre;
	private ArrayList<Integer> tablaVerdad;

	public atomo(char nombre, ArrayList<Integer> tablaVerdad) {
		this.nombre = nombre;
		this.tablaVerdad = tablaVerdad;
	}

	public char getNombre() {
		return nombre;
	}

	public void setNombre(char nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "atomo{" + "nombre=" + nombre + ", tablaVerdad=" + tablaVerdad + '}';
	}

	public ArrayList<Integer> getTablaVerdad() {
		return tablaVerdad;
	}

	public void setTablaVerdad(ArrayList<Integer> tablaVerdad) {
		this.tablaVerdad = tablaVerdad;
	}

}