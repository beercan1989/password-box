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
		Encrypter result = encrypterFactory.getEncrypter(EncryptionType.AES_256);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(EncrypterAES.class)));
	}

	@Test
	public void shouldCreatePbeEncrypter() throws DatabaseException {
		Encrypter result = encrypterFactory.getEncrypter(EncryptionType.PBE_WITH_MD5_AND_DES);
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(EncrypterPBE.class)));
	}

	@Test(expected = DatabaseException.class)
	public void shouldThrowExceptionOnBadEncrypterType() throws DatabaseException {
		encrypterFactory.getEncrypter(EncryptionType.UNSUPPORTED_TYPE);
	}
}
