package com.jbacon.passwordstorage.models;

public interface Defaultable<T> {

    public boolean isDefault();

    public boolean isNotDefault();

    public T getDefault();

}
