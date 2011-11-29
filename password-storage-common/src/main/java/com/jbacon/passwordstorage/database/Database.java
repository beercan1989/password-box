package com.jbacon.passwordstorage.database;

import java.io.IOException;

import com.jbacon.passwordstorage.database.dao.MaintenanceDao;
import com.jbacon.passwordstorage.database.dao.MasterPasswordDao;
import com.jbacon.passwordstorage.database.dao.StoredPasswordDao;
import com.jbacon.passwordstorage.database.errors.UnsupportedDatabaseException;

public interface Database {

	public MaintenanceDao createMaintenanceDao() throws UnsupportedDatabaseException, IOException;

	public MasterPasswordDao createMasterPasswordDao() throws UnsupportedDatabaseException, IOException;

	public StoredPasswordDao createStoredPasswordDao() throws UnsupportedDatabaseException, IOException;

}
