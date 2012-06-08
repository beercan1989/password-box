package com.jbacon.passwordstorage.password.generation;

import java.util.Arrays;

public enum CharSet {
    
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
    
    public static <T> T[] add(final T[] original, final T[]... additional) {
        int totalLength = original.length;
        for (final T[] array : additional) {
            totalLength += array.length;
        }
        final T[] result = Arrays.copyOf(original, totalLength);
        int offset = original.length;
        for (final T[] array : additional) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    
    public static Character[] concat(final CharSet initalChars, final CharSet... additionalChars) {
        Character[] result = initalChars.get();
        
        for (final CharSet charSet : additionalChars) {
            result = add(result, charSet.get());
        }
        
        return result;
    }
    
    private static Character[] generateChars(final int firstUnicodePoint, final int numberOfChars) {
        final Character[] toReturn = new Character[numberOfChars];
        
        for (int i = 0; i < numberOfChars; i++) {
            toReturn[i] = (char) (firstUnicodePoint + i);
        }
        
        return toReturn;
    }
    
    public abstract Character[] get();
}
