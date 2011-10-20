package com.jbacon.passwordstorage.backend.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncrypterAES implements Encrypter {
	public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] cipherText, final byte[] aesKey) throws EncrypterException {
		try {
			SecretKeySpec secretKeySpecification = new SecretKeySpec(aesKey, EncryptionType.AES.algorithm());
			Cipher cipher = Cipher.getInstance(EncryptionType.AES.algorithm(), EncryptionType.AES.provider());
			cipher.init(encryptionMode.mode(), secretKeySpecification);
			return cipher.doFinal(cipherText);
		} catch (NoSuchAlgorithmException e) {
			throw new EncrypterException("No Such Encryption Algorithm", e);
		} catch (NoSuchPaddingException e) {
			throw new EncrypterException("No Such Encryption Padding Found", e);
		} catch (InvalidKeyException e) {
			throw new EncrypterException("Invalid Encryption Key - You may need the Unlimited JCE Policies installing.", e);
		} catch (IllegalBlockSizeException e) {
			throw new EncrypterException(e);
		} catch (BadPaddingException e) {
			throw new EncrypterException(e);
		} catch (NoSuchProviderException e) {
			throw new EncrypterException("Bouncy Castle library has not been installed/provided.", e);
		}
	}
}
