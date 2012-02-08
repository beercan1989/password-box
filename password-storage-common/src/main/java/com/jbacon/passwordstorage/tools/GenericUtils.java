package com.jbacon.passwordstorage.tools;

public class GenericUtils {

	public static <T> boolean areNotNull(final T... objects) {
		for (T object : objects) {
			if (isNull(object)) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean areNull(final T... objects) {
		for (T object : objects) {
			if (isNotNull(object)) {
				return false;
			}
		}
		return true;
	}

	public static <T> boolean isNotNull(final T object) {
		return !isNull(object);
	}

	public static <T> boolean isNull(final T object) {
		return (object == null) ? true : false;
	}
}
