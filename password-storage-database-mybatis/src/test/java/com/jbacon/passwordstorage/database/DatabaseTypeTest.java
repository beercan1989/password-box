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
import com.jbacon.passwordstorage.database.mybatis.DatabaseType;
import com.jbacon.passwordstorage.database.mybatis.MaintenanceMybatisDao;
import com.jbacon.passwordstorage.database.mybatis.MasterPasswordMybatisDao;
import com.jbacon.passwordstorage.database.mybatis.StoredPasswordMybatisDao;

public class DatabaseTypeTest {

	@Test
	public void shouldCreateComputerMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createMaintenanceDao();
		verifyDao(result, MaintenanceMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createMasterPasswordDao();
		verifyDao(result, MasterPasswordMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createStoredPasswordDao();
		verifyDao(result, StoredPasswordMybatisDao.class);
	}

	private void verifyDao(final GenericDao result, final Class<?> mybatisDaoType) {
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(mybatisDaoType)));
	}

}
