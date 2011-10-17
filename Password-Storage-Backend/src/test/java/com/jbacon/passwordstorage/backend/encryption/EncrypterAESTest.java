package com.jbacon.passwordstorage.backend.encryption;

import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.jbacon.passwordstorage.backend.database.DatabaseException;

public class EncrypterAESTest {

	private static byte[] hexStringToByteArray(final String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	private EncrypterAES encrypter;
	private EncrypterUtils encrypterUtils;

	@Before
	public void setup() throws DatabaseException {
		EncrypterFactory encrypterFactory = new EncrypterFactory();
		encrypter = (EncrypterAES) encrypterFactory.getEncrypter(EncrypterType.AES_ENCRYPTER);
		encrypterUtils = new EncrypterUtils();
	}

	@Test
	public void testDecryptWithAes() throws EncrypterException, NoSuchAlgorithmException {
		// byte[] result = encrypter.decryptWithAes(toDecrypt, aesKey);
		fail("Not Implemented Yet");
	}

	@Test
	public void testEncryptWithAes() throws UnsupportedEncodingException, NoSuchAlgorithmException, EncrypterException {
		byte[] toEncrypt = encrypterUtils.stringToByte("HelloWorld");
		byte[] aesKey = encrypterUtils.generateAesEncryptionKey();

		byte[] result = encrypter.encryptWithAes(toEncrypt, aesKey);
	}

}
