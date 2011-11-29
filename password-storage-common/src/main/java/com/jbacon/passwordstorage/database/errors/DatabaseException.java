package com.jbacon.passwordstorage.database.errors;

public abstract class DatabaseException extends Exception {
    private static final long serialVersionUID = 3512692824289047375L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(final String message) {
        super(message);
    }

    public DatabaseException(final Throwable throwable) {
        super(throwable);
    }

    public DatabaseException(final String string, final Throwable throwable) {
        super(string, throwable);
    }
}
