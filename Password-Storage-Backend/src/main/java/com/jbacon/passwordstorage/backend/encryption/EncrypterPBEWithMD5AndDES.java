package com.jbacon.passwordstorage.backend.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncrypterPBEWithMD5AndDES implements Encrypter {
	private static final int ENCRYPTION_ITERATION_COUNT = 20;
	private static final String ENCRYPTION_TYPE = "PBEWithMD5AndDES";

	public byte[] decryptWithPassword(final byte[] salt, final byte[] toDecrypt, final char[] passPhrase) throws EncrypterException {
		try {
			PBEKeySpec pbeKeySpecification;
			PBEParameterSpec pbeParameterSpecification;
			SecretKeyFactory keyFactory;
			Cipher cipher;
			SecretKey secretKey;

			pbeParameterSpecification = new PBEParameterSpec(salt, ENCRYPTION_ITERATION_COUNT);
			pbeKeySpecification = new PBEKeySpec(passPhrase);
			keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_TYPE);
			secretKey = keyFactory.generateSecret(pbeKeySpecification);
			cipher = Cipher.getInstance(ENCRYPTION_TYPE);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpecification);
			byte[] decryptedText = cipher.doFinal(toDecrypt);
			return decryptedText;
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Algorithm", e);
		} catch (InvalidKeySpecException e) {
			throw new EncrypterException("Invalid PBEncryption Key Spec", e);
		} catch (NoSuchPaddingException e) {
			throw new EncrypterException("No Such Encryption Padding Found", e);
		} catch (InvalidKeyException e) {
			throw new EncrypterException("Invalid Encryption Key", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncrypterException("Invalid Algorithm Params", e);
		} catch (IllegalBlockSizeException e) {
			throw new EncrypterException(e);
		} catch (BadPaddingException e) {
			throw new EncrypterException(e);
		}
	}

	public byte[] encryptWithPassword(final byte[] salt, final byte[] toEncrypt, final char[] passPhrase) throws EncrypterException {
		try {
			PBEKeySpec pbeKeySpecification;
			PBEParameterSpec pbeParameterSpecification;
			SecretKeyFactory keyFactory;
			Cipher cipher;
			SecretKey secretKey;

			pbeParameterSpecification = new PBEParameterSpec(salt, ENCRYPTION_ITERATION_COUNT);
			pbeKeySpecification = new PBEKeySpec(passPhrase);
			keyFactory = SecretKeyFactory.getInstance(ENCRYPTION_TYPE);
			secretKey = keyFactory.generateSecret(pbeKeySpecification);
			cipher = Cipher.getInstance(ENCRYPTION_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpecification);
			byte[] encryptedText = cipher.doFinal(toEncrypt);
			return encryptedText;
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Algorithm", e);
		} catch (InvalidKeySpecException e) {
			throw new EncrypterException("Invalid PBEncryption Key Spec", e);
		} catch (NoSuchPaddingException e) {
			throw new EncrypterException("No Such Encryption Padding Found", e);
		} catch (InvalidKeyException e) {
			throw new EncrypterException("Invalid Encryption Key", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncrypterException("Invalid Algorithm Params", e);
		} catch (IllegalBlockSizeException e) {
			throw new EncrypterException(e);
		} catch (BadPaddingException e) {
			throw new EncrypterException(e);
		}
	}
}
