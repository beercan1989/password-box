package com.jbacon.passwordstorage.database.mybatis;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.junit.Test;

import com.jbacon.passwordstorage.database.dao.GenericDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;
import com.jbacon.test.tools.RemoveTestFiles;

public class DatabaseTypeTest {

	@Test
	public void shouldCreateComputerMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createMaintenanceDao("mybatisTest/Configuration.xml");
		verifyDao(result, MaintenanceMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createMasterPasswordDao("mybatisTest/Configuration.xml");
		verifyDao(result, MasterPasswordMybatisDao.class);
	}

	@Test
	public void shouldCreateComputerStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		GenericDao result = DatabaseType.MyBatis.createStoredPasswordDao("mybatisTest/Configuration.xml");
		verifyDao(result, StoredPasswordMybatisDao.class);
	}

	@Test(expected = AssertionError.class)
	public void shouldNotHaveCreatedAnything() throws IOException {
		RemoveTestFiles.remove("dbTest.sqlite");
	}

	private void verifyDao(final GenericDao result, final Class<?> mybatisDaoType) {
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(instanceOf(mybatisDaoType)));
	}

}
