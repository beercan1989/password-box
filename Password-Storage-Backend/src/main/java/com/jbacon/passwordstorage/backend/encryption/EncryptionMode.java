package com.jbacon.passwordstorage.backend.encryption;

import javax.crypto.Cipher;

public enum EncryptionMode {

	// WRAP_MODE(Cipher.WRAP_MODE),
	// UNWRAP_MODE(Cipher.UNWRAP_MODE),
	// PUBLIC_KEY(Cipher.PUBLIC_KEY),
	// PRIVATE_KEY(Cipher.PRIVATE_KEY),
	// SECRET_KEY(Cipher.SECRET_KEY),

	DECRYPT_MODE(Cipher.DECRYPT_MODE), ENCRYPT_MODE(Cipher.ENCRYPT_MODE);

	public final int mode;

	private EncryptionMode(final int mode) {
		this.mode = mode;
	}
}
