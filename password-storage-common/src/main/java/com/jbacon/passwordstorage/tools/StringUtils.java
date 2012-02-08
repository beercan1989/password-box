package com.jbacon.passwordstorage.tools;

import static com.jbacon.passwordstorage.tools.GenericUtils.isNotNull;

public class StringUtils {
	public static final String BLANK = "";
	public static final String SPACE = " ";
	public static final String UNDER_SCORE = "_";
	public static final String COMMA = ",";
	public static final String SEMI_COLON = ";";

	public static boolean areEmpty(final String... strings) {
		for (String string : strings) {
			if (isNotEmpty(string)) {
				return false;
			}
		}
		return true;
	}

	public static boolean areNotEmpty(final String... strings) {
		for (String string : strings) {
			if (isEmpty(string)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(final String string) {
		return (isNotNull(string)) ? string.trim().isEmpty() : true;
	}

	public static boolean isNotEmpty(final String string) {
		return !isEmpty(string);
	}

}
