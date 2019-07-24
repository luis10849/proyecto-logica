package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import logica.ConseceunciaLogica;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class pasoApaso extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static ArrayList<Integer> satifacibilidadPremisas;
	static ArrayList<String> todasLasFormulas;
	static int[][] tablasDeVerdadPremisas;
	static ArrayList<Integer> tablaDeVerdadConclusion;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public pasoApaso(ArrayList<Integer> satifacibilidadPremisas, ArrayList<String> todasLasFormulas,
			int[][] tablasDeVerdadPremisas, ArrayList<Integer> tablaDeVerdadConclusion) {

		this.satifacibilidadPremisas = satifacibilidadPremisas;
		this.todasLasFormulas = todasLasFormulas;
		this.tablasDeVerdadPremisas = tablasDeVerdadPremisas;
		this.tablaDeVerdadConclusion = tablaDeVerdadConclusion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CONSECUENCIA LOGICA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(204, 11, 370, 44);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 72, 472, 341);
		contentPane.add(scrollPane);

		table = new JTable();
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		resizeColumnWidth(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		String[] columnNames = cargar();
	
		String[][] dataP = convertirMatriz();
		DefaultTableModel miModelo = new DefaultTableModel(dataP, columnNames);
		table.setModel(miModelo);
		
		
		scrollPane.setViewportView(table);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(503, 72, 262, 341);
		contentPane.add(textArea);
	}

	public static String[][] convertirMatriz() {
		String[][] dataP = new String[tablasDeVerdadPremisas.length][tablasDeVerdadPremisas[0].length + 2];
		System.err.println("llego metodo");
		for (int i = 0; i < satifacibilidadPremisas.size(); i++) {
			System.out.println("en el for");
			dataP[i][0] = "" + satifacibilidadPremisas.get(i);
		}

		for (int i = 0; i < tablasDeVerdadPremisas.length; i++) {
			for (int j = 0; j < tablasDeVerdadPremisas[0].length; j++) {
				dataP[i][j + 1] = "" + tablasDeVerdadPremisas[i][j];
			}
		}
		for (int i = 0; i <tablaDeVerdadConclusion.size(); i++) {
			dataP[i][dataP[0].length-1]=""+tablaDeVerdadConclusion.get(i);
		}
		return dataP;
	}

	public static String[] cargar() {

		String[] a = new String[todasLasFormulas.size() + 1];
		a[0] = "ik";
		for (int i = 0; i < todasLasFormulas.size(); i++) {
			a[i + 1] = todasLasFormulas.get(i);
		}
		return a;
	}
	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; //Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}
