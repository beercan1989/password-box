package com.jbacon.passwordstorage.functions;

public interface FunctionFromTo<From, To> {

    To apply(From input);

}
