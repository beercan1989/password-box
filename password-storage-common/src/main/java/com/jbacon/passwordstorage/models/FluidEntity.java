package com.jbacon.passwordstorage.models;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FluidEntity<T> {

    private T entity;

    public FluidEntity(final T entity) {
        this.set(entity);
    }

    public T get() {
        return entity;
    }

    public void set(final T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
