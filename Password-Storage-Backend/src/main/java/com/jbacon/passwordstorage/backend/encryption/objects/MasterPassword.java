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

	public String getEncryptedSecretKey() {
		return encryptedSecretKey;
	}

	public String getProfileName() {
		return profileName;
	}

	public String getSalt() {
		return salt;
	}

	public void setEncryptedSecretKey(final String encryptedSecretKey) {
		this.encryptedSecretKey = encryptedSecretKey;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public void setSalt(final String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MasterPassword [profileName=");
		builder.append(profileName);
		builder.append(", encryptedSecretKey=");
		builder.append(encryptedSecretKey);
		builder.append(", salt=");
		builder.append(salt);
		builder.append("]");
		return builder.toString();
	}

}
