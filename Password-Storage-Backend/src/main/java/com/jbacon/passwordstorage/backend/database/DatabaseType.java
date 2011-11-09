package com.jbacon.passwordstorage.backend.database;

import java.io.IOException;

import com.jbacon.passwordstorage.backend.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.backend.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.backend.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.backend.database.errors.UnsupportedDatabaseException;
import com.jbacon.passwordstorage.backend.database.mybatis.MaintenanceMybatisDao;
import com.jbacon.passwordstorage.backend.database.mybatis.MasterPasswordMybatisDao;
import com.jbacon.passwordstorage.backend.database.mybatis.StoredPasswordMybatisDao;

public enum DatabaseType implements Database {
	Computer {
		@Override
		public MaintenanceDao createMaintenanceDao() throws IOException {
			return new MaintenanceMybatisDao();
		}

		@Override
		public MasterPasswordDao createMasterPasswordDao() throws IOException {
			return new MasterPasswordMybatisDao();
		}

		@Override
		public StoredPasswordDao createStoredPasswordDao() throws IOException {
			return new StoredPasswordMybatisDao();
		}
	},

	Android,

	Blackberry;

	@Override
	public MaintenanceDao createMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	@Override
	public MasterPasswordDao createMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	@Override
	public StoredPasswordDao createStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}
}
