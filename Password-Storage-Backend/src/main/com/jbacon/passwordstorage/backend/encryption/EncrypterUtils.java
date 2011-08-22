package com.jbacon.passwordstorage.backend.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncrypterUtils {
	private static final int DEFAULT_SALT_LENGTH = 8;
	private static final int AES_ENCRYPTION_KEY_LENGTH = 128;
	private static final String SECURE_SALT_ALGORITHM = "SHA1PRNG";
	private static final String TEXT_ENCODING_TYPE = "UTF-8";
	private static final String AES_ENCRYPTION_KEY_TYPE = "AES";

	public byte[] generateAesEncryptionKey() throws EncrypterException {
		try {
			KeyGenerator keyGenerator = KeyGenerator
					.getInstance(AES_ENCRYPTION_KEY_TYPE);
			keyGenerator.init(AES_ENCRYPTION_KEY_LENGTH);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Key Algorithm", e);
		}
	}

	public byte[] generateSalt() throws EncrypterException {
		return generateSalt(DEFAULT_SALT_LENGTH);
	}

	public byte[] generateSalt(final int numberOfBytes)
			throws EncrypterException {
		try {
			byte[] salt = new byte[numberOfBytes];
			SecureRandom saltGen = SecureRandom.getInstance(SECURE_SALT_ALGORITHM);
			saltGen.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Salt Generation Algorithm", e);
		}
	}

	public byte[] stringToByte(final String stringToByte)
			throws EncrypterException {
		try {
			return stringToByte.getBytes(TEXT_ENCODING_TYPE);
		} catch (UnsupportedEncodingException e) {
			throw new EncrypterException(
					"Failed to convert the string to a byte array", e);
		}
	}

	public String byteToString(final byte[] byteToString)
			throws EncrypterException {
		try {
			return new String(byteToString, TEXT_ENCODING_TYPE);
		} catch (UnsupportedEncodingException e) {
			throw new EncrypterException(
					"Failed to convert the string to a byte array", e);
		}
	}
}
