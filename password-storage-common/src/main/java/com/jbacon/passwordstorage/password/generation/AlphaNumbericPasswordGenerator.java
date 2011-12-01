package com.jbacon.passwordstorage.password.generation;

public class AlphaNumbericPasswordGenerator extends AbstractPasswordGenerator {

	@Override
	public String getPassword() {
		CharSetPasswordGenerator alphaNumbericPasswordGenerator = new CharSetPasswordGenerator();
		PasswordGeneratorProperty[] properties = new PasswordGeneratorProperty[] {
				new PasswordGeneratorProperty(PasswordGeneratorProperty.PasswordLength, LENGTH),//
				new PasswordGeneratorProperty(PasswordGeneratorProperty.UseCharSet, CharSetType.MIXED_ALPHA_NUMERIC) };
		return alphaNumbericPasswordGenerator.getPassword(properties);
	}

}
