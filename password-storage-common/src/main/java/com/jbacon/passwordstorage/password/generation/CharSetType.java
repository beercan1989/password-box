package com.jbacon.passwordstorage.password.generation;

import java.util.Arrays;

public enum CharSetType {

	NUMERIC {
		@Override
		public Character[] get() {
			return generateChars(48, 10); // [0-9]
		}
	},

	LOWER_ALPHA {
		@Override
		public Character[] get() {
			return generateChars(97, 26); // [a-z]
		}
	},

	UPPER_ALPHA {
		@Override
		public Character[] get() {
			return generateChars(65, 26); // [A-Z]
		}
	},

	SPECIAL {
		@Override
		public Character[] get() {
			// return new Character[] { '!', '£', '$', '%', '^', '&', '*', '-',
			// '=', '+', '_', '@', ':', ';', '/', '?', '.', ',', '<', '>', '"'
			// };

			return add(generateChars(33, 7), generateChars(42, 6), generateChars(58, 7), generateChars(94, 3));
		}
	},

	MIXED_ALPHA {
		@Override
		public Character[] get() {
			return concat(LOWER_ALPHA, UPPER_ALPHA);
		}
	},

	MIXED_ALPHA_NUMERIC {
		@Override
		public Character[] get() {
			return concat(LOWER_ALPHA, UPPER_ALPHA, NUMERIC);
		}
	};

	private static <T> T[] add(final T[] first, final T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	public static Character[] concat(final CharSetType first, final CharSetType... rest) {
		Character[] result = first.get();

		for (CharSetType charSet : rest) {
			add(result, charSet.get());
		}

		return result;
	}

	private static Character[] generateChars(final int firstUnicodePoint, final int numberOfChars) {
		Character[] toReturn = new Character[numberOfChars];

		for (int i = 0; i < numberOfChars; i++) {
			toReturn[i] = (char) (firstUnicodePoint + i);
		}

		return toReturn;
	}

	public Character[] get() {
		return null;
	}
}
