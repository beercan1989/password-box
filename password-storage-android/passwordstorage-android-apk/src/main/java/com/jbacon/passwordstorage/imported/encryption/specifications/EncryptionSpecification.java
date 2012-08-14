package com.jbacon.passwordstorage.imported.encryption.specifications;

public interface EncryptionSpecification {
    public <T> T get(final String attributeName);
}
