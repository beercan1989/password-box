package com.jbacon.passwordstorage.backend.password.generation;

import java.util.Arrays;

public enum CharSetType {

	NUMERIC {
		@Override
		public Character[] get() {
			return new Character[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		}
	},

	LOWER_ALPHA {
		@Override
		public Character[] get() {
			return new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
					'y', 'z' };
		}
	},

	UPPER_ALPHA {
		@Override
		public Character[] get() {
			return new Character[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
					'Y', 'Z' };
		}
	},

	SPECIAL {
		@Override
		public Character[] get() {
			return new Character[] { '!', '£', '$', '%', '^', '&', '*', '-', '=', '+', '_', '@', ':', ';', '/', '?', '.', ',', '<', '>', '"' };
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

	public Character[] get() {
		return null;
	}
}
