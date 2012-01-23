package com.jbacon.passwordstorage.encryption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jbacon.passwordstorage.encryption.errors.AbstractEncrypterException;

public class EncrypterPBETest {

	@BeforeClass
	public static void setupBeforeTest() {
	}

	private final byte[] toEncrypt = new byte[] { 77, 121, 32, 77, 101, 115, 115, 97, 103, 101, 32, 84, 111, 32, 69, 110, 99, 114, 121, 112, 116 };
	private final byte[] toDecryptWithMd5AndDes = new byte[] { 14, -2, -89, -15, 47, 93, 69, 30, 37, -49, -112, 67, -98, -61, -86, -36, -33, 33, 21, 11, -114,
			-127, 78, 100 };
	private final byte[] toDecryptWithShaAndAes = new byte[] { -125, 62, 82, 87, -73, 29, 116, -11, -75, -21, 32, -91, -123, 44, 6, -48, 23, -11, 119, -70, 67,
			-118, 116, -8, 49, 81, -73, -48, -120, 46, 19, 15 };
	private final byte[] toDecryptWithShaAndTrippleDes = new byte[] { -45, 94, -124, 108, 17, -99, 18, 78, -92, 45, 22, 55, -12, 52, -128, -93, 32, 115, 78,
			78, 12, 9, -40, 110 };

	private final byte[] toDecryptWithShaAndTwofish = new byte[] { -102, 121, 113, 55, -76, -75, 21, -40, 3, 78, -39, 47, -29, 55, -40, 15, -25, 21, 61, -92,
			97, -71, -44, -126, -91, 100, 72, -66, -20, 41, 80, -61 };
	private final char[] passPhrase = new char[] { 'M', 'y', 'P', 'a', 's', 's', 'w', 'o', 'r', 'd' };

	private final byte[] salt = new byte[] { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

	@Test
	public void shouldWithMd5AndDesDecrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_MD5_AND_DES.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, toDecryptWithMd5AndDes, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldWithMd5AndDesEncrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_MD5_AND_DES.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, toEncrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecryptWithMd5AndDes)));
	}

	@Test
	public void shouldWithShaAndAesDecrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, toDecryptWithShaAndAes, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldWithShaAndAesEncrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA1_AND_256_AES_CBC_BC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, toEncrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecryptWithShaAndAes)));
	}

	@Test
	public void shouldWithShaAndTrippleDesDecrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, toDecryptWithShaAndTrippleDes, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldWithShaAndTrippleDesEncrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA_AND_3_KEY_TRIPPLE_DES_CBC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, toEncrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecryptWithShaAndTrippleDes)));
	}

	@Test
	public void shouldWithShaAndTwofishDecrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.DECRYPT_MODE, salt, toDecryptWithShaAndTwofish, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toEncrypt)));
	}

	@Test
	public void shouldWithShaAndTwofishEncrypt() throws AbstractEncrypterException {
		EncrypterPBE encrypter = (EncrypterPBE) EncryptionType.PBE_WITH_SHA_AND_TWOFISH_CBC.getEncrypter();

		final byte[] result = encrypter.doCiper(EncryptionMode.ENCRYPT_MODE, salt, toEncrypt, passPhrase);

		assertThat(result, is(not(nullValue())));
		assertThat(result.length, is(greaterThan(0)));
		assertThat(result, is(equalTo(toDecryptWithShaAndTwofish)));
	}
}
