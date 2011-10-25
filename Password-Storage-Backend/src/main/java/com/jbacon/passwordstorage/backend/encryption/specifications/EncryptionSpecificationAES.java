package com.jbacon.passwordstorage.backend.encryption.specifications;

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
	public Object get(final String attributeName) {
		return attributeMap.get(attributeName);
	}
}
