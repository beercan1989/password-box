package com.jbacon.passwordstorage.encryption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import com.jbacon.passwordstorage.encryption.EncrypterAES;
import com.jbacon.passwordstorage.encryption.EncrypterFactory;
import com.jbacon.passwordstorage.encryption.EncryptionMode;
import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.NoSuchEncryptionException;

public class EncrypterAESTest {

	private EncrypterAES encrypter;
	private final byte[] toEncrypt = new byte[] { 72, 101, 108, 108, 111, 87, 111, 114, 108, 100 };
	private final byte[] toDecrypt = new byte[] { 64, -32, -57, 114, 0, 55, -73, -29, -33, 106, -70, 41, 32, 55, 92, -14 };

	private final byte[] aesKey = new byte[] { -57, -92, -38, -41, -8, -26, -13, -121, -100, -31, -106, 43, -64, -41, -57, 22, -55, -82, -99, -5, -123, -9,
			-19, -106, 15, -65, -102, 16, -23, -88, -18, 26 };

	@Before
	public void setupBeforeTest() throws NoSuchEncryptionException {
		final EncrypterFactory encrypterFactory = new EncrypterFactory();
		encrypter = (EncrypterAES) encrypterFactory.getEncrypter(EncryptionType.AES_256);
	}

	@Test
	public void shouldDecryptWithAes() throws AbstractEncrypterException, UnsupportedEncodingException, NoSuchAlgorithmException {
		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, toDecrypt, aesKey);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldEncryptWithAes() throws AbstractEncrypterException {
		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, toEncrypt, aesKey);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecrypt)));
	}
}
