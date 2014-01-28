package com.jbacon.passwordstorage.functions;

public interface AnnonymousFunctionWithException<T extends Throwable> {

    void apply() throws T;

}
