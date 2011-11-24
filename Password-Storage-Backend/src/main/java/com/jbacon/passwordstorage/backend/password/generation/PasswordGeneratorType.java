package com.jbacon.passwordstorage.backend.password.generation;

public enum PasswordGeneratorType {
	/**
	 * Generates a password containing random letters and numbers from the
	 * English alphabet.
	 */
	AlphaNumberic {
		@Override
		public PasswordGenerator getPasswordGenerator() throws Exception {
			return new AlphaNumbericPasswordGenerator();
		}
	},

	/**
	 * 
	 */
	CharSet,

	/**
	 * 
	 */
	Phrase,

	/**
	 * 
	 */
	RandomWord,

	/**
	 * 
	 */
	UnicodePassword;

	public PasswordGenerator getPasswordGenerator() throws Exception {
		throw new Exception("Unsupported PasswordGenerator: " + this.name());
	}

}
