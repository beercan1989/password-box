package com.jbacon.passwordstorage.functions;

public interface AnnonymousFunctionWithException<E extends Exception> {

    void apply() throws E;

}
