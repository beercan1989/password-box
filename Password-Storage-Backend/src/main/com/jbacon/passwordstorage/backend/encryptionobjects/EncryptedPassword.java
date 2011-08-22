package com.jbacon.passwordstorage.backend.encryptionobjects;

import java.io.Serializable;

public class EncryptedPassword implements Serializable {

	private static final long serialVersionUID = -1839898907563182564L;

	private String encryptedPasswordName;
	private String encryptedPassword;
	private String encryptedWebsiteUrl;
	private PasswordType passwordType;
	private String profileName;

	public String getEncryptedPasswordName() {
		return encryptedPasswordName;
	}

	public String getProfileName() {
		return profileName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public PasswordType getPasswordType() {
		return passwordType;
	}

	public String getEncryptedWebsiteURL() {
		return encryptedWebsiteUrl;
	}

	public void setEncryptedPasswordName(final String encryptedPasswordName) {
		this.encryptedPasswordName = encryptedPasswordName;
	}

	public void setProfileName(final String profileName) {
		this.profileName = profileName;
	}

	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setPasswordType(final PasswordType passwordType) {
		this.passwordType = passwordType;
	}

	public void setEncryptedWebsiteURL(final String encryptedWebsiteUrl) {
		this.encryptedWebsiteUrl = encryptedWebsiteUrl;
	}

	@Override
	public String toString() {
		return "Name: " + encryptedPasswordName + ", Type: " + passwordType
				+ ", URL: " + encryptedWebsiteUrl;
	}
}
