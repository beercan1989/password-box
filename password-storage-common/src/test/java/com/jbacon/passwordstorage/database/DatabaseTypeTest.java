package com.jbacon.passwordstorage.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.junit.Test;

import com.jbacon.passwordstorage.database.dao.GenericDao;
import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public class DatabaseTypeTest {

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Android.createMaintenanceDao();
		verifyDao(result, MaintenanceDao.class);
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Android.createMasterPasswordDao();
		verifyDao(result, MasterPasswordDao.class);
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetAndroidStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Android.createStoredPasswordDao();
		verifyDao(result, StoredPasswordDao.class);
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Blackberry.createMaintenanceDao();
		verifyDao(result, MaintenanceDao.class);
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Blackberry.createMasterPasswordDao();
		verifyDao(result, MasterPasswordDao.class);
	}

	@Test(expected = UnsupportedDatabaseException.class)
	public void shouldFailToGetBlackberryStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Blackberry.createStoredPasswordDao();
		verifyDao(result, StoredPasswordDao.class);
	}

	private void verifyDao(final GenericDao result, final Class<?> mybatisDaoType) {
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(mybatisDaoType)));
	}

}
