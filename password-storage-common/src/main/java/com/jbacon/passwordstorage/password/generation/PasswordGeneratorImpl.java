package com.jbacon.passwordstorage.password.generation;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGeneratorImpl implements PasswordGenerator {
    
    private final Random numberGenerator = new SecureRandom();
    private char[] characterArray;
    private int length;
    
    PasswordGeneratorImpl() {
        characterArray = CharSet.MIXED_ALPHA_NUMERIC.getChars();
        length = 8;
    }
    
    PasswordGeneratorImpl(final char[] chars) {
        characterArray = chars;
        length = 8;
    }
    
    PasswordGeneratorImpl(final char[] chars, final int length) {
        characterArray = chars;
        this.length = length;
    }
    
    PasswordGeneratorImpl(final int length) {
        characterArray = CharSet.MIXED_ALPHA_NUMERIC.getChars();
        this.length = length;
    }
    
    private int getNextRandomInt(final int maximum) {
        return numberGenerator.nextInt(maximum);
    }
    
    @Override
    public String getPassword() {
        final StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i <= length; i++) {
            stringBuilder.append(characterArray[getNextRandomInt(characterArray.length)]);
        }
        
        return stringBuilder.toString();
    }
    
    @Override
    public String getPassword(final int length) {
        this.length = length;
        return getPassword();
    }
    
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
