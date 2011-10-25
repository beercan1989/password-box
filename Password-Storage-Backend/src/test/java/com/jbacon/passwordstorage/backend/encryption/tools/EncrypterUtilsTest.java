package com.jbacon.passwordstorage.backend.encryption.tools;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jbacon.passwordstorage.backend.encryption.EncryptionType;
import com.jbacon.passwordstorage.backend.encryption.errors.InvalidEncryptionTypeChangeException;
import com.jbacon.passwordstorage.backend.encryption.errors.NoSuchEncryptionException;

public class EncrypterUtilsTest {

	@BeforeClass
	public static void setupBeforeClass() {
		Security.addProvider(new BouncyCastleProvider());
	}

	private EncrypterUtils encrypterUtils;

	@Before
	public void setup() {
		encrypterUtils = new EncrypterUtils();
	}

	@Test
	public void shouldConvertByteArrayToString() throws UnsupportedEncodingException {
		assertThat(encrypterUtils.byteToString(new byte[] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100 }), is(equalTo("Hello World")));
	}

	@Test
	public void shouldConvertStringToByteArray() throws UnsupportedEncodingException {
		assertThat(encrypterUtils.stringToByte("Hello World"), is(equalTo(new byte[] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100 })));
	}

	@Test
	public void shouldGenerateAesEncryptionKey() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchEncryptionException {
		assertThat(encrypterUtils.generateAesEncryptionKey(EncryptionType.AES_256).length, is(equalTo(32)));
		assertThat(encrypterUtils.generateAesEncryptionKey(EncryptionType.AES_128).length, is(equalTo(16)));
	}

	@Test
	public void shouldGenerateDefaultSaltValueForPBEWithMD5AndDES() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchEncryptionException,
			InvalidEncryptionTypeChangeException {
		final byte[] generatedSalt = encrypterUtils.generateSalt(EncryptionType.PBE_WITH_MD5_AND_DES);
		assertThat(generatedSalt, is(not(nullValue())));
		assertThat(generatedSalt.length, is(not(lessThanOrEqualTo(0))));
		assertThat(generatedSalt[0], is(not(nullValue())));
		assertThat(generatedSalt.length, is(equalTo(8)));
	}

	@Test
	public void shouldGenerateSaltValueForPBEWithMD5AndDES() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchEncryptionException,
			InvalidEncryptionTypeChangeException {
		final byte[] generatedSalt = encrypterUtils.generateSalt(EncryptionType.PBE_WITH_MD5_AND_DES, 18);
		assertThat(generatedSalt, is(not(nullValue())));
		assertThat(generatedSalt.length, is(not(lessThanOrEqualTo(0))));
		assertThat(generatedSalt[0], is(not(nullValue())));
		assertThat(generatedSalt.length, is(equalTo(18)));
	}

	@Test(expected = InvalidEncryptionTypeChangeException.class)
	public void shouldThrowExceptionGeneratingSaltForAES() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchEncryptionException,
			InvalidEncryptionTypeChangeException {
		final byte[] generatedSalt = encrypterUtils.generateSalt(EncryptionType.AES_128);
		assertThat(generatedSalt, is(nullValue()));
	}

}
