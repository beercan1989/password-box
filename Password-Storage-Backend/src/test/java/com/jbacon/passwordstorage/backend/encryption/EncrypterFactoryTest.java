package com.jbacon.passwordstorage.backend.encryption;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.jbacon.passwordstorage.backend.database.DatabaseException;

public class EncrypterFactoryTest {

	private EncrypterFactory encrypterFactory;

	@Before
	public void setup() {
		encrypterFactory = new EncrypterFactory();
	}

	@Test
	public void shouldCreateAesEncrypter() throws DatabaseException {
		Encrypter result = encrypterFactory.getEncrypter(EncrypterType.AES_ENCRYPTER);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(EncrypterAES.class)));
	}

	@Test
	public void shouldCreatePbeEncrypter() throws DatabaseException {
		Encrypter result = encrypterFactory.getEncrypter(EncrypterType.PBEWithMD5AndDES_ENCRYPTER);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(EncrypterPBEWithMD5AndDES.class)));
	}

	@Test(expected = DatabaseException.class)
	public void shouldThrowExceptionOnBadEncrypterType() throws DatabaseException {
		encrypterFactory.getEncrypter(EncrypterType.UnsupportedPBEWithSHA256AndAES);
	}
}
