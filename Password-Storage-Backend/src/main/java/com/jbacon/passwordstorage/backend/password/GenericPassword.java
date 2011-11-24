package com.jbacon.passwordstorage.backend.password;

import java.sql.Timestamp;

public abstract class GenericPassword {
	private Integer id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String profileName;

	public GenericPassword() {
	}

	public GenericPassword(final Timestamp createdAt, final Timestamp updatedAt, final Integer id, final String profileName) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.id = id;
		this.setProfileName(profileName);
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Integer getId() {
		return id;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(final Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setUpdatedAt(final Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
}
