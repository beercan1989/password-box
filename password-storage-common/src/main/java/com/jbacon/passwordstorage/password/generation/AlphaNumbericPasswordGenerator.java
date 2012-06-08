package com.jbacon.passwordstorage.password.generation;

public class AlphaNumbericPasswordGenerator extends AbstractPasswordGenerator {
    
    @Override
    public String getPassword() {
        final CharSetPasswordGenerator alphaNumbericPasswordGenerator = new CharSetPasswordGenerator();
        final PasswordGeneratorProperty[] properties = new PasswordGeneratorProperty[] { new PasswordGeneratorProperty(PasswordGeneratorProperty.PasswordLength, LENGTH),//
                new PasswordGeneratorProperty(PasswordGeneratorProperty.UseCharSet, CharSet.MIXED_ALPHA_NUMERIC.get()) };
        return alphaNumbericPasswordGenerator.getPassword(properties);
    }
    
}
