package com.jbacon.passwordstorage.encryption;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;
import com.jbacon.passwordstorage.encryption.errors.UnlimitedJcePoliciesNotInstalledException;

public class EncrypterAES extends Encrypter {

	private final EncryptionType encryptionType;

	public EncrypterAES(final EncryptionType encryptionType) {
		this.encryptionType = encryptionType;
	}

	public byte[] doCiper(final EncryptionMode encryptionMode, final byte[] cipherText, final byte[] aesKey) throws AbstractEncrypterException {
		try {
			SecretKeySpec secretKeySpecification = new SecretKeySpec(aesKey, encryptionType.algorithmName);
			Cipher cipher = Cipher.getInstance(encryptionType.algorithmName, EncryptionType.PROVIDER_NAME);
			cipher.init(encryptionMode.mode, secretKeySpecification);
			return cipher.doFinal(cipherText);
		} catch (InvalidKeyException e) {
			if (aesKey.length > 16) {
				throw new UnlimitedJcePoliciesNotInstalledException(e);
			} else {
				throw new NoSuchEncryptionException(e);
			}
		} catch (Exception e) {
			throw new AbstractEncrypterException(e);
		}
		//
		// catch (NoSuchAlgorithmException e) {
		// throw new NoSuchEncryptionException(e);
		// } catch (NoSuchPaddingException e) {
		// throw new NoSuchEncryptionException(e);
		// } catch (InvalidKeyException e) {
		// if (aesKey.length > 16) {
		// throw new UnlimitedJcePoliciesNotInstalledException(e);
		// } else {
		// throw new NoSuchEncryptionException(e);
		// }
		// } catch (IllegalBlockSizeException e) {
		// throw new NoSuchEncryptionException(e);
		// } catch (BadPaddingException e) {
		// throw new NoSuchEncryptionException(e);
		// } catch (NoSuchProviderException e) {
		// throw new BouncyCastleNotInstalledException(e);
		// }
	}
}
