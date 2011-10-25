package com.jbacon.passwordstorage.backend.encryption;

import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;

public interface Encrypter {
	public void changeEncryptionType(final EncryptionType encryptionType) throws InvalidEncryptionTypeChangeException;
}
