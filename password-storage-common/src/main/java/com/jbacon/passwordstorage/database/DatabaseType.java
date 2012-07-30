package com.jbacon.passwordstorage.database;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public enum DatabaseType implements Database {
	Android,

	Blackberry;

	@Override
	public MaintenanceDao createMaintenanceDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	@Override
	public MasterPasswordsDao createMasterPasswordDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}

	@Override
	public StoredPasswordsDao createStoredPasswordDao() throws UnsupportedDatabaseException, IOException {
		throw new UnsupportedDatabaseException("Database type has not been implemented yet.");
	}
}
