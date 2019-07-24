package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import modelo.PremisasObservable;

public class ConseceunciaLogica {

	static ArrayList<atomo> atomos = new ArrayList<atomo>();
	static ArrayList<String> todasLasFormulas = new ArrayList<>();;
	static ArrayList<Integer> w = new ArrayList<>();
	static ArrayList<Integer>tablaDeVerdadConclusion=new ArrayList<>();
	static int[][] x=null;
	static ArrayList<Tiempo> lineaDelTiempo = new ArrayList<Tiempo>();
	static int posicionEnElTiempo = 0;

	
	public static void eliminarPremisa(String formula) {
		
		boolean v=true;
		for (int i = 0; i <todasLasFormulas.size()&&v==true; i++) {
			
			if(todasLasFormulas.get(i).equals(formula)) {
				todasLasFormulas.remove(i);
				v=false;
			}
			
		}
		
	}
	public static void agregarTiempo(Tiempo t) {

		if (lineaDelTiempo.size() == 0) {
			lineaDelTiempo.add(t);
		} else if (LineaDelTiempoRepetida(t) == false) {
			System.out.println("nombre del que llega  " + t.getEspacioTiempo());
			for (int i = 0; i < lineaDelTiempo.size(); i++) {
				if (lineaDelTiempo.get(i).getLineaActual() == 1) {
					Tiempo tn = lineaDelTiempo.get(i);
					tn.setLineaActual(0);
					lineaDelTiempo.set(i, tn);
				}
			}
			System.out.println("lo agrega");
			lineaDelTiempo.add(t);
		} else {

			t.setLineaActual(1);
			lineaDelTiempo.set(posicionEnElTiempo, t);
			borrarLineasActualesDelPasado();
			borrarLineasActualesDelFuturo();
		}

	}

	public static void borrarLineasActualesDelPasado() {

		for (int i = 0; i < posicionEnElTiempo; i++) {
			if (lineaDelTiempo.get(i).getLineaActual() == 1) {
				Tiempo tn = lineaDelTiempo.get(i);
				tn.setLineaActual(0);
				lineaDelTiempo.set(i, tn);
			}

		}
	}

	public static void borrarLineasActualesDelFuturo() {

		for (int i = posicionEnElTiempo + 1; i < lineaDelTiempo.size(); i++) {
			if (lineaDelTiempo.get(i).getLineaActual() == 1) {
				Tiempo tn = lineaDelTiempo.get(i);
				tn.setLineaActual(0);
				lineaDelTiempo.set(i, tn);
			}

		}
	}

	public static ArrayList<Integer> getW() {
		return w;
	}

	public static void setW(ArrayList<Integer> w) {
		ConseceunciaLogica.w = w;
	}

	public static int[][] getX() {
		return x;
	}

	public static void setX(int[][] x) {
		ConseceunciaLogica.x = x;
	}

	public static ArrayList<Tiempo> getLineaDelTiempo() {
		return lineaDelTiempo;
	}

	public static void setLineaDelTiempo(ArrayList<Tiempo> lineaDelTiempo) {
		ConseceunciaLogica.lineaDelTiempo = lineaDelTiempo;
	}

	public static void AgregarPremisa(String p) {
		todasLasFormulas.add(p);
	}

	/**
	 * Genera la tabla de verdad de los atomos
	 */
	public static void generarTablasDeVerdadAtomos() {
		int combinaciones = (int) (Math.pow(2, atomos.size()));
		int tope = combinaciones / 2;
		hacerTablasDeVerdad(0, tope, combinaciones);
	}

	/**
	 * 
	 * @param i
	 * @param denominador
	 * @param numerador
	 */
	public static void hacerTablasDeVerdad(int i, int denominador, int numerador) {

		if (i < atomos.size()) {
			ArrayList<Integer> tablaDeVerdad = new ArrayList<>();
			int cv = numerador / denominador;
			tablaDeVerdad = hacerValoresDeverdad(denominador, cv, 0, tablaDeVerdad, 0, 1);
			denominador = denominador / 2;
			atomo x = atomos.get(i);
			x.setTablaVerdad(tablaDeVerdad);
			hacerTablasDeVerdad(i + 1, denominador, numerador);
		}
	}

	public static int[][] llenarMatriz(int[][] m, int i, ArrayList<Integer> p) {
		for (int j = 0; j < m.length; j++) {
			m[j][i] = p.get(j);
		}
		return m;
	}

	public static void imprimir(int[][] matriz) {
		for (int x = 0; x < matriz.length; x++) {
			System.out.print("|");
			for (int y = 0; y < matriz[x].length; y++) {
				System.out.print(matriz[x][y]);
				if (y != matriz[x].length - 1) {
					System.out.print("\t");
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Valida la consecuencia logica
	 * 
	 * @return true si es consecuencia logica
	 */
	public static boolean validarConsecuenciaLogica() {

		generarTablasDeVerdadAtomos();
		//ArrayList<Integer> ic = new ArrayList<>();
		int combinaciones = (int) (Math.pow(2, atomos.size()));
		x = new int[combinaciones][todasLasFormulas.size() - 1];
		for (int i = 0; i < todasLasFormulas.size() - 1; i++) {
			Node raiz = new Node(null);
			arbol(todasLasFormulas.get(i), raiz);
			// a.add(i,TablaPremisa(raiz));
			x = llenarMatriz(x, i, TablaPremisa(raiz));

		}
		imprimir(x);

		w = concidenciaPremisas(w, x, 0, 0);

		ArrayList<Integer> indicesPremisas = indices(w);

		Node raiz = new Node(null);
		arbol(todasLasFormulas.get(todasLasFormulas.size() - 1), raiz);
		tablaDeVerdadConclusion = TablaPremisa(raiz);
		ArrayList<Integer> indicesConclusion = indices(tablaDeVerdadConclusion);

		return coincidenIndices(indicesPremisas, indicesConclusion);
	}
	public static boolean validarConsecuenciaLogica1(ObservableList<PremisasObservable>todasLasFormulas) {

		generarTablasDeVerdadAtomos();
		//ArrayList<Integer> ic = new ArrayList<>();
		int combinaciones = (int) (Math.pow(2, atomos.size()));
		x = new int[combinaciones][todasLasFormulas.size() - 1];
		for (int i = 0; i < todasLasFormulas.size() - 1; i++) {
			Node raiz = new Node(null);
			System.out.println("formula  "+todasLasFormulas.get(i).getFormula().toString());
			arbol(todasLasFormulas.get(i).getFormula().toString(), raiz);
			// a.add(i,TablaPremisa(raiz));
			x = llenarMatriz(x, i, TablaPremisa(raiz));

		}
		imprimir(x);

		w = concidenciaPremisas(w, x, 0, 0);

		ArrayList<Integer> indicesPremisas = indices(w);

		Node raiz = new Node(null);
		arbol(todasLasFormulas.get(todasLasFormulas.size() - 1).getFormula().toString(), raiz);
		tablaDeVerdadConclusion = TablaPremisa(raiz);
		ArrayList<Integer> indicesConclusion = indices(tablaDeVerdadConclusion);

		return coincidenIndices(indicesPremisas, indicesConclusion);
	}


	public static ArrayList<Integer> getTablaDeVerdadConclusion() {
		return tablaDeVerdadConclusion;
	}

	public static void setTablaDeVerdadConclusion(ArrayList<Integer> tablaDeVerdadConclusion) {
		ConseceunciaLogica.tablaDeVerdadConclusion = tablaDeVerdadConclusion;
	}


	/**
	 * Examina las concidencia donde todas las premisas y la conclusion son 1
	 * 
	 * @param indicesPremisas
	 * @param indicesConclusion
	 * @return true si conciden y falso en el caso contrario
	 */
	public static boolean coincidenIndices(ArrayList<Integer> indicesPremisas, ArrayList<Integer> indicesConclusion) {
		// boolean c=fals;
		int con = 0;
		boolean b = true;
		System.out.println("entro");
		for (int i = 0; i < indicesConclusion.size(); i++) {
			b = true;
			for (int j = 0; j < indicesPremisas.size() && b == true; j++) {
				if (indicesConclusion.get(i) == indicesPremisas.get(j)) {
					con++;
					b = false;
				}
			}
		}

		
	
		if (con == indicesPremisas.size()) {
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param indices
	 * @return
	 */
	public static ArrayList<Integer> indices(ArrayList<Integer> indices) {
		ArrayList<Integer> k = new ArrayList<>();
		for (int i = 0; i < indices.size(); i++) {
			if (indices.get(i) == 1) {
				k.add(i);
			}
		}
		return k;
	}

	/**
	 * Examina donde las premisas son iguales
	 * 
	 * @param salida
	 * @param matrizEvaluar
	 * @param i
	 * @param j
	 * @return un ArrayList con los indices donde conciden las premisas
	 */
	public static ArrayList<Integer> concidenciaPremisas(ArrayList<Integer> salida, int matrizEvaluar[][], int i,
			int j) {

		if (i < matrizEvaluar.length) {
			if (j < matrizEvaluar[0].length - 1) {
				if (matrizEvaluar[i][j] == 1 && matrizEvaluar[i][j + 1] == 1
						&& (j + 1) == matrizEvaluar[0].length - 1) {
					salida.add(1);
					return concidenciaPremisas(salida, matrizEvaluar, i, j + 1);
				}
				if (matrizEvaluar[i][j] == 1 && matrizEvaluar[i][j + 1] == 1 && (j + 1) < matrizEvaluar[0].length - 1) {
					return concidenciaPremisas(salida, matrizEvaluar, i, j + 1);
				}
				if (matrizEvaluar[i][j] == 1 && matrizEvaluar[i][j + 1] == 0
						|| matrizEvaluar[i][j] == 0 && matrizEvaluar[i][j + 1] == 1
						|| matrizEvaluar[i][j] == 0 && matrizEvaluar[i][j + 1] == 0) {
					salida.add(0);
					return concidenciaPremisas(salida, matrizEvaluar, i + 1, 0);
				}

			} else {
				return concidenciaPremisas(salida, matrizEvaluar, i + 1, 0);
			}

		}
		return salida;

	}

	/**
	 * Vacea el guardado
	 */
	public static void reiniciar() {
		atomos = new ArrayList<>();
		todasLasFormulas = new ArrayList<>();
		x=null;
		
		w=new ArrayList<Integer>();
		tablaDeVerdadConclusion=new ArrayList<>();
		
	}

	/**
	 * Devuevle la formula correcta con parentesis segun el operador que le llegue
	 * 
	 * @param formula  formula actual
	 * @param operador operadorElegido
	 * @param pos      donde se va insertar el atomo
	 * @return
	 */
	public static String calcularFormula(String formula, String operador, int pos) {

		if (formula.length() == 0) {
//			char o = operador.charAt(0);
//			if (!(isOperadorBinario(o))) {
//
//				// Atomo
//				atomo a = new atomo(o, null);
//				agregarAtomo(a);
//			}

			return formulaCorrecta(operador);

		} else {
			String inserto = formulaCorrecta(operador);

//			char o = operador.charAt(0);
//			if (!(isOperadorBinario(o))) {
//				atomo a = new atomo(o, null);
//				agregarAtomo(a);
//			}
			formula = inser(formula, inserto, pos);
			return formula;

		}
	}
	public static void examinarArgumento() {
		for (int i = 0; i < todasLasFormulas.size(); i++) {
			
			String premisa= todasLasFormulas.get(i);
			examinarPremisa(premisa);
		}
	}
	
	public static void  examinarPremisa(String premisa) {
		
		for (int i = 0; i <premisa.length(); i++) {
			System.out.println("en el for ");
			char o = premisa.charAt(i);
			if (isAtomo(o)) {

				// Atomo
				System.out.println("agregaando atomo ");
				atomo a = new atomo(o, null);
				agregarAtomo(a);
			}
			
		}
		
		
		
	}
	public static boolean isAtomo(char k) {
		  
		if (k != '>' && k != '^' && k != 'v' && k != '-' && k != '¬'&& k != '('&& k != ')'&& k != ' ') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Formulas bien formadas
	 * 
	 * @param operador
	 * @return
	 */
	public static String formulaCorrecta(String operador) {
		String m = "";
		m = operador;
		if (operador.equals("v")) {
			m = "(  )" + operador + "(  )";
		}
		if (operador.equals("^")) {
			m = "(  )" + operador + "(  )";
		}
		if (operador.equals(">")) {
			m = "(  )" + operador + "(  )";
		}
		if (operador.equals("-")) {
			m = "(  )" + operador + "(  )";
		}
		if (operador.equals("¬")) {
			m = "¬" + "(  )";
		}

		return m;
	}

	/**
	 * agrega el atomo
	 * 
	 * @param a
	 */
	public static void agregarAtomo(atomo a) {
		if (atomos.size() == 0) {
			atomos.add(a);
		} else if (!estaRepetido(a)) {
			atomos.add(a);
			System.out.println("atomos en local" + ConseceunciaLogica.getAtomos().toString());
		}
		System.out.println("atomos en local" + ConseceunciaLogica.getAtomos().toString());
	}

	/**
	 * Examina su un atomo se encuentra repetido
	 * 
	 * @param a
	 * @return
	 */
	public static boolean estaRepetido(atomo a) {
		for (int i = 0; i < atomos.size(); i++) {
			if (atomos.get(i).getNombre() == a.getNombre()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Examina si una linea del tiempo esta repetida
	 * 
	 * @param a
	 * @return
	 */
	public static boolean LineaDelTiempoRepetida(Tiempo t) {
		for (int i = 0; i < lineaDelTiempo.size(); i++) {
			if (lineaDelTiempo.get(i).getEspacioTiempo().equals(t.getEspacioTiempo())) {
				posicionEnElTiempo = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * Realiza la tabla de verdad
	 * 
	 * @param tope
	 * @param cantidadDeVeces
	 * @param i
	 * @param t
	 * @param j
	 * @param v
	 * @return
	 */
	public static ArrayList<Integer> hacerValoresDeverdad(int tope, int cantidadDeVeces, int i, ArrayList<Integer> t,
			int j, int v) {

		if (i < cantidadDeVeces) {
			if (j < tope) {
				t.add(v);
				return hacerValoresDeverdad(tope, cantidadDeVeces, i, t, j + 1, v);
			} else {
				if (v == 1) {
					v = 0;
				} else {
					v = 1;
				}
				return hacerValoresDeverdad(tope, cantidadDeVeces, i + 1, t, 0, v);
			}
		}
		return t;
	}

	/**
	 * 
	 * @param it
	 * @param msg
	 * @return
	 */
	private static String recorrido(LinkedList it, String msg) {
		int i = 0;
		String r = msg + "\n";
		while (i < it.size()) {
			r += "\t" + it.get(i).toString() + "";
			i++;
		}
		return (r);
	}

	public static void hacerTablaDeVerdad(Node raiz, Node izquierdo) {
		if (izquierdo.getLeft() != null) {
			hacerTablaDeVerdad(izquierdo, izquierdo.getLeft());
		} else {
			atomo izq = buscarAtomo((char) izquierdo.getValue());
			atomo der = buscarAtomo((char) raiz.getRight().getValue());

			ArrayList<Integer> tabla1 = izq.getTablaVerdad();
			ArrayList<Integer> tabla2 = der.getTablaVerdad();
			String operador = Character.toString((char) raiz.getValue());
			ArrayList<Integer> tablaOp = tablasDeVerdad(operador, tabla1, tabla2);

		}
	}

	public static ArrayList<Integer> TablaPremisa(Node raiz) {

		if (isOp((char) raiz.getValue())) {

			String op = Character.toString((char) (raiz.getValue()));
			return tablasDeVerdad(op, TablaPremisa(raiz.getLeft()), TablaPremisa(raiz.getRight()));
		}
		char n = (char) (raiz.getValue());
		if (n == '¬') {
			String op = Character.toString(n);
			return tablasDeVerdad(op, TablaPremisa(raiz.getLeft()), null);
		} else {
			atomo a = buscarAtomo((char) (raiz.getValue()));
			return a.getTablaVerdad();
		}
	}

	public static boolean isOp(char k) {
		if (k == '>' || k == '^' || k == 'v' || k == '-') {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isOperadorBinario(char k) {
		if (k == '>' || k == '^' || k == 'v' || k == '-' || k == '¬') {
			return true;
		} else {
			return false;
		}
	}

	public static atomo buscarAtomo(char k) {
		for (int i = 0; i < atomos.size(); i++) {

			if (atomos.get(i).getNombre() == k) {
				return atomos.get(i);
			}
		}
		return null;
	}

	/**
	 * Devuelve un arrayList con los valores de verdad dado dos atomos y su operador
	 *
	 * @param operador
	 * @param valores1
	 * @param valores2
	 * @return
	 */
	public static ArrayList<Integer> tablasDeVerdad(String operador, ArrayList<Integer> valores1,
			ArrayList<Integer> valores2) {

		ArrayList<Integer> salida = new ArrayList<Integer>();
		if (operador.equals("¬")) {
			for (int i = 0; i < valores1.size(); i++) {

				if (valores1.get(i) == 1) {

					salida.add(0);

				} else {
					salida.add(1);

				}

			}
		}
		/**
		 * Si el operador es un &
		 */
		if (operador.equals("^")) {

			for (int i = 0; i < valores1.size(); i++) {

				if (valores1.get(i) == 1 && valores2.get(i) == 1) {

					salida.add(1);

				} else {
					salida.add(0);

				}

			}

		}

		/**
		 * Si el operador es un v
		 */
		if (operador.equals("v")) {
			for (int i = 0; i < valores1.size(); i++) {

				if (valores1.get(i) == 1 || valores2.get(i) == 1) {

					salida.add(1);

				} else {
					salida.add(0);

				}

			}
		}

		/**
		 * Si es un entonces
		 */
		if (operador.equals(">")) {

			for (int i = 0; i < valores1.size(); i++) {

				if (valores1.get(i) == valores2.get(i)) {

					salida.add(1);

				} else if (valores2.get(i) == 1) {

					salida.add(1);

				} else {
					salida.add(0);

				}

			}

		}

		/**
		 * Si el operador es si y solo si
		 */
		if (operador.equals("-")) {

			for (int i = 0; i < valores1.size(); i++) {

				if (valores1.get(i) == valores2.get(i)) {

					salida.add(1);

				} else {

					salida.add(0);
				}

			}

		}

		return salida;

	}

	/**
	 * Realiza el arbol sintactico de cada premisa
	 * 
	 * @param p
	 * @param a
	 */
	public static void arbol(String p, Node a) {

		int i = buscaElOperadorPricipal(p, 0, 0, 0);

		char c = p.charAt(i);

		atomo ato = buscarAtomo(c);

		if (c != 'v' && c != '^' && c != '>' && c != '¬' && c != '-') {
			a.setValue(c);
		} else {
			if (c == '¬') {

				a.setValue(c);
				Node iz = new Node(null);
				a.setLeft(iz);

				String nuevo = "";
				for (int j = 3; j < p.length() - 2; j++) {

					nuevo += p.charAt(j);

				}
				arbol(nuevo, iz);
			} else {
				a.setValue(c);
				Node izquierdo = new Node(null);
				Node derecho = new Node(null);
				a.setLeft(izquierdo);
				a.setRight(derecho);
				arbol(p.substring(2, i - 2), izquierdo);

				arbol(p.substring(i + 3, p.length() - 2), derecho);
			}

		}

	}

	/**
	 * Busca el operador principal
	 * 
	 * @param p
	 * @param i
	 * @param suma
	 * @param resta
	 * @return
	 */
	public static int buscaElOperadorPricipal(String p, int i, int suma, int resta) {

		if (p.charAt(0) == '¬') {
			return 0;
		}
		if (p.length() == 1) {
			return i;
		} else {
			if (i < p.length()) {
				if (i > 0 && suma - resta == 0) {
					return i;
				}
				if (p.charAt(i) == '(') {
					return buscaElOperadorPricipal(p, i + 1, suma + 1, resta);
				}
				if (p.charAt(i) == ')') {
					return buscaElOperadorPricipal(p, i + 1, suma, resta + 1);
				}
				return buscaElOperadorPricipal(p, i + 1, suma, resta);
			} else {
				if (i == (p.length())) {
					return i - 2;
				}
			}

		}
		return i;
	}

	public static String inser(String formula, String inserto, int p) {
		StringBuffer sb = new StringBuffer(formula);
		sb.replace(p, p, inserto);
		formula = sb.toString();
		return formula;
	}

	public static boolean validar(String formula, int p) {

		if (p < formula.length()) {
			System.out.println("llego ");
			if (formula.charAt(p) == ' ') {
				System.out.println("paso");

				if (p + 1 < formula.length() && p > 1) {
					System.out.println("la de atras " + formula.charAt(p - 2));
					System.out.println("la de adelante " + formula.charAt(p + 1));
					if (formula.charAt(p - 2) == '(' && formula.charAt(p + 1) == ')') {
						System.out.println("valindando");
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void borrarLineasDelTiempo() {

		for (int i = lineaDelTiempo.size()-1; i > 0; i--) {
			lineaDelTiempo.remove(i);
		}
		lineaDelTiempo.get(0).setLineaActual(1);
		System.out.println("linea temporall " + lineaDelTiempo.toString());
	}

	public static boolean validarPremisa(String p) {

		char l[] = p.toCharArray();
		for (int i = 0; i < l.length; i++) {
			if (l[i] == ' ' && l[i + 1] == ' ') {
				return false;
			}
		}
		return true;
	}

	public static int agujeroNegro(String formula) {
		char l[] = formula.toCharArray();
		System.out.println("for " + Arrays.toString(l));
		for (int i = 2; i < formula.length(); i++) {
			if (i + 1 < formula.length()) {
				if (formula.charAt(i - 2) == '(' && formula.charAt(i + 1) == ')') {

					return i;
				}
			}
		}
		return -1;
	}

	public static int antimateria(String formula, int p) {

		System.out.println("formula   " + formula.length());
		System.out.println("pos cuando me devulvo " + p);
		char l[] = formula.toCharArray();
		System.out.println("for " + Arrays.toString(l));
		if (p < formula.length()) {
			if (formula.charAt(p) == ' ') {

				if (p + 1 < formula.length() && p > 1) {
					if (formula.charAt(p - 2) == '(' && formula.charAt(p + 1) == ')') {

						return p;
					}
				} else {
					if (p + 1 == formula.length()) {
						return antimateria(formula, p - 1);
					}
					return antimateria(formula, p + 1);

				}
			} else {
				if (p + 1 == formula.length()) {
					return antimateria(formula, p - 1);
				}
				return antimateria(formula, p + 1);

			}
			return antimateria(formula, p + 1);
		} else {
			return antimateria(formula, p - 1);
		}

	}

	public static String volverAlPasado(int direccionEspacioTemporal) {

		if (direccionEspacioTemporal == 0) {
			for (int i = 1; i < lineaDelTiempo.size(); i++) {
				if (lineaDelTiempo.get(i).getLineaActual() == 1) {

					Tiempo tn = lineaDelTiempo.get(i - 1);
					tn.setLineaActual(1);
					lineaDelTiempo.set(i - 1, tn);
					Tiempo tn2 = lineaDelTiempo.get(i);
					tn2.setLineaActual(0);
					lineaDelTiempo.set(i, tn2);
					return lineaDelTiempo.get(i - 1).getEspacioTiempo();
				}
			}
		} else if (direccionEspacioTemporal == 1) {
			for (int i = 0; i < lineaDelTiempo.size(); i++) {

				if (lineaDelTiempo.get(i).getLineaActual() == 1) {
					if (i != lineaDelTiempo.size() - 1) {
						Tiempo tn = lineaDelTiempo.get(i + 1);
						tn.setLineaActual(1);
						lineaDelTiempo.set(i + 1, tn);
						Tiempo tn2 = lineaDelTiempo.get(i);
						tn2.setLineaActual(0);
						lineaDelTiempo.set(i, tn2);
						return lineaDelTiempo.get(i + 1).getEspacioTiempo();
					}
				}
			}
		}
		return "null";
	}

	public static ArrayList<atomo> getAtomos() {
		return atomos;
	}

	public static void setAtomos(ArrayList<atomo> atomos) {
		ConseceunciaLogica.atomos = atomos;
	}

	public static ArrayList<String> getTodasLasFormulas() {
		return todasLasFormulas;
	}

	public static void setTodasLasFormulas(ArrayList<String> todasLasFormulas) {
		ConseceunciaLogica.todasLasFormulas = todasLasFormulas;
	}
}
