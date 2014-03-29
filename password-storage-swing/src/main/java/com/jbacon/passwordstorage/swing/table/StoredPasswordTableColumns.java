package com.jbacon.passwordstorage.swing.table;

import org.apache.commons.lang.WordUtils;

import com.jbacon.passwordstorage.utils.StringUtil;

public enum StoredPasswordTableColumns {
	ID(Integer.class), //
	PROFILE_NAME(String.class), //
	PASSWORD_NAME(String.class), //
	PASSWORD(String.class), //
	PASSWORD_NOTES(String.class), //
	CREATED_AT(String.class), //
	UPDATED_AT(String.class);

	public static StoredPasswordTableColumns stringToEnum(final String name) {
		return valueOf(name.replaceAll(StringUtil.SPACE, StringUtil.UNDER_SCORE).toUpperCase());
	}

	private final Class<?> type;

	StoredPasswordTableColumns(final Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return WordUtils.capitalizeFully(this.name().replaceAll(StringUtil.UNDER_SCORE, StringUtil.SPACE));
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return getName();
	}
}
