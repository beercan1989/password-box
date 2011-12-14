package com.jbacon.passwordstorage.swing.table;

import java.sql.Timestamp;

import org.apache.commons.lang.WordUtils;

public enum StoredPasswordTableColumns {
	PASSWORD_ID(Integer.class), //
	PROFILE_NAME(String.class), //
	PASSWORD_NAME(String.class), //
	PASSWORD(String.class), //
	PASSWORD_NOTES(String.class), //
	CREATED_AT(Timestamp.class), //
	UPDATED_AT(Timestamp.class);

	public static StoredPasswordTableColumns stringToEnum(final String name) {
		return valueOf(name.replaceAll(" ", "_").toUpperCase());
	}

	private final Class<?> type;

	StoredPasswordTableColumns(final Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return WordUtils.capitalizeFully(this.name(), new char[] { '_' }).replaceAll("_", " ");
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return getName();
	}
}
