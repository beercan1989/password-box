package com.jbacon.passwordstorage.encryption.specifications;

public interface EncryptionSpecification {
	public <T> T get(final String attributeName);
}
