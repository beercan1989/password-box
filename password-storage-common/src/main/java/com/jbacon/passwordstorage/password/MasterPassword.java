package com.jbacon.passwordstorage.password;

import java.sql.Timestamp;

public class MasterPassword extends GenericPassword {

	private String encryptedSecretKey;
	private String salt;

	public MasterPassword() {
		super();
	}

	public MasterPassword(final String profileName, final String encryptedSecretKey, final String salt, final Timestamp createdAt, final Timestamp updatedAt,
			final Integer id) {
		super(createdAt, updatedAt, id, profileName);
		this.encryptedSecretKey = encryptedSecretKey;
		this.salt = salt;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public String getSalt() {
		return salt;
	}

	public void setEncryptedSecretKey(final String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		// builder.append("MasterPassword [id=");
		// builder.append(getId());
		// builder.append(", profileName=");
		builder.append(getProfileName());
		// builder.append(", encryptedSecretKey=");
		// builder.append(encryptedSecretKey);
		// builder.append(", salt=");
		// builder.append(salt);
		// builder.append(", createdAt=");
		// builder.append(getCreatedAt());
		// builder.append(", updatedAt=");
		// builder.append(getUpdatedAt());
		// builder.append("]");
		return builder.toString();
	}

}
