package com.jbacon.passwordstorage.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.junit.Test;

import com.jbacon.passwordstorage.database.dao.GenericDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public class DatabaseTypeTest {

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Android.createMaintenanceDao();
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Android.createMasterPasswordDao();
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Android.createStoredPasswordDao();
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Blackberry.createMaintenanceDao();
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Blackberry.createMasterPasswordDao();
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		DatabaseType.Blackberry.createStoredPasswordDao();
	}

	private void verifyDao(final GenericDao result, final Class<?> mybatisDaoType) {
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(mybatisDaoType)));
	}

}
