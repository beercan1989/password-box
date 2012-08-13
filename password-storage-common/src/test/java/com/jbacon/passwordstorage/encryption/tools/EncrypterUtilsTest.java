package com.jbacon.passwordstorage.encryption.tools;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.security.Security;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jbacon.passwordstorage.encryption.EncryptionType;
import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;
import com.jbacon.passwordstorage.encryption.errors.InvalidEncryptionTypeForSaltGeneration;

public class EncrypterUtilsTest {

	private static final String STRING_HELLOWORLD = "Hello World";
	private static final byte[] BYTEARRAY_HELLOWORLD = new byte[] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100 };
	private static final String HEXSTRING_HELLOWORLD = "48656c6c6f20576f726c64";

	@BeforeClass
	public static void setupBeforeClass() {
		Security.addProvider(new BouncyCastleProvider());
	}

	@Test
	public void shouldConvertByteArrayToString() throws UnsupportedEncodingException {
		assertThat(EncrypterUtils.byteToString(BYTEARRAY_HELLOWORLD), is(equalTo(STRING_HELLOWORLD)));
	}

	@Test
	public void shouldConvertStringToByteArray() throws UnsupportedEncodingException, DecoderException {
		assertThat(EncrypterUtils.stringToByte(STRING_HELLOWORLD), is(equalTo(BYTEARRAY_HELLOWORLD)));
	}
	
	@Test
	public void shouldConvertByteArrayToHexString() throws UnsupportedEncodingException {
		assertThat(EncrypterUtils.byteToHexString(BYTEARRAY_HELLOWORLD), is(equalTo(HEXSTRING_HELLOWORLD)));
	}

	@Test
	public void shouldConvertHexStringToByteArray() throws UnsupportedEncodingException, DecoderException {
		assertThat(EncrypterUtils.hexStringToByte(HEXSTRING_HELLOWORLD), is(equalTo(BYTEARRAY_HELLOWORLD)));
	}

	@Test
	public void shouldConvertStringToCharArray() {
		final char[] convertedString = EncrypterUtils.stringToChar("Test");
		assertThat(convertedString, is(not(nullValue())));
		assertThat(convertedString.length, is(not(lessThanOrEqualTo(0))));
		assertThat(convertedString[0], is(not(nullValue())));
		assertThat(convertedString.length, is(equalTo(4)));
	}

	@Test
	public void shouldGenerateAesEncryptionKey() throws AbstractEncrypterException {
		assertThat(EncrypterUtils.generateAesEncryptionKey(EncryptionType.AES_256).length, is(equalTo(32)));
		assertThat(EncrypterUtils.generateAesEncryptionKey(EncryptionType.AES_128).length, is(equalTo(16)));
	}

	@Test
	public void shouldGenerateDefaultSaltValueForPBEWithMD5AndDES() throws AbstractEncrypterException {
		final byte[] generatedSalt = EncrypterUtils.generateSalt(EncryptionType.PBE_WITH_MD5_AND_DES);
		assertThat(generatedSalt, is(not(nullValue())));
		assertThat(generatedSalt.length, is(not(lessThanOrEqualTo(0))));
		assertThat(generatedSalt[0], is(not(nullValue())));
		assertThat(generatedSalt.length, is(equalTo(8)));
	}

	@Test
	public void shouldGenerateSaltValueForPBEWithMD5AndDES() throws AbstractEncrypterException {
		final byte[] generatedSalt = EncrypterUtils.generateSalt(EncryptionType.PBE_WITH_MD5_AND_DES, 18);
		assertThat(generatedSalt, is(not(nullValue())));
		assertThat(generatedSalt.length, is(not(lessThanOrEqualTo(0))));
		assertThat(generatedSalt[0], is(not(nullValue())));
		assertThat(generatedSalt.length, is(equalTo(18)));
	}

	@Test(expected = InvalidEncryptionTypeForSaltGeneration.class)
	public void shouldThrowExceptionGeneratingSaltForAES() throws AbstractEncrypterException {
		EncrypterUtils.generateSalt(EncryptionType.AES_128);
	}

}
