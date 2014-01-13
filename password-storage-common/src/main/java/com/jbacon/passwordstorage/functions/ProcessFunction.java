package com.jbacon.passwordstorage.functions;

public interface ProcessFunction<ProcessType> {

    void apply(ProcessType input);

}
