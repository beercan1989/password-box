package com.jbacon.passwordstorage.imported.encryption.specifications;

import java.util.HashMap;
import java.util.Map;

public class EncryptionSpecificationAES implements EncryptionSpecification {

    private static final String KEY_SIZE = "keysize";

    private final Map<String, Object> attributeMap;

    public EncryptionSpecificationAES(final Integer keysize) {
        attributeMap = new HashMap<String, Object>();
        attributeMap.put(KEY_SIZE, keysize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(final String attributeName) {
        return (T) attributeMap.get(attributeName);
    }
}
