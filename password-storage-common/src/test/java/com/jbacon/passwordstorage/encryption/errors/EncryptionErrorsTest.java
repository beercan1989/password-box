package com.jbacon.passwordstorage.encryption.errors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class EncryptionErrorsTest {

	@Test
	public void shouldVerifyThatBouncyCastleNotInstalledExceptionExists() {
		AbstractEncrypterException testException = new BouncyCastleNotInstalledException();
		assertThat(testException, is(not(nullValue())));

		testException = new BouncyCastleNotInstalledException(new Throwable());
		assertThat(testException, is(not(nullValue())));
	}

	@Test
	public void shouldVerifyThatInvalidExceptionExists() {
		AbstractEncrypterException testException = new InvalidEncryptionTypeForSaltGeneration();
		assertThat(testException, is(not(nullValue())));

		testException = new InvalidEncryptionTypeForSaltGeneration(new Throwable());
		assertThat(testException, is(not(nullValue())));

		testException = new InvalidEncryptionTypeForSaltGeneration("Test");
		assertThat(testException, is(not(nullValue())));

		testException = new InvalidEncryptionTypeForSaltGeneration("Test", new Throwable());
		assertThat(testException, is(not(nullValue())));
	}

	@Test
	public void shouldVerifyThatNoSuchEncryptionExceptionExists() {
		AbstractEncrypterException testException = new NoSuchEncryptionException();
		assertThat(testException, is(not(nullValue())));

		testException = new NoSuchEncryptionException(new Throwable());
		assertThat(testException, is(not(nullValue())));

		testException = new NoSuchEncryptionException("Test");
		assertThat(testException, is(not(nullValue())));
	}

	@Test
	public void shouldVerifyThatUnlimitedJcePoliciesNotInstalledExceptionExists() {
		AbstractEncrypterException testException = new UnlimitedJcePoliciesNotInstalledException();
		assertThat(testException, is(not(nullValue())));

		testException = new UnlimitedJcePoliciesNotInstalledException(new Throwable());
		assertThat(testException, is(not(nullValue())));
	}
}
