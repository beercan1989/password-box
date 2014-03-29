package com.jbacon.passwordstorage.functions;

public interface TransformFunction<FromType, ToType> {

    ToType apply(FromType input);

}
