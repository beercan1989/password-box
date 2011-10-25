package com.jbacon.passwordstorage.backend.encryption.errors;

public class InvalidEncryptionTypeChangeException extends AbstractEncrypterException {

	private static final long serialVersionUID = 8129527503763592732L;

	public InvalidEncryptionTypeChangeException() {
		super("Invalid Encryption Type To Change To.");
	}

	public InvalidEncryptionTypeChangeException(final Throwable throwable) {
		super("Invalid Encryption Type To Change To.", throwable);
	}

}
