package com.jbacon.passwordstorage.password.generation;

import java.util.Arrays;

public enum CharSet {
    
    NUMERIC(generateChars(48, 10)), //
    LOWER_ALPHA(generateChars(97, 26)), //
    UPPER_ALPHA(generateChars(65, 26)), //
    SPECIAL(add(generateChars(33, 7), generateChars(42, 6), generateChars(58, 7), generateChars(94, 3))), //
    MIXED_ALPHA(concat(LOWER_ALPHA, UPPER_ALPHA)), //
    MIXED_ALPHA_NUMERIC(concat(LOWER_ALPHA, UPPER_ALPHA, NUMERIC));
    
    public static char[] add(final char[] result2, final char[]... c) {
        int totalLength = result2.length;
        for (final char[] array : c) {
            totalLength += array.length;
        }
        final char[] result = Arrays.copyOf(result2, totalLength);
        int offset = result2.length;
        for (final char[] array : c) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    
    public static char[] concat(final CharSet initalChars, final CharSet... additionalChars) {
        char[] result = initalChars.getChars();
        
        for (final CharSet charSet : additionalChars) {
            result = add(result, charSet.getChars());
        }
        
        return result;
    }
    
    private static char[] generateChars(final int firstUnicodePoint, final int numberOfChars) {
        final char[] toReturn = new char[numberOfChars];
        
        for (int i = 0; i < numberOfChars; i++) {
            toReturn[i] = (char) (firstUnicodePoint + i);
        }
        
        return toReturn;
    }
    
    private char[] charArray;
    
    private CharSet(final char[] charArray) {
        this.charArray = charArray;
    }
    
    public char[] getChars() {
        return charArray;
    }
}
