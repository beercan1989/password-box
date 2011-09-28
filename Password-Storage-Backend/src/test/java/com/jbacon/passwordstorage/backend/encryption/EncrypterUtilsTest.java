package com.jbacon.passwordstorage.backend.encryption;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class EncrypterUtilsTest {

	private EncrypterUtils encrypterUtils;

	@Before
	public void setup() {
		encrypterUtils = new EncrypterUtils();
	}

	@Test
	public void shouldGenerateAesEncryptionKey() throws EncrypterException {
		assertThat(encrypterUtils.generateAesEncryptionKey().length, is(equalTo(32)));
	}

	@Test
	public void testByteToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testStringToByte() {
		fail("Not yet implemented"); // TODO
	}

}
