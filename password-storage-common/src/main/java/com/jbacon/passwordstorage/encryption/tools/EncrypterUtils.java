package com.jbacon.passwordstorage.encryption.tools;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.InvalidEncryptionTypeForSaltGeneration;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;

public class EncrypterUtils {

	private static final String SALT_SIZE = "saltSize";
	private static final String KEY_SIZE = "keysize";
	private static final String SECURE_SALT_ALGORITHM = "secureSaltAlgorithm";
	private static final String TEXT_ENCODING_TYPE = "UTF-8";

	/**
	 * @param byteToChar
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static char[] byteToChar(final byte[] byteToChar) throws UnsupportedEncodingException {
		return new String(byteToChar).toCharArray();
	}

	public static String byteToHexString(final byte[] byteToString) {
		return new String(Hex.encodeHex(byteToString));
	}

	/**
	 * @param byteToString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String byteToString(final byte[] byteToString) throws UnsupportedEncodingException {
		return new String(byteToString, TEXT_ENCODING_TYPE);
	}

	/**
	 * @param charToByte
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] charToByte(final char[] charToByte) throws UnsupportedEncodingException {
		return new String(charToByte).getBytes(TEXT_ENCODING_TYPE);
	}

	/**
	 * @param charToString
	 * @return
	 */
	public static String charToString(final char[] charToString) {
		return new String(charToString);
	}

	/**
	 * @param encryptionType
	 * @return
	 * @throws AbstractEncrypterException
	 */
	public static byte[] generateAesEncryptionKey(final EncryptionType encryptionType) throws AbstractEncrypterException {
		try {
			Integer keySize = encryptionType.getSpecification().get(KEY_SIZE);
			KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType.algorithmName);
			keyGenerator.init(keySize);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new AbstractEncrypterException(e);
		} catch (NoSuchEncryptionException e) {
			throw new AbstractEncrypterException(e);
		}
	}

	/**
	 * @param encryptionType
	 * @return
	 * @throws AbstractEncrypterException
	 */
	public static byte[] generateSalt(final EncryptionType encryptionType) throws AbstractEncrypterException {
		try {
			Integer saltSize = encryptionType.getSpecification().get(SALT_SIZE);
			return generateSalt(encryptionType, saltSize);
		} catch (NoSuchEncryptionException e) {
			throw new AbstractEncrypterException(e);
		}
	}

	/**
	 * @param encryptionType
	 * @param numberOfBytes
	 * @return
	 * @throws AbstractEncrypterException
	 */
	public static byte[] generateSalt(final EncryptionType encryptionType, final Integer numberOfBytes) throws AbstractEncrypterException {
		try {
			switch (encryptionType) {
			case PBE_WITH_MD5_AND_DES:
			case PBE_WITH_SHA_AND_TWOFISH_CBC:
			case PBE_WITH_SHA1_AND_256_AES_CBC_BC:
			case PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC:
				byte[] salt = new byte[numberOfBytes];
				String secureSaltAlgorithm = encryptionType.getSpecification().get(SECURE_SALT_ALGORITHM);
				SecureRandom saltGen = SecureRandom.getInstance(secureSaltAlgorithm);
				saltGen.nextBytes(salt);
				return salt;
			default:
				throw new InvalidEncryptionTypeForSaltGeneration("No such supported passwordbased encryption.");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new AbstractEncrypterException(e);
		} catch (NoSuchEncryptionException e) {
			throw new AbstractEncrypterException(e);
		}
	}

	/**
	 * @param hexToByte
	 * @return
	 * @throws DecoderException
	 */
	public static byte[] hexStringToByte(final String hexToByte) throws DecoderException {
		return Hex.decodeHex(hexToByte.toCharArray());
	}

	/**
	 * @param stringToByte
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] stringToByte(final String stringToByte) throws UnsupportedEncodingException {
		return stringToByte.getBytes(TEXT_ENCODING_TYPE);
	}

	/**
	 * @param stringToChar
	 * @return
	 */
	public static char[] stringToChar(final String stringToChar) {
		return stringToChar.toCharArray();
	}
}
