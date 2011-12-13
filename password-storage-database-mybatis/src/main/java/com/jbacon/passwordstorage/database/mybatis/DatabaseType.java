package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;

import com.jbacon.passwordstorage.database.Database;
import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public enum DatabaseType implements Database {
	MyBatis {
		@Override
		public MaintenanceDao createMaintenanceDao() throws IOException {
			return new MaintenanceMybatisDao();
		}

		@Override
		public MaintenanceDao createMaintenanceDao(final String configurationFile) throws UnsupportedDatabaseException, IOException {
			return new MaintenanceMybatisDao(configurationFile);
		}

		@Override
		public MasterPasswordDao createMasterPasswordDao() throws IOException {
			return new MasterPasswordMybatisDao();
		}

		@Override
		public MasterPasswordDao createMasterPasswordDao(final String configurationFile) throws UnsupportedDatabaseException, IOException {
			return new MasterPasswordMybatisDao(configurationFile);
		}

		@Override
		public StoredPasswordDao createStoredPasswordDao() throws IOException {
			return new StoredPasswordMybatisDao();
		}

		@Override
		public StoredPasswordDao createStoredPasswordDao(final String configurationFile) throws UnsupportedDatabaseException, IOException {
			return new StoredPasswordMybatisDao(configurationFile);
		}
	};

	public MaintenanceDao createMaintenanceDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	public MasterPasswordDao createMasterPasswordDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	public StoredPasswordDao createStoredPasswordDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

}
