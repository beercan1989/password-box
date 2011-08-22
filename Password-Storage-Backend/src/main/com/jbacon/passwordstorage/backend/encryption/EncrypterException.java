package com.jbacon.passwordstorage.backend.encryption;

public class EncrypterException extends Exception {

	private static final long serialVersionUID = -1041082585694947134L;

	public EncrypterException() {
		super();
	}

	public EncrypterException(final String message) {
		super(message);
	}

	public EncrypterException(final Throwable throwable) {
		super(throwable);
	}

	public EncrypterException(final String string, final Throwable throwable) {
		super(string, throwable);
	}
}
