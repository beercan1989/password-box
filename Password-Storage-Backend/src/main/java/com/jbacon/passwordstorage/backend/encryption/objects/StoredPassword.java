package com.jbacon.passwordstorage.backend.encryption.objects;

public class StoredPassword {

	private String encryptedPasswordName;
	private String encryptedPassword;
	private String encryptedPasswordNotes;
	private String profileName;

	public StoredPassword() {
	}

	public StoredPassword(final String encryptedPasswordName, final String profileName, final String encryptedPassword, final String encryptedPasswordNotes) {
		this.encryptedPasswordName = encryptedPasswordName;
		this.profileName = profileName;
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

	public String getProfileName() {
		return profileName;
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

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StoredPassword [encryptedPasswordName=");
		builder.append(encryptedPasswordName);
		builder.append(", encryptedPassword=");
		builder.append(encryptedPassword);
		builder.append(", encryptedPasswordNotes=");
		builder.append(encryptedPasswordNotes);
		builder.append(", profileName=");
		builder.append(profileName);
		builder.append("]");
		return builder.toString();
	}
}
