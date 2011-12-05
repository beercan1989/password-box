package com.jbacon.passwordstorage.database.errors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class DatabaseErrorsTest {

	@Test
	public void shouldVerifyThatUnsupportedDatabaseExceptionExists() {
		DatabaseException testException = new UnsupportedDatabaseException();
		assertThat(testException, is(not(nullValue())));

		testException = new UnsupportedDatabaseException(new Throwable());
		assertThat(testException, is(not(nullValue())));

		testException = new UnsupportedDatabaseException("Test");
		assertThat(testException, is(not(nullValue())));

		testException = new UnsupportedDatabaseException("Test", new Throwable());
		assertThat(testException, is(not(nullValue())));
	}
}
