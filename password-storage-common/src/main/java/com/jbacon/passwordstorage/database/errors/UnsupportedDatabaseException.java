package com.jbacon.passwordstorage.database.errors;

public class UnsupportedDatabaseException extends Exception {

    private static final long serialVersionUID = -8885640080641144708L;

    public UnsupportedDatabaseException() {
        super();
    }

    public UnsupportedDatabaseException(final String message) {
        super(message);
    }

    public UnsupportedDatabaseException(final Throwable throwable) {
        super(throwable);
    }

    public UnsupportedDatabaseException(final String string, final Throwable throwable) {
        super(string, throwable);
    }
}
