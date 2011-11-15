package com.jbacon.passwordstorage.backend.encryption.objects;

import java.sql.Timestamp;

public class StoredPassword {

	private Integer id;
	private String encryptedPasswordName;
	private String encryptedPassword;
	private String encryptedPasswordNotes;
	private String profileName;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public StoredPassword() {
	}

	public StoredPassword(final String encryptedPasswordName, final String profileName, final String encryptedPassword, final String encryptedPasswordNotes) {
		this.encryptedPasswordName = encryptedPasswordName;
		this.profileName = profileName;
		this.encryptedPassword = encryptedPassword;
		this.encryptedPasswordNotes = encryptedPasswordNotes;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
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

	public Integer getId() {
		return id;
	}

	public String getProfileName() {
		return profileName;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(final Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setEncryptedPasswordName(final String encryptedPasswordName) {
		this.encryptedPasswordName = encryptedPasswordName;
	}

	public void setEncryptedPasswordNotes(final String encryptedWebsiteUrl) {
		this.encryptedPasswordNotes = encryptedWebsiteUrl;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public void setUpdatedAt(final Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StoredPassword [id=");
		builder.append(id);
		builder.append(", encryptedPasswordName=");
		builder.append(encryptedPasswordName);
		builder.append(", encryptedPassword=");
		builder.append(encryptedPassword);
		builder.append(", encryptedPasswordNotes=");
		builder.append(encryptedPasswordNotes);
		builder.append(", profileName=");
		builder.append(profileName);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}

}
