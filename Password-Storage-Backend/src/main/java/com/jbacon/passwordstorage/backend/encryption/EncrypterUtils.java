package com.jbacon.passwordstorage.backend.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncrypterUtils {
	private static final int DEFAULT_SALT_LENGTH = 8;
	private static final int AES_ENCRYPTION_KEY_LENGTH = 256;
	private static final String SECURE_SALT_ALGORITHM = "SHA1PRNG";
	private static final String TEXT_ENCODING_TYPE = "UTF-8";

	public String byteToString(final byte[] byteToString) throws UnsupportedEncodingException {
		return new String(byteToString, TEXT_ENCODING_TYPE);
	}

	public byte[] generateAesEncryptionKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(EncrypterAES.ENCRYPTION_TYPE);
		keyGenerator.init(AES_ENCRYPTION_KEY_LENGTH);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {
		return generateSalt(DEFAULT_SALT_LENGTH);
	}

	public byte[] generateSalt(final int numberOfBytes) throws NoSuchAlgorithmException {
		byte[] salt = new byte[numberOfBytes];
		SecureRandom saltGen = SecureRandom.getInstance(SECURE_SALT_ALGORITHM);
		saltGen.nextBytes(salt);
		return salt;
	}

	public byte[] stringToByte(final String stringToByte) throws UnsupportedEncodingException {
		return stringToByte.getBytes(TEXT_ENCODING_TYPE);
	}
}
