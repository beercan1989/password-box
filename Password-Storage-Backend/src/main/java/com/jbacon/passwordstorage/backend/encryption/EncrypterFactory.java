package com.jbacon.passwordstorage.backend.encryption;

import java.util.EnumMap;
import java.util.Map;

import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;

public class EncrypterFactory {

	private final Map<EncryptionType, Encrypter> encrypters;

	public EncrypterFactory() {
		encrypters = new EnumMap<EncryptionType, Encrypter>(EncryptionType.class);
		encrypters.put(EncryptionType.AES_256, new EncrypterAES());
		encrypters.put(EncryptionType.PBE_WITH_MD5_AND_DES, new EncrypterPBE());
		encrypters.put(EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC, new EncrypterPBE());
		encrypters.put(EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC, new EncrypterPBE());
		encrypters.put(EncryptionType.PBE_WITH_SHA512_AND_AES_CBC, new EncrypterPBE());
	}

	public Encrypter getEncrypter(final EncryptionType encrypterType) throws NoSuchEncryptionException {
		if (!encrypters.containsKey(encrypterType)) {
			throw new NoSuchEncryptionException("Unsupported encrypter type [" + encrypterType.name() + "]");
		}

		return encrypters.get(encrypterType);
	}
}
