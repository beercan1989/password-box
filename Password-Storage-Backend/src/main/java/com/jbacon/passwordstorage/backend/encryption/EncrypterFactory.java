package com.jbacon.passwordstorage.backend.encryption;

import java.util.EnumMap;
import java.util.Map;

import com.jbacon.passwordstorage.backend.database.DatabaseException;

public class EncrypterFactory {

	private final Map<EncrypterType, Encrypter> encrypters;

	public EncrypterFactory() {
		encrypters = new EnumMap<EncrypterType, Encrypter>(EncrypterType.class);
		encrypters.put(EncrypterType.AES_ENCRYPTER, new EncrypterAES());
		encrypters.put(EncrypterType.PBEWithMD5AndDES_ENCRYPTER, new EncrypterPBEWithMD5AndDES());
	}

	public Encrypter getEncrypter(final EncrypterType encrypterType) throws DatabaseException {
		if (!encrypters.containsKey(encrypterType)) {
			throw new DatabaseException("Unsupported encrypter type [" + encrypterType.name() + "]");
		}
		return encrypters.get(encrypterType);
	}
}
