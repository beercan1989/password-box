package com.jbacon.passwordstorage.encryption.errors;

public class NoSuchEncryptionException extends AbstractEncrypterException {

	private static final long serialVersionUID = 4876978104021014048L;

	public NoSuchEncryptionException() {
		super("No Such Encrption Cipher.");
	}

	public NoSuchEncryptionException(final String message) {
		super(message);
	}

	public NoSuchEncryptionException(final Throwable throwable) {
		super(throwable.getMessage(), throwable);
	}

}
