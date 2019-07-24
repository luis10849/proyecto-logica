package logica;

public class Tiempo {

	
	private String espacioTiempo;
	
	private int lineaActual;

	public Tiempo(String espacioTiempo) {
		
		this.espacioTiempo = espacioTiempo;
		this.lineaActual = 1;
		
	}

	public String getEspacioTiempo() {
		return espacioTiempo;
	}

	public void setEspacioTiempo(String espacioTiempo) {
		this.espacioTiempo = espacioTiempo;
	}

	public int getLineaActual() {
		return lineaActual;
	}

	@Override
	public String toString() {
		return "Tiempo [espacioTiempo=" + espacioTiempo + ", lineaActual=" + lineaActual + "]";
	}

	public void setLineaActual(int lineaActual) {
		this.lineaActual = lineaActual;
	}
	
	
}

