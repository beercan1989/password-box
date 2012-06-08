package com.jbacon.passwordstorage.password.generation;

public class PasswordGeneratorProperty {
    
    static final String UseCharSet = "Character-Set-Property";
    
    static final String PasswordLength = "Password-Length-Property";
    
    static final PasswordGeneratorProperty useCharSet(final Character[] charSet) {
        return new PasswordGeneratorProperty(UseCharSet, charSet);
    }
    
    static final PasswordGeneratorProperty usePasswordLength(final Integer length) {
        return new PasswordGeneratorProperty(PasswordLength, length);
    }
    
    public final String name;
    public final Object value;
    
    protected PasswordGeneratorProperty(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }
}
