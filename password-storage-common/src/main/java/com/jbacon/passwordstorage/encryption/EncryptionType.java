package com.jbacon.passwordstorage.encryption;

import static com.jbacon.passwordstorage.utils.GenericValidationUtil.areNull;
import static com.jbacon.passwordstorage.utils.GenericValidationUtil.isNotNull;
import static com.jbacon.passwordstorage.utils.GenericValidationUtil.isNull;

import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.specifications.EncryptionSpecification;
import com.jbacon.passwordstorage.encryption.specifications.EncryptionSpecificationAES;
import com.jbacon.passwordstorage.encryption.specifications.EncryptionSpecificationPBE;

public enum EncryptionType {

	AES_256("AES", true),

	AES_128("AES", true),

	PBE_WITH_MD5_AND_DES("PbeWithMd5AndDes", true),

	PBE_WITH_SHA1_AND_256_AES_CBC_BC("PbeWithSHA1And256BitAES-CBC-BC", true),

	PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC("PbeWithSHAAnd3-KeyTripleDES-CBC", true),

	PBE_WITH_SHA_AND_TWOFISH_CBC("PbeWithSHAAndTwofish-CBC", true);

	/** Unsupported - Test Value */
	// UNSUPPORTED_TYPE("", false);

	private static final int AES_128_KEY_SIZE = 128;
	private static final int AES_256_KEY_SIZE = 256;
	public static final int DEFAULT_SALT_SIZE = 8;
	private static final int DEFAULT_ITERATION_COUNT = 20;
	private static final String DEFAULT_SALT_ALGORITHM = "SHA1PRNG";

	public static final String PROVIDER_NAME = "BC";

	public static boolean areValid(final EncryptionType... encryptionTypes) {

		if (areNull(encryptionTypes)) {
			return false;
		}

		for (EncryptionType encryptionType : encryptionTypes) {
			if (!isValid(encryptionType)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValid(final EncryptionType encryptionType) {

		if (isNull(encryptionType)) {
			return false;
		}

		try {
			return isNotNull(encryptionType.getEncrypter());
		} catch (NoSuchEncryptionException e) {
			return false;
		}
	}

	public final String algorithmName;

	public final boolean isSupported;

	private EncryptionType(final String algorithm, final boolean isSupported) {
		this.algorithmName = algorithm;
		this.isSupported = isSupported;
	}

	public Encrypter getEncrypter() throws NoSuchEncryptionException {
		switch (this) {
		case AES_128:
			return new EncrypterAES(EncryptionType.AES_128);
		case AES_256:
			return new EncrypterAES(EncryptionType.AES_256);
		case PBE_WITH_MD5_AND_DES:
			return new EncrypterPBE(EncryptionType.PBE_WITH_MD5_AND_DES);
		case PBE_WITH_SHA1_AND_256_AES_CBC_BC:
			return new EncrypterPBE(EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC);
		case PBE_WITH_SHA_AND_TWOFISH_CBC:
			return new EncrypterPBE(EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC);
		case PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC:
			return new EncrypterPBE(EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC);
		default:
			throw new NoSuchEncryptionException("Unsupported encrypter type [" + name() + "]");
		}
	}

	public EncryptionSpecification getSpecification() throws NoSuchEncryptionException {
		switch (this) {
		case AES_128:
			return new EncryptionSpecificationAES(AES_128_KEY_SIZE);
		case AES_256:
			return new EncryptionSpecificationAES(AES_256_KEY_SIZE);
		case PBE_WITH_MD5_AND_DES:
		case PBE_WITH_SHA_AND_TWOFISH_CBC:
		case PBE_WITH_SHA1_AND_256_AES_CBC_BC:
		case PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC:
			return new EncryptionSpecificationPBE(DEFAULT_SALT_SIZE, DEFAULT_ITERATION_COUNT, DEFAULT_SALT_ALGORITHM);
		default:
			throw new NoSuchEncryptionException();
		}
	}
}
