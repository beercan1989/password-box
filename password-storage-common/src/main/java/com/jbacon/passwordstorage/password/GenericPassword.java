package com.jbacon.passwordstorage.password;

import java.sql.Timestamp;

import com.jbacon.passwordstorage.formatters.TimestampFormatter;

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

	public String getCreatedAtAsString() {
		return TimestampFormatter.format(createdAt);
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

	public String getUpdatedAtAsString() {
		return TimestampFormatter.format(updatedAt);
	}

	public void setCreatedAt(final Timestamp createdAt) {
		this.createdAt = createdAt;
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
}
