package com.jbacon.passwordstorage.database.mybatis;

import java.io.IOException;

import com.jbacon.passwordstorage.database.Database;
import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
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
		public MasterPasswordsDao createMasterPasswordDao() throws IOException {
			return new MasterPasswordMybatisDao();
		}

		@Override
		public MasterPasswordsDao createMasterPasswordDao(final String configurationFile) throws UnsupportedDatabaseException, IOException {
			return new MasterPasswordMybatisDao(configurationFile);
		}

		@Override
		public StoredPasswordsDao createStoredPasswordDao() throws IOException {
			return new StoredPasswordMybatisDao();
		}

		@Override
		public StoredPasswordsDao createStoredPasswordDao(final String configurationFile) throws UnsupportedDatabaseException, IOException {
			return new StoredPasswordMybatisDao(configurationFile);
		}
	};

	public MaintenanceDao createMaintenanceDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	public MasterPasswordsDao createMasterPasswordDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	public StoredPasswordsDao createStoredPasswordDao(final String configurationFilePath) throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

}
