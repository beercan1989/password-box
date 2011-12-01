package com.jbacon.passwordstorage.password.generation;

public enum PasswordGeneratorType {

	AlphaNumberic {
		@Override
		public PasswordGenerator getPasswordGenerator() throws Exception {
			return new AlphaNumbericPasswordGenerator();
		}
	},

	CharSet {
		@Override
		public PasswordGenerator getPasswordGenerator() throws Exception {
			return new CharSetPasswordGenerator();
		}
	},

	Phrase, RandomWord, UnicodePassword;

	public PasswordGenerator getPasswordGenerator() throws Exception {
		throw new Exception("Unsupported PasswordGenerator: " + this.name());
	}

}
