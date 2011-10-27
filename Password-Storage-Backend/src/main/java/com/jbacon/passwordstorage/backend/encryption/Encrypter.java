package com.jbacon.passwordstorage.backend.encryption;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;

public abstract class Encrypter {

	public Encrypter() {
		Security.addProvider(new BouncyCastleProvider());
	}

	public abstract void changeEncryptionType(final EncryptionType encryptionType) throws InvalidEncryptionTypeChangeException;
}
