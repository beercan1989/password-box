package com.jbacon.passwordstorage.backend.encryption.tools;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.jbacon.passwordstorage.backend.encryption.EncryptionType;
import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;
import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;

public class EncrypterUtils {

	private static final String SALT_SIZE = "saltSize";
	private static final String KEY_SIZE = "keysize";
	private static final String SECURE_SALT_ALGORITHM = "secureSaltAlgorithm";
	private static final String TEXT_ENCODING_TYPE = "UTF-8";

	public String byteToString(final byte[] byteToString) throws UnsupportedEncodingException {
		return new String(byteToString, TEXT_ENCODING_TYPE);
	}

	public byte[] generateAesEncryptionKey(final EncryptionType encryptionType) throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchEncryptionException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType.algorithmName);
		keyGenerator.init((Integer) encryptionType.getSpecification().get(KEY_SIZE));
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}

	public byte[] generateSalt(final EncryptionType encryptionType) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchEncryptionException,
			InvalidEncryptionTypeChangeException {
		return generateSalt(encryptionType, (Integer) encryptionType.getSpecification().get(SALT_SIZE));
	}

	public byte[] generateSalt(final EncryptionType encryptionType, final Integer numberOfBytes) throws NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchEncryptionException, InvalidEncryptionTypeChangeException {
		switch (encryptionType) {
		case PBE_WITH_MD5_AND_DES:
		case PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC:
		case PBE_WITH_SHA_AND_TWOFISH_CBC:
		case PBE_WITH_SHA512_AND_AES_CBC:
			break;
		default:
			throw new InvalidEncryptionTypeChangeException();
		}
		byte[] salt = new byte[numberOfBytes];
		SecureRandom saltGen = SecureRandom.getInstance((String) encryptionType.getSpecification().get(SECURE_SALT_ALGORITHM));
		saltGen.nextBytes(salt);
		return salt;
	}

	public byte[] stringToByte(final String stringToByte) throws UnsupportedEncodingException {
		return stringToByte.getBytes(TEXT_ENCODING_TYPE);
	}

	public char[] stringToChar(final String stringToChar) {
		return stringToChar.toCharArray();
	}
}
