package com.jbacon.passwordstorage.backend.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.junit.Test;

import com.jbacon.passwordstorage.backend.database.dao.GenericDao;
import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;
import com.jbacon.passwordstorage.backend.database.mybatis.MaintenanceMybatisDao;
import com.jbacon.passwordstorage.backend.database.mybatis.MasterPasswordMybatisDao;
import com.jbacon.passwordstorage.backend.database.mybatis.StoredPasswordMybatisDao;

public class DatabaseTypeTest {

	@Test
	public void shouldCreateComputerMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Computer.createMaintenanceDao();
		verifyDao(result, MaintenanceMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Computer.createMasterPasswordDao();
		verifyDao(result, MasterPasswordMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.Computer.createStoredPasswordDao();
		verifyDao(result, StoredPasswordMybatisDao.class);
	}

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
