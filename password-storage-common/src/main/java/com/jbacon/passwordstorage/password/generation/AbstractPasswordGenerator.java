package com.jbacon.passwordstorage.password.generation;

import java.util.Random;

public abstract class AbstractPasswordGenerator implements PasswordGenerator {
    
    protected static final int DEFAULT_LENGTH = 8;
    
    protected static final Character[] DEFAULT_CHARS = CharSet.MIXED_ALPHA_NUMERIC.get();
    
    protected static Character[] CUSTOM_CHARS = DEFAULT_CHARS;
    
    private final Random NUMBER_GENERATOR = new Random();
    
    protected int LENGTH = DEFAULT_LENGTH;
    
    protected int getNextRandomInt(final int maximum) {
        return NUMBER_GENERATOR.nextInt(maximum);
    }
    
    @Override
    public String getPassword(final int length) {
        LENGTH = length;
        return getPassword();
    }
    
    @Override
    public String getPassword(final PasswordGeneratorProperty... properties) {
        for (final PasswordGeneratorProperty property : properties) {
            if (PasswordGeneratorProperty.PasswordLength.equalsIgnoreCase(property.name)) {
                LENGTH = (Integer) property.value;
            }
        }
        return getPassword();
    }
    
    protected void setLength(final String value) {
        LENGTH = Integer.valueOf(value);
    }
}
