package com.jbacon.passwordstorage.password.generation;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGeneratorImpl implements PasswordGenerator {

	private final Random numberGenerator = new SecureRandom();
	private char[] characterArray;
	private int length;

	/**
	 * Create an instance with the default settings.
	 * 
	 * With a <strong>Character Set</strong> of <em>Mixed Alpha Numeric</em> characters and a <strong>Password Length</strong> of <em>8</em>.
	 */
	protected PasswordGeneratorImpl() {
		characterArray = CharSet.MIXED_ALPHA_NUMERIC.getChars();
		length = 8;
	}

	/**
	 * Create an instance with a custom character set.
	 * 
	 * With a <strong>Character Set</strong> of the characters in parameter <em>chars</em> and a <strong>Password Length</strong> of <em>8</em>.
	 * 
	 * @param chars
	 *            The array of characters to use.
	 */
	protected PasswordGeneratorImpl(final char[] chars) {
		characterArray = chars;
		length = 8;
	}

	/**
	 * Create an instance with a custom character set and password length.
	 * 
	 * With a <strong>Character Set</strong> of the characters in parameter <em>chars</em> and a <strong>Password Length</strong> of value in parameter
	 * <em>length</em>.
	 * 
	 * @param chars
	 *            The array of characters to use.
	 * @param length
	 *            The length of password to generate.
	 */
	protected PasswordGeneratorImpl(final char[] chars, final int length) {
		characterArray = chars;
		this.length = length;
	}

	/**
	 * Create an instance with a custom password length.
	 * 
	 * With a <strong>Character Set</strong> of <em>Mixed Alpha Numeric</em> characters and a <strong>Password Length</strong> of value in parameter
	 * <em>length</em>.
	 * 
	 * @param length
	 *            The length of password to generate.
	 */
	protected PasswordGeneratorImpl(final int length) {
		characterArray = CharSet.MIXED_ALPHA_NUMERIC.getChars();
		this.length = length;
	}

	private int getNextRandomInt(final int maximum) {
		return numberGenerator.nextInt(maximum);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		final StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i <= length; i++) {
			stringBuilder.append(characterArray[getNextRandomInt(characterArray.length)]);
		}

		return stringBuilder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword(final int length) {
		this.length = length;
		return getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword(final PasswordGeneratorProperty... properties) {
		for (final PasswordGeneratorProperty property : properties) {
			if (PasswordGeneratorProperty.PasswordLength.equalsIgnoreCase(property.name)) {
				length = (Integer) property.value;
			}
			if (PasswordGeneratorProperty.UseCharSet.equalsIgnoreCase(property.name)) {
				characterArray = (char[]) property.value;
			}
		}
		return getPassword();
	}
}
