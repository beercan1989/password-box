package com.jbacon.passwordstorage.swing.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jbacon.passwordstorage.password.StoredPassword;

public class StoredPasswordTableModel extends AbstractTableModel implements Serializable {

	/**
	 * Eclipse Generated.
	 */
	private static final long serialVersionUID = -6892459980031513925L;

	private static final List<StoredPasswordTableColumns> TABLE_COLUMNS = new ArrayList<StoredPasswordTableColumns>(
			EnumSet.allOf(StoredPasswordTableColumns.class));
	private final List<StoredPassword> tableData = new ArrayList<StoredPassword>();

	public StoredPasswordTableModel() {

	}

	public void add(final StoredPassword storedPassword) {
		tableData.add(storedPassword);
	}

	public void addAll(final List<StoredPassword> storedPasswords) {
		tableData.addAll(storedPasswords);
	}

	@Override
	public int findColumn(final String columnName) {
		return TABLE_COLUMNS.indexOf(StoredPasswordTableColumns.stringToEnum(columnName));
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return TABLE_COLUMNS.get(columnIndex).getType();
	}

	@Override
	public int getColumnCount() {
		return TABLE_COLUMNS.size();
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return TABLE_COLUMNS.get(columnIndex).getName();
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(final int row, final int column) {
		return false;
	}

	@Override
	public void setValueAt(final Object value, final int rowIndex, final int columnIndex) {
		// TODO Auto-generated method stub
	}

}
