package com.jbacon.passwordstorage.swing;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public final class SwingComponantFactory {

	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source model new javax.swing.table.DefaultTableModel(new java.lang.Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, }, new java.lang.String[] { "ID", "Profile Name",
				"Password Name [E]", "Password [E]", "Password Notes [E]" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return columnEditables[column];
			}
		}
	 */
	public static JTable createPasswordJTable(TableModel model) {
		JTable table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setModel(model);
		table.setCellSelectionEnabled(true);
		return table;
	}
}