package com.jbacon.passwordstorage.backend.encryption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.security.InvalidAlgorithmParameterException;

import org.junit.Before;
import org.junit.Test;

import com.jbacon.passwordstorage.backend.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;

public class EncrypterPBETest {

	private EncrypterPBE encrypter;
	private final char[] passPhrase = new char[] { 'M', 'y', 'P', 'a', 's', 's', 'w', 'o', 'r', 'd' };
	private final byte[] toEncrypt = new byte[] { 77, 121, 32, 77, 101, 115, 115, 97, 103, 101, 32, 84, 111, 32, 69, 110, 99, 114, 121, 112, 116 };
	private final byte[] toDecrypt = new byte[] { 14, -2, -89, -15, 47, 93, 69, 30, 37, -49, -112, 67, -98, -61, -86, -36, -33, 33, 21, 11, -114, -127, 78, 100 };
	private final byte[] salt = new byte[] { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

	@Before
	public void setupBeforeTest() throws NoSuchEncryptionException {
		final EncrypterFactory encrypterFactory = new EncrypterFactory();
		encrypter = (EncrypterPBE) encrypterFactory.getEncrypter(EncryptionType.PBE_WITH_MD5_AND_DES);
	}

	@Test
	public void shouldDecryptWithPassword() throws AbstractEncrypterException, InvalidAlgorithmParameterException {
		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, toDecrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldEncryptWithPassword() throws AbstractEncrypterException, InvalidAlgorithmParameterException {
		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, toEncrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecrypt)));
	}
}
