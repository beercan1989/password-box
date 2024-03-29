package com.jbacon.passwordstorage.password.generation;

public interface PasswordGenerator {

	/**
	 * Generates a password of the default length (8), in the chosen style.
	 * 
	 * @return the generated password.
	 */
	public String getPassword();

	/**
	 * Generates a password of the provided length, in the chosen style.
	 * 
	 * @param length
	 *            The length of the password to generate.
	 * @return the generated password.
	 */
	public String getPassword(int length);

	/**
	 * Generates a password with the provided properties, in the chosen style.
	 * 
	 * NOTE: Not all properties available work for every PasswordGenerator.
	 * 
	 * @param properties
	 *            One or more property describing the requirements for the password.
	 * @return the generated password.
	 * 
	 * @deprecated Use the {@link PasswordGeneratorFactory} to choose the character set.
	 */
	@Deprecated
	public String getPassword(PasswordGeneratorProperty... properties);
}
