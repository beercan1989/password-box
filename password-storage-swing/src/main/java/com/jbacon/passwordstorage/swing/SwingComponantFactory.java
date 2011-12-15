package com.jbacon.passwordstorage.swing;

import javax.swing.JTable;

import com.jbacon.passwordstorage.swing.table.StoredPasswordTableModel;

public final class SwingComponantFactory {

	public static JTable createPasswordJTable(final StoredPasswordTableModel model) {
		JTable table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setModel(model);
		table.setCellSelectionEnabled(true);
		return table;
	}
}