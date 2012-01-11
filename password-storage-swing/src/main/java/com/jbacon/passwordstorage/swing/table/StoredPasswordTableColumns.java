package com.jbacon.passwordstorage.swing.table;

import org.apache.commons.lang.WordUtils;

import com.jbacon.passwordstorage.tools.StringUtils;

public enum StoredPasswordTableColumns {
	ID(Integer.class), //
	PROFILE_NAME(String.class), //
	PASSWORD_NAME(String.class), //
	PASSWORD(String.class), //
	PASSWORD_NOTES(String.class), //
	CREATED_AT(String.class), //
	UPDATED_AT(String.class);

	public static StoredPasswordTableColumns stringToEnum(final String name) {
		return valueOf(name.replaceAll(StringUtils.SPACE, StringUtils.UNDER_SCORE).toUpperCase());
	}

	private final Class<?> type;

	StoredPasswordTableColumns(final Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return WordUtils.capitalizeFully(this.name().replaceAll(StringUtils.UNDER_SCORE, StringUtils.SPACE));
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return getName();
	}
}
