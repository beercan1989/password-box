package com.jbacon.passwordstorage.backend.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;

import org.junit.Test;

import com.jbacon.passwordstorage.backend.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;
import com.jbacon.passwordstorage.backend.database.mybatis.MaintenanceMybatisDao;

public class DatabaseFactoryTest {
	@Test
	public void testDesktopMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		MaintenanceDao dao = DatabaseType.Computer.createMaintenanceDao();

		assertThat(dao, is(not(nullValue())));
		assertThat(dao, is(instanceOf(MaintenanceMybatisDao.class)));
	}

	@Test
	public void testDesktopMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		MasterPasswordDao dao = DatabaseType.Computer.createMasterPasswordDao();
		assertThat(dao, is(not(nullValue())));
		assertThat(dao, is(instanceOf(MasterPasswordDao.class)));
	}

	@Test
	public void testDesktopStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		StoredPasswordDao dao = DatabaseType.Computer.createStoredPasswordDao();
		assertThat(dao, is(not(nullValue())));
		assertThat(dao, is(instanceOf(StoredPasswordDao.class)));
	}
}
