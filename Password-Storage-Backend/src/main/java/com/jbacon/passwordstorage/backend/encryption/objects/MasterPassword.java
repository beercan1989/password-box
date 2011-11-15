package com.jbacon.passwordstorage.backend.encryption.objects;

import java.sql.Timestamp;

public class MasterPassword {

	private Integer id;
	private String profileName;
	private String encryptedSecretKey;
	private String salt;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public MasterPassword() {
	}

	public MasterPassword(final String profileName, final String encryptedSecretKey, final String salt) {
		this.profileName = profileName;
		this.encryptedSecretKey = encryptedSecretKey;
		this.salt = salt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public Integer getId() {
		return id;
	}

	public String getProfileName() {
		return profileName;
	}

	public String getSalt() {
		return salt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(final Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setEncryptedSecretKey(final String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	public void setUpdatedAt(final Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MasterPassword [id=");
		builder.append(id);
		builder.append(", profileName=");
		builder.append(profileName);
		builder.append(", encryptedSecretKey=");
		builder.append(encryptedSecretKey);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}

}
