package com.jbacon.passwordstorage.backend.password.generation;

public class PasswordGeneratorProperty {

	static final String UseCharSet = "Use-This-Character-Set-Property";

	static final String PasswordLength = "Password-Length";

	static final PasswordGeneratorProperty useCharSet(final String charSet) {
		return new PasswordGeneratorProperty(UseCharSet, charSet);
	}

	static final PasswordGeneratorProperty usePasswordLength(final Integer length) {
		return new PasswordGeneratorProperty(PasswordLength, length);
	}

	public final String name;
	public final Object value;

	public PasswordGeneratorProperty(final String name, final Object value) {
		this.name = name;
		this.value = value;
	}
}
