package com.jbacon.passwordstorage.password.generation;

public class CharSetPasswordGenerator extends AbstractPasswordGenerator {
    
    @Override
    public String getPassword() {
        final StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i <= LENGTH; i++) {
            stringBuilder.append(CUSTOM_CHARS[getNextRandomInt(CUSTOM_CHARS.length)]);
        }
        
        return stringBuilder.toString();
    }
    
    @Override
    public String getPassword(final PasswordGeneratorProperty... properties) {
        for (final PasswordGeneratorProperty property : properties) {
            if (PasswordGeneratorProperty.UseCharSet.equalsIgnoreCase(property.name)) {
                setCharSet((Character[]) property.value);
            }
        }
        return getPassword(properties);
    }
    
    private void setCharSet(final Character[] charSet) {
        CUSTOM_CHARS = charSet;
    }
    
}
