package com.jbacon.passwordstorage.password;

import java.util.Date;

public class StoredPassword extends GenericPassword {

    private String encryptedPasswordName;
    private String encryptedPassword;
    private String encryptedPasswordNotes;

    public StoredPassword() {
        super();
    }

    public StoredPassword(final String encryptedPasswordName, final String profileName, final String encryptedPassword,
            final String encryptedPasswordNotes, final Date createdAt, final Date updatedAt, final Integer id) {
        super(createdAt, updatedAt, id, profileName);
        this.encryptedPasswordName = encryptedPasswordName;
        this.encryptedPassword = encryptedPassword;
        this.encryptedPasswordNotes = encryptedPasswordNotes;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getEncryptedPasswordName() {
        return encryptedPasswordName;
    }

    public String getEncryptedPasswordNotes() {
        return encryptedPasswordNotes;
    }

    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setEncryptedPasswordName(final String encryptedPasswordName) {
        this.encryptedPasswordName = encryptedPasswordName;
    }

    public void setEncryptedPasswordNotes(final String encryptedWebsiteUrl) {
        encryptedPasswordNotes = encryptedWebsiteUrl;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StoredPassword [id=");
        builder.append(getId());
        builder.append(", encryptedPasswordName=");
        builder.append(encryptedPasswordName);
        builder.append(", encryptedPassword=");
        builder.append(encryptedPassword);
        builder.append(", encryptedPasswordNotes=");
        builder.append(encryptedPasswordNotes);
        builder.append(", profileName=");
        builder.append(getProfileName());
        builder.append(", createdAt=");
        builder.append(getCreatedAt());
        builder.append(", updatedAt=");
        builder.append(getUpdatedAt());
        builder.append("]");
        return builder.toString();
    }

}
