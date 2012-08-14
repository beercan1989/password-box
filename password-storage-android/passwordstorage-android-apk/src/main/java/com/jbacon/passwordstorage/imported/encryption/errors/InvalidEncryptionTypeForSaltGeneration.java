package com.jbacon.passwordstorage.imported.encryption.errors;

public class InvalidEncryptionTypeForSaltGeneration extends AbstractEncrypterException {

    private static final long serialVersionUID = 1214391968720704824L;

    public InvalidEncryptionTypeForSaltGeneration() {
        super();
    }

    public InvalidEncryptionTypeForSaltGeneration(final String message) {
        super(message);
    }

    public InvalidEncryptionTypeForSaltGeneration(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public InvalidEncryptionTypeForSaltGeneration(final Throwable throwable) {
        super(throwable);
    }
}
