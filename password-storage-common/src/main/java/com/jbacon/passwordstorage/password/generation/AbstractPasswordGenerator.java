package com.jbacon.passwordstorage.password.generation;

import java.util.Random;

public abstract class AbstractPasswordGenerator implements PasswordGenerator {

	private static final Random NUMBER_GENERATOR = new Random();

	protected static final int DEFAULT_LENGTH = 8;

	protected static final CharSetType DEFAULT_CHARS = CharSetType.MIXED_ALPHA_NUMERIC;

	protected static CharSetType CUSTOM_CHARS = DEFAULT_CHARS;

	protected int LENGTH = DEFAULT_LENGTH;

	protected int getNextRandomInt(final int maximum) {
		return NUMBER_GENERATOR.nextInt(maximum);
	}

	@Override
	public String getPassword(final int length) {
		LENGTH = length;
		return getPassword();
	}

	protected void setLength(final String value) {
		LENGTH = Integer.valueOf(value);
	}
}
