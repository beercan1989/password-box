package com.jbacon.passwordstorage.password.generation;

import static com.jbacon.passwordstorage.password.generation.CharSet.LOWER_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.MIXED_ALPHA_NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.NUMERIC;
import static com.jbacon.passwordstorage.password.generation.CharSet.SPECIAL;
import static com.jbacon.passwordstorage.password.generation.CharSet.UPPER_ALPHA;
import static com.jbacon.passwordstorage.password.generation.CharSet.add;
import static com.jbacon.passwordstorage.password.generation.CharSet.concat;

public enum PasswordGeneratorFactory {

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with just lower case letters in the character set.
	 */
	LETTER {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(LOWER_ALPHA.getChars());
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with just numbers in the character set.
	 */
	NUMBER {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(NUMERIC.getChars());
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with just upper case letters in the character set.
	 */
	CAPITAL {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(UPPER_ALPHA.getChars());
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with lower case letters and numbers in the character set.
	 */
	LETTER_NUMBER {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(concat(LOWER_ALPHA, NUMERIC));
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with upper and lower case letters in the character set.
	 */
	LETTER_CAPITAL {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(MIXED_ALPHA.getChars());
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with upper case letters and numbers in the character set.
	 */
	CAPITAL_NUMBER {
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(concat(NUMERIC, UPPER_ALPHA));
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with numbers, upper and lower case letters in the character set.
	 */
	LETTER_CAPITAL_NUMBER {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(MIXED_ALPHA_NUMERIC.getChars());
		}
	},

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with lower case letters, numbers and special characters in the character set.
	 */
	LETTER_NUMBER_PUNCTUATION {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public PasswordGenerator getPasswordGenerator() {
			return new PasswordGeneratorImpl(concat(LOWER_ALPHA, NUMERIC, SPECIAL));
		}
	};

	/**
	 * Returns a new instance of {@link PasswordGeneratorImpl} with a custom character set.
	 * 
	 * @param chars
	 *            the main body of characters for use in the password generator.
	 * @param extraChars
	 *            any extra individual characters to add into the character set.
	 * 
	 * @return a new instance of the custom PasswordGenerator.
	 */
	public static PasswordGenerator getCustomPasswordGenerator(final char[] chars, final char... extraChars) {
		return new PasswordGeneratorImpl(add(chars, extraChars));
	}

	/**
	 * Returns a new instance of {@link PasswordGenerator}.
	 * 
	 * @return a new instance of the PasswordGenerator.
	 */
	public abstract PasswordGenerator getPasswordGenerator();
}
