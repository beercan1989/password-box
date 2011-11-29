package com.jbacon.passwordstorage.encryption;

import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;

public class EncrypterFactory {

	public Encrypter getEncrypter(final EncryptionType encrypterType) throws NoSuchEncryptionException {
		switch (encrypterType) {
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
			throw new NoSuchEncryptionException("Unsupported encrypter type [" + encrypterType.name() + "]");
		}
	}
}
