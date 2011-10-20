package com.jbacon.passwordstorage.backend.encryption;

import java.util.EnumMap;
import java.util.Map;

import com.jbacon.passwordstorage.backend.database.DatabaseException;

public class EncrypterFactory {

	private final Map<EncryptionType, Encrypter> encrypters;

	public EncrypterFactory() {
		encrypters = new EnumMap<EncryptionType, Encrypter>(EncryptionType.class);
		encrypters.put(EncryptionType.AES, new EncrypterAES());
		encrypters.put(EncryptionType.PBE_WITH_MD5_AND_DES, new EncrypterPBEWithMD5AndDES());
	}

	public Encrypter getEncrypter(final EncryptionType encrypterType) throws DatabaseException {
		if (!encrypters.containsKey(encrypterType)) {
			throw new DatabaseException("Unsupported encrypter type [" + encrypterType.name() + "]");
		}
		return encrypters.get(encrypterType);
	}
}
