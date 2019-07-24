package vistas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRenderer extends DefaultTableCellRenderer {
	private int columna;
	private Component componente;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {

		int c = 0;
		componente = super.getTableCellRendererComponent(table, value, selected, focused, row, column);

		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				if (table.getValueAt(i,j).equals("1")) {
					c++;
				}
			}
			System.out.println("c  "+c);
			if (c == table.getColumnCount()) {
				componente.setBackground(Color.GREEN);
				c = 0;
			} else {
				componente.setBackground(Color.RED);
				c = 0;
			}
		}

		return componente;
	}
}
