package com.jbacon.passwordstorage.backend.password.generation;

import java.util.Random;

public abstract class AbstractPasswordGenerator {

	protected static final char[] ALPHA_NUMERIC_CHARS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final Random NUMBER_GENERATOR = new Random();

	protected static final int DEFAULT_LENGTH = 8;

	protected static char[] CHAR_SET_CHARS = ALPHA_NUMERIC_CHARS;

	protected static int LENGTH = DEFAULT_LENGTH;

	protected int getNextRandomInt(final int maximum) {
		return NUMBER_GENERATOR.nextInt(maximum);
	}
}
