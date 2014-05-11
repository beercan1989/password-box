package com.jbacon.passwordstorage.encryption.errors;

public class InvalidEncryptionTypeForSaltGeneration extends Exception {
    
    private static final long serialVersionUID = 1214391968720704824L;
    
    public InvalidEncryptionTypeForSaltGeneration() {
        super();
    }
    
    public InvalidEncryptionTypeForSaltGeneration(final String message) {
        super(message);
    }
    
    public InvalidEncryptionTypeForSaltGeneration(final String string, final Exception exception) {
        super(string, exception);
    }
    
    public InvalidEncryptionTypeForSaltGeneration(final Exception exception) {
        super(exception);
    }
}
