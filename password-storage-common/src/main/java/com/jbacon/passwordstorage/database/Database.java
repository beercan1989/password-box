package com.jbacon.passwordstorage.database;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordsDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordsDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public interface Database {

	public MaintenanceDao createMaintenanceDao() throws UnsupportedDatabaseException, IOException;

	public MasterPasswordsDao createMasterPasswordDao() throws UnsupportedDatabaseException, IOException;

	public StoredPasswordsDao createStoredPasswordDao() throws UnsupportedDatabaseException, IOException;
}
