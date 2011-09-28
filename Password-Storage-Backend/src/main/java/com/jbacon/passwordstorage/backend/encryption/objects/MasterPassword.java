package com.jbacon.passwordstorage.backend.encryption.objects;

public class MasterPassword {

	private String profileName;
	private String encryptedSecretKey;
	private String salt;

	public MasterPassword() {
	}

	public MasterPassword(final String profileName, final String encryptedSecretKey, final String salt) {
		this.profileName = profileName;
		this.encryptedSecretKey = encryptedSecretKey;
		this.salt = salt;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public void setEncryptedSecretKey(final String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "Name: " + profileName;
	}
}
