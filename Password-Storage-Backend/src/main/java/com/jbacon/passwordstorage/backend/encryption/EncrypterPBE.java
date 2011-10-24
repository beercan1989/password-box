package com.jbacon.passwordstorage.backend.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.jbacon.passwordstorage.backend.encryption.error.AbstractEncrypterException;
import com.jbacon.passwordstorage.backend.encryption.error.BouncyCastleNotInstalledException;
import com.jbacon.passwordstorage.backend.encryption.error.InvalidEncryptionTypeChangeException;
import com.jbacon.passwordstorage.backend.encryption.error.NoSuchEncryptionException;

public class EncrypterPBE implements Encrypter {

	private EncryptionType encryptionType;

	public EncrypterPBE() {
		encryptionType = EncryptionType.PBE_WITH_MD5_AND_DES;
	}

	public EncrypterPBE(final EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}

	public void changeEncryptionType(final EncryptionType encryptionType) throws InvalidEncryptionTypeChangeException {
		switch (encryptionType) {
		case PBE_WITH_MD5_AND_DES:
			this.encryptionType = encryptionType;
			break;
		default:
			throw new InvalidEncryptionTypeChangeException();
		}
	}

	public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] salt, final byte[] cipherText, final char[] passPhrase)
			throws AbstractEncrypterException {
		try {
			PBEKeySpec keySpecification;
			PBEParameterSpec parameterSpecification;
			SecretKeyFactory keyFactory;
			Cipher cipher;
			SecretKey secretKey;

			parameterSpecification = new PBEParameterSpec(salt, encryptionType.keyLength());
			keySpecification = new PBEKeySpec(passPhrase);
			keyFactory = SecretKeyFactory.getInstance(encryptionType.algorithm());
			secretKey = keyFactory.generateSecret(keySpecification);
			cipher = Cipher.getInstance(encryptionType.algorithm(), encryptionType.provider());
			cipher.init(encryptionMode.mode(), secretKey, parameterSpecification);
			byte[] processedText = cipher.doFinal(cipherText);
			return processedText;

		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchEncryptionException(e);
		} catch (InvalidKeySpecException e) {
			throw new NoSuchEncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new NoSuchEncryptionException(e);
		} catch (InvalidKeyException e) {
			throw new NoSuchEncryptionException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new NoSuchEncryptionException(e);
		} catch (IllegalBlockSizeException e) {
			throw new NoSuchEncryptionException(e);
		} catch (BadPaddingException e) {
			throw new NoSuchEncryptionException(e);
		} catch (NoSuchProviderException e) {
			throw new BouncyCastleNotInstalledException(e);
		}
	}
}
