package com.jbacon.passwordstorage.models;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FluidEntity<T> implements Defaultable<T> {
    
    private T entity;
    private final T defaultEntity;
    
    public static <T> FluidEntity<T> createWithDefault(final T defaultEntity) {
        return new FluidEntity<T>(defaultEntity, defaultEntity);
    }
    
    public static <T> FluidEntity<T> createWithNoDefault(final T entity) {
        return new FluidEntity<T>(entity, null);
    }
    
    public FluidEntity(final T entity, final T defaultEntity) {
        this.entity = entity;
        this.defaultEntity = defaultEntity;
    }
    
    public T get() {
        return entity;
    }
    
    public void set(final T entity) {
        this.entity = entity;
    }
    
    @Override
    public boolean isNotDefault() {
        return !isDefault();
    }
    
    @Override
    public boolean isDefault() {
        return EqualsBuilder.reflectionEquals(entity, defaultEntity);
    }
    
    @Override
    public T getDefault() {
        return defaultEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
