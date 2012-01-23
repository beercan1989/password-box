package com.jbacon.passwordstorage.password;

import java.sql.Timestamp;

import com.jbacon.passwordstorage.encryption.EncryptionType;

public class MasterPassword extends GenericPassword {

	private String encryptedSecretKey;
	private String salt;

	private EncryptionType masterPasswordEncryptionType;
	private EncryptionType storedPasswordEncryptionType;

	public MasterPassword() {
		super();
	}

	public MasterPassword(final String profileName, final String encryptedSecretKey, final String salt, final Timestamp createdAt, final Timestamp updatedAt,
			final Integer id, final EncryptionType masterPasswordEncryptionType, final EncryptionType storedPasswordEncryptionType) {
		super(createdAt, updatedAt, id, profileName);
		this.encryptedSecretKey = encryptedSecretKey;
		this.salt = salt;
		this.masterPasswordEncryptionType = masterPasswordEncryptionType;
		this.storedPasswordEncryptionType = storedPasswordEncryptionType;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public EncryptionType getMasterPasswordEncryptionType() {
		return masterPasswordEncryptionType;
	}

	public String getSalt() {
		return salt;
	}

	public EncryptionType getStoredPasswordEncryptionType() {
		return storedPasswordEncryptionType;
	}

	public void setEncryptedSecretKey(final String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public void setMasterPasswordEncryptionType(final EncryptionType masterPasswordEncryptionType) {
		this.masterPasswordEncryptionType = masterPasswordEncryptionType;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	public void setStoredPasswordsEncryptionType(final EncryptionType storedPasswordEncryptionType) {
		this.storedPasswordEncryptionType = storedPasswordEncryptionType;
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
