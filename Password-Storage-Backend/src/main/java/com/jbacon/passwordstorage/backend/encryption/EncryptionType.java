package com.jbacon.passwordstorage.backend.encryption;

import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.backend.encryption.specifications.EncryptionSpecification;
import com.jbacon.passwordstorage.backend.encryption.specifications.EncryptionSpecificationAES;
import com.jbacon.passwordstorage.backend.encryption.specifications.EncryptionSpecificationPBE;

public enum EncryptionType {

	AES_256("AES", true), AES_128("AES", true), PBE_WITH_MD5_AND_DES("PBE_WITH_MD5_AND_DES", true),

	/** Unsupported - Test Value */
	UNSUPPORTED_TYPE("", false);

	private static final int AES_128_KEY_SIZE = 128;
	private static final int AES_256_KEY_SIZE = 256;
	private static final int PBE_MD5_DES_SALT_SIZE = 8;
	private static final int PBE_MD5_DES_ITERATION_COUNT = 20;
	private static final String PBE_MD5_DES_SALT_ALGORITHM = "SHA1PRNG";

	public static final String PROVIDER_NAME = "BC";
	public final String algorithmName;
	public final boolean isSupported;

	private EncryptionType(final String algorithm, final boolean isSupported) {
		this.algorithmName = algorithm;
		this.isSupported = isSupported;
	}

	public EncryptionSpecification getSpecification() throws NoSuchEncryptionException {
		switch (this) {
		case AES_128:
			return new EncryptionSpecificationAES(AES_128_KEY_SIZE);
		case AES_256:
			return new EncryptionSpecificationAES(AES_256_KEY_SIZE);
		case PBE_WITH_MD5_AND_DES:
			return new EncryptionSpecificationPBE(PBE_MD5_DES_SALT_SIZE, PBE_MD5_DES_ITERATION_COUNT, PBE_MD5_DES_SALT_ALGORITHM);
		default:
			throw new NoSuchEncryptionException();
		}
	}
}
