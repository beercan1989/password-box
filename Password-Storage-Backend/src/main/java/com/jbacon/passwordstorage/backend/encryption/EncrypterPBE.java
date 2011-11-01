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

import com.jbacon.passwordstorage.backend.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;
import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;

public class EncrypterPBE extends Encrypter {

	private static final String ITERATION_COUNT = "iterationCount";
	private EncryptionType encryptionType;

	public EncrypterPBE() {
		encryptionType = EncryptionType.PBE_WITH_MD5_AND_DES;
	}

	public EncrypterPBE(final EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}

	@Override
	public void changeEncryptionType(final EncryptionType encryptionType) throws InvalidEncryptionTypeChangeException {
		switch (encryptionType) {
		case PBE_WITH_MD5_AND_DES:
		case PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC:
		case PBE_WITH_SHA_AND_TWOFISH_CBC:
		case PBE_WITH_SHA512_AND_AES_CBC:
			this.encryptionType = encryptionType;
			break;
		default:
			throw new InvalidEncryptionTypeChangeException();
		}
	}

	public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] salt, final byte[] cipherText, final char[] passPhrase)
			throws AbstractEncrypterException, InvalidAlgorithmParameterException {
		try {
			PBEKeySpec keySpecification;
			PBEParameterSpec parameterSpecification;
			SecretKeyFactory keyFactory;
			Cipher cipher;
			SecretKey secretKey;

			parameterSpecification = new PBEParameterSpec(salt, (Integer) encryptionType.getSpecification().get(ITERATION_COUNT));
			keySpecification = new PBEKeySpec(passPhrase);
			keyFactory = SecretKeyFactory.getInstance(encryptionType.algorithmName);
			secretKey = keyFactory.generateSecret(keySpecification);
			cipher = Cipher.getInstance(encryptionType.algorithmName
			// , EncryptionType.PROVIDER_NAME
					);
			cipher.init(encryptionMode.mode, secretKey, parameterSpecification);
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
		}
		// catch (NoSuchProviderException e) {
		// throw new BouncyCastleNotInstalledException(e);
		// }
	}
}
