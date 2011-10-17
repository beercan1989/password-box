package com.jbacon.passwordstorage.backend.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncrypterAES implements Encrypter {
	private static final String ENCRYPTION_TYPE = "AES"; // AES

	public byte[] decryptWithAes(final byte[] toDecrypt, final byte[] aesKey) throws EncrypterException {
		try {
			SecretKeySpec secretKeySpecification = new SecretKeySpec(aesKey, ENCRYPTION_TYPE);
			Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpecification);
			return cipher.doFinal(toDecrypt);
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Algorithm", e);
		} catch (NoSuchPaddingException e) {
			throw new EncrypterException("No Such Encryption Padding Found", e);
		} catch (InvalidKeyException e) {
			throw new EncrypterException("Invalid Encryption Key", e);
		} catch (IllegalBlockSizeException e) {
			throw new EncrypterException(e);
		} catch (BadPaddingException e) {
			throw new EncrypterException(e);
		}
	}

	public byte[] encryptWithAes(final byte[] toEncrypt, final byte[] aesKey) throws EncrypterException {
		try {
			SecretKeySpec secretKeySpecification = new SecretKeySpec(aesKey, ENCRYPTION_TYPE);
			Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpecification);
			return cipher.doFinal(toEncrypt);
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Algorithm", e);
		} catch (NoSuchPaddingException e) {
			throw new EncrypterException("No Such Encryption Padding Found", e);
		} catch (InvalidKeyException e) {
			throw new EncrypterException("Invalid Encryption Key", e);
		} catch (IllegalBlockSizeException e) {
			throw new EncrypterException(e);
		} catch (BadPaddingException e) {
			throw new EncrypterException(e);
		}
	}
}
