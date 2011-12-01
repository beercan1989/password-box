package com.jbacon.passwordstorage.password.generation;

public class CharSetPasswordGenerator extends AbstractPasswordGenerator {

	@Override
	public String getPassword() {
		StringBuilder stringBuilder = new StringBuilder();
		Character[] charactersToSelect = CUSTOM_CHARS.get();

		for (int i = 0; i <= LENGTH; i++) {
			stringBuilder.append(charactersToSelect[getNextRandomInt(charactersToSelect.length)]);
		}

		return stringBuilder.toString();
	}

	@Override
	public String getPassword(final PasswordGeneratorProperty... properties) {
		for (PasswordGeneratorProperty property : properties) {
			if (PasswordGeneratorProperty.UseCharSet.equalsIgnoreCase(property.name)) {
				setCharSet(property.value.toString());
			}
		}
		return getPassword(properties);
	}

	private void setCharSet(final String charSet) {
		CUSTOM_CHARS = CharSetType.valueOf(charSet);
	}

}
