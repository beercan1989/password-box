package com.jbacon.passwordstorage.imported.encryption.errors;

public class BouncyCastleNotInstalledException extends AbstractEncrypterException {

    private static final long serialVersionUID = 5246025187768111503L;

    public BouncyCastleNotInstalledException() {
        super("Bouncy Castle library has not been installed/provided.");
    }

    public BouncyCastleNotInstalledException(final Throwable throwable) {
        super("Bouncy Castle library has not been installed/provided.", throwable);
    }
}
