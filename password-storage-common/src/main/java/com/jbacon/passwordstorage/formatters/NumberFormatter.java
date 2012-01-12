package com.jbacon.passwordstorage.formatters;

import java.text.DecimalFormat;

public class NumberFormatter {
	private static final DecimalFormat LEADING_ZERO_NUMBER_FORMATTER = new DecimalFormat("#######00");

	public static String format(final Number number) {
		return LEADING_ZERO_NUMBER_FORMATTER.format(number);
	}
}
