package com.jbacon.passwordstorage.encryption.errors;

public class AbstractEncrypterException extends Exception {

	private static final long serialVersionUID = -1128746862562678562L;

	public AbstractEncrypterException() {
		super();
	}

	public AbstractEncrypterException(final String message) {
		super(message);
	}

	public AbstractEncrypterException(final String string, final Throwable throwable) {
		super(string, throwable);
	}

	public AbstractEncrypterException(final Throwable throwable) {
		super(throwable);
	}
}
