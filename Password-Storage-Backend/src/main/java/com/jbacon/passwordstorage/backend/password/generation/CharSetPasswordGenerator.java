package com.jbacon.passwordstorage.backend.password.generation;

public class CharSetPasswordGenerator extends AbstractPasswordGenerator implements PasswordGenerator {

	@Override
	public String getPassword() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i <= LENGTH; i++) {
			stringBuilder.append(CHAR_SET_CHARS[getNextRandomInt(CHAR_SET_CHARS.length)]);
		}

		return stringBuilder.toString();
	}

	@Override
	public String getPassword(final int length) {
		LENGTH = length;
		return getPassword();
	}

	@Override
	public String getPassword(final PasswordGeneratorProperty... properties) {
		for (PasswordGeneratorProperty property : properties) {
			if (PasswordGeneratorProperty.UseCharSet.equalsIgnoreCase(property.name)) {
				setCharSet(property.value.toString());
			}
			if (PasswordGeneratorProperty.PasswordLength.equalsIgnoreCase(property.name)) {
				LENGTH = Integer.valueOf((String) property.value);
			}
		}
		return null;
	}

	private void setCharSet(final String charSet) {

	}

}
