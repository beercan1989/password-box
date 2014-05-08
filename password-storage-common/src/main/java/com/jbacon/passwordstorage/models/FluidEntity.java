package com.jbacon.passwordstorage.models;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FluidEntity<T> implements Defaultable<T> {
    
    private T entity;
    private boolean set;
    private final T defaultEntity;
    
    public static <T> FluidEntity<T> createWithDefault(final T defaultEntity) {
        return new FluidEntity<T>(defaultEntity, defaultEntity);
    }
    
    public static <T> FluidEntity<T> createWithNoDefault(final T entity) {
        return new FluidEntity<T>(entity, null);
    }
    
    public static <T> FluidEntity<T> createEmpty() {
        return new FluidEntity<T>();
    }
    
    public FluidEntity(final T entity, final T defaultEntity) {
        this.set = true;
        this.entity = entity;
        this.defaultEntity = defaultEntity;
    }
    
    public FluidEntity() {
        this.set = false;
        this.entity = null;
        this.defaultEntity = null;
    }
    
    @Override
    public T get() {
        return entity;
    }
    
    public boolean isSet() {
        return set;
    }
    
    /**
     * Reset the FluidEntity to the default value in <code>defaultEntity</code> and mark as not set.
     */
    public void reset() {
        this.set = false;
        this.entity = defaultEntity;
    }
    
    public void set(final T entity) {
        this.set = true;
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
