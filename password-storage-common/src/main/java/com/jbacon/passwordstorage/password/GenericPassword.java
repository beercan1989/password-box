package com.jbacon.passwordstorage.password;

import java.util.Date;

import com.jbacon.passwordstorage.formatters.TimestampFormatter;

public abstract class GenericPassword {
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
    private String profileName;

    public GenericPassword() {
    }

    public GenericPassword(final Date createdAt, final Date updatedAt, final Integer id, final String profileName) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        setProfileName(profileName);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtAsString() {
        return TimestampFormatter.format(createdAt);
    }

    public Integer getId() {
        return id;
    }

    public String getProfileName() {
        return profileName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedAtAsString() {
        return TimestampFormatter.format(updatedAt);
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setProfileName(final String profileName) {
        this.profileName = profileName;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
