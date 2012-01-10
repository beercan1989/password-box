package com.jbacon.passwordstorage.formatters;

import java.text.SimpleDateFormat;

public class TimestampFormatter {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

	public static final String format(final java.sql.Date timestamp) {
		return DATE_FORMAT.format(timestamp);
	}

	public static final String format(final java.sql.Timestamp timestamp) {
		return DATE_FORMAT.format(timestamp);
	}

	public static final String format(final java.util.Date timestamp) {
		return DATE_FORMAT.format(timestamp);
	}
}
