package com.jbacon.passwordstorage.backend.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.jbacon.passwordstorage.backend.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.backend.encryption.errors.BouncyCastleNotInstalledException;
import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;
import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.backend.encryption.errors.UnlimitedJcePoliciesNotInstalledException;

public class EncrypterAES extends Encrypter {

	private EncryptionType encryptionType;

	public EncrypterAES() {
		encryptionType = EncryptionType.AES_256;
	}

	public EncrypterAES(final EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}

	@Override
	public void changeEncryptionType(final EncryptionType encryptionType) throws InvalidEncryptionTypeChangeException {
		switch (encryptionType) {
		case AES_128:
		case AES_256:
			this.encryptionType = encryptionType;
			break;
		default:
			throw new InvalidEncryptionTypeChangeException();
		}
	}

	public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] cipherText, final byte[] aesKey) throws AbstractEncrypterException {
		try {
			SecretKeySpec secretKeySpecification = new SecretKeySpec(aesKey, encryptionType.algorithmName);
			Cipher cipher = Cipher.getInstance(encryptionType.algorithmName, EncryptionType.PROVIDER_NAME);
			cipher.init(encryptionMode.mode, secretKeySpecification);
			return cipher.doFinal(cipherText);

		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchEncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new NoSuchEncryptionException(e);
		} catch (InvalidKeyException e) {
			if (aesKey.length > 16) {
				throw new UnlimitedJcePoliciesNotInstalledException(e);
			} else {
				throw new NoSuchEncryptionException(e);
			}
		} catch (IllegalBlockSizeException e) {
			throw new NoSuchEncryptionException(e);
		} catch (BadPaddingException e) {
			throw new NoSuchEncryptionException(e);
		} catch (NoSuchProviderException e) {
			throw new BouncyCastleNotInstalledException(e);
		}
	}
}
