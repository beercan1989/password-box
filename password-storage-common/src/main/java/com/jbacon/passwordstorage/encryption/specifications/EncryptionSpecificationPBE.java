package com.jbacon.passwordstorage.encryption.specifications;

import java.util.HashMap;
import java.util.Map;

public class EncryptionSpecificationPBE implements EncryptionSpecification {

	private static final String SALT_SIZE = "saltSize";
	private static final String ITERATION_COUNT = "iterationCount";
	private static final String SECURE_SALT_ALGORITHM = "secureSaltAlgorithm";

	private final Map<String, Object> attributeMap;

	public EncryptionSpecificationPBE(final Integer saltSize, final Integer iterationCount, final String secureSaltAlgorithm) {
		attributeMap = new HashMap<String, Object>();
		attributeMap.put(SALT_SIZE, saltSize);
		attributeMap.put(ITERATION_COUNT, iterationCount);
		attributeMap.put(SECURE_SALT_ALGORITHM, secureSaltAlgorithm);
	}

	@Override
	public Object get(final String attributeName) {
		return attributeMap.get(attributeName);
	}
}
